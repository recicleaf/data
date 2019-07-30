package fh.fa.data.repository;

import fh.fa.data.model.Material;
import fh.fa.data.model.error.ErrorCode;
import fh.fa.data.model.error.SystemException;
import org.reactivestreams.Subscriber;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MaterialReactiveRepository {

    private static final String ERROR_SOURCE = "Material Repository";

    private final MaterialRepository materialRepository;

    public MaterialReactiveRepository(final MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Mono<Material> findById(final Mono<Long> id) {

        return id.map(materialId -> {
            var material = materialRepository.findById(materialId);
            if (material.isPresent()) {
                return material.get();
            } else {
                throw SystemException.newException(ERROR_SOURCE, "Material not found", ErrorCode.NOT_FOUND)
                                     .log("id", materialId.toString())
                                     .build();
            }
        });
    }

    public Flux<Material> findAll() {

        return Flux.fromIterable(materialRepository.findAll());
    }

    public Mono<Material> save(final Mono<Material> newMaterial) {
        return newMaterial
                .doOnNext(material -> {
                    if (!materialRepository.existsById(material.getId())) {
                        try {
                            materialRepository.save(material);
                        } catch (Exception e) {
                            throw SystemException.newException(ERROR_SOURCE, e.getMessage(), ErrorCode.INTERNAL_ERROR)
                                                 .log("id", material.getId().toString())
                                                 .build();
                        }
                    } else {
                        throw SystemException.newException(ERROR_SOURCE, "Material already exists", ErrorCode.ALREADY_EXISTS)
                                             .log("id", material.getId().toString())
                                             .build();
                    }
                });
    }

    public Mono<Void> delete(final Mono<Long> id) {
        return id.doOnNext(materialRepository::deleteById)
                 .thenEmpty(Subscriber::onComplete);
    }

    public Mono<Material> update(final Mono<Long> id, final Mono<Material> material) {

        return id.zipWith(material)
                 .map(tuple -> {
                     var materialId = tuple.getT1();
                     var materialData = tuple.getT2();

                     if (!materialId.equals(materialData.getId())) {
                         throw SystemException.newException(ERROR_SOURCE, "Ids not match", ErrorCode.ILLEGAL_ARGUMENT)
                                              .log("path_id", materialId.toString())
                                              .log("body_id", materialData.getId().toString())
                                              .build();
                     }

                     if (!materialRepository.existsById(materialId)) {
                         throw SystemException.newException(ERROR_SOURCE, "Not found", ErrorCode.NOT_FOUND)
                                              .log("material_id", materialId.toString())
                                              .build();
                     }

                     return materialRepository.save(materialData);
                 });
    }
}
