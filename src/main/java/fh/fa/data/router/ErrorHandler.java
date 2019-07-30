package fh.fa.data.router;

import fh.fa.data.model.error.Error;
import fh.fa.data.model.error.SystemException;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Order(-10)
public class ErrorHandler extends AbstractErrorWebExceptionHandler {

    public ErrorHandler(final ErrorAttributes errorAttributes,
                        final ResourceProperties resourceProperties,
                        final ApplicationContext applicationContext,
                        final ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, resourceProperties, applicationContext);
        this.setMessageReaders(serverCodecConfigurer.getReaders());
        this.setMessageWriters(serverCodecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::handleError);
    }

    private Mono<ServerResponse> handleError(final ServerRequest request) {
        Throwable throwable = this.getError(request);
        if (throwable instanceof SystemException) {
            return getResponseEntity((SystemException) throwable);
        }
        return defaultHandleException(throwable);
    }

    protected Mono<ServerResponse> defaultHandleException(final Throwable e) {
        return getResponseEntity(SystemException.newDefault().courceCause(e.getMessage()).build());
    }

    private Mono<ServerResponse> getResponseEntity(final SystemException systemException) {
        return ServerResponse.status(systemException.getErrorCode().getStatus())
                             .syncBody(new Error(systemException.getSource(),
                                                 systemException.getSourceCause(),
                                                 systemException.getErrorCode(),
                                                 systemException.getDetails()));
    }
}
