package fh.fa.data.repository;

import fh.fa.data.model.error.ErrorCode;
import fh.fa.data.model.error.SystemException;
import org.reactivestreams.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

abstract class ReactiveRepository<ENTITY_MODEL extends DatabaseEntity> {

    final JpaRepository<ENTITY_MODEL, Long> repository;

    public ReactiveRepository(final JpaRepository<ENTITY_MODEL, Long> repository) {
        this.repository = repository;
    }

    abstract String getRepositoryIdentification();

    public Mono<ENTITY_MODEL> findById(final Mono<Long> id) {

        return id.map(entityId -> {
            var material = repository.findById(entityId);
            if (material.isPresent()) {
                return material.get();
            } else {
                throw SystemException.newException(this.getRepositoryIdentification(), "Entity not found", ErrorCode.NOT_FOUND)
                                     .log("id", entityId.toString())
                                     .build();
            }
        });
    }

    public Flux<ENTITY_MODEL> findAll() {

        return Flux.fromIterable(repository.findAll());
    }

    public Mono<ENTITY_MODEL> save(final Mono<ENTITY_MODEL> newEntity) {
        return newEntity
                .doOnNext(entity -> {
                    if (!repository.existsById(entity.getId())) {
                        try {
                            repository.save(entity);
                        } catch (Exception e) {
                            throw SystemException.newException(this.getRepositoryIdentification(), e.getMessage(), ErrorCode.INTERNAL_ERROR)
                                                 .log("id", entity.getId().toString())
                                                 .build();
                        }
                    } else {
                        throw SystemException.newException(this.getRepositoryIdentification(), "Entity already exists", ErrorCode.ALREADY_EXISTS)
                                             .log("id", entity.getId().toString())
                                             .build();
                    }
                });
    }

    public Flux<ENTITY_MODEL> save(final Flux<ENTITY_MODEL> newEntity) {
        return newEntity
                .collectList()
                .doOnNext(repository::saveAll)
                .flatMapMany(Flux::fromIterable);
    }

    public Mono<Void> delete(final Mono<Long> id) {
        return id.doOnNext(repository::deleteById)
                 .thenEmpty(Subscriber::onComplete);
    }

    public Mono<ENTITY_MODEL> update(final Mono<Long> id, final Mono<ENTITY_MODEL> entity) {

        return id.zipWith(entity)
                 .map(tuple -> {
                     var entityId = tuple.getT1();
                     var entityData = tuple.getT2();

                     if (!entityId.equals(entityData.getId())) {
                         throw SystemException.newException(this.getRepositoryIdentification(), "Ids dont match", ErrorCode.ILLEGAL_ARGUMENT)
                                              .log("path_id", entityId.toString())
                                              .log("body_id", entityData.getId().toString())
                                              .build();
                     }

                     if (!repository.existsById(entityId)) {
                         throw SystemException.newException(this.getRepositoryIdentification(), "Not found", ErrorCode.NOT_FOUND)
                                              .log("material_id", entityId.toString())
                                              .build();
                     }

                     return repository.save(entityData);
                 });
    }
}
