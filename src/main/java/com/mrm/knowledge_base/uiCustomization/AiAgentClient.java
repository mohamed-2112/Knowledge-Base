package com.mrm.knowledge_base.uiCustomization;


import com.mrm.knowledge_base.configuration.AiAgentProperties;
import com.mrm.knowledge_base.uiCustomization.dto.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class AiAgentClient {
    private static final String SERVICE_TOKEN_HEADER = "X-Service-Token";
    private static final String AUTHENTICATION_USER_ID_HEADER = "X-Authenticated-User-Id";

    private final WebClient aiAgentWebClient;
    private final AiAgentProperties properties;

    public AiAgentClient(WebClient aiAgentWebClient, AiAgentProperties properties) {
        this.aiAgentWebClient = aiAgentWebClient;
        this.properties = properties;
    }

    public UiCustomizationDraftResponse createDraft(
            String authenticatedUserId,
            UiCustomizationDraftRequest request
    ) {
        return aiAgentWebClient
                .post()
                .uri("/agent/ui-customization/draft")
                .headers(headers -> addInternalHeaders(headers, authenticatedUserId))
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .defaultIfEmpty("AI agnet service error")
                                .flatMap(body -> Mono.error(
                                        new ResponseStatusException(
                                                response.statusCode(),
                                                body
                                        )
                                ))
                )
                .bodyToMono(UiCustomizationDraftResponse.class)
                .block();
    }

    public UiDraftDetailsResponse getDraft(
            String authenticatedUserId,
            String draftId
    ) {
        return aiAgentWebClient
                .get()
                .uri("/agent/ui-customization/drafts/{draftId}", draftId)
                .headers(headers -> addInternalHeaders(headers, authenticatedUserId))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .defaultIfEmpty("AI agent service error")
                                .flatMap(body -> Mono.error(
                                        new ResponseStatusException(
                                                response.statusCode(),
                                                body
                                        )
                                ))
                )
                .bodyToMono(UiDraftDetailsResponse.class)
                .block();
    }

    public UiApplyDraftResponse applyDraft(
            String authenticatedUserId,
            String draftId
    ) {
        return aiAgentWebClient
                .post()
                .uri("/agent/ui-customization/drafts/{draftId}/apply", draftId)
                .headers(headers -> addInternalHeaders(headers, authenticatedUserId))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .defaultIfEmpty("AI agent service error")
                                .flatMap(body -> Mono.error(
                                        new ResponseStatusException(
                                                response.statusCode(),
                                                body
                                        )
                                ))
                )
                .bodyToMono(UiApplyDraftResponse.class)
                .block();
    }

    public UiActiveTemplateResponse getActiveTemplate(
            String authenticatedUserId,
            String page
    ) {
        return aiAgentWebClient
                .get()
                .uri("/agent/ui-customization/pages/{page}/active", page)
                .headers(headers -> addInternalHeaders(headers, authenticatedUserId))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .defaultIfEmpty("AI agent service error")
                                .flatMap(body -> Mono.error(
                                        new ResponseStatusException(
                                                response.statusCode(),
                                                body
                                        )
                                ))
                )
                .bodyToMono(UiActiveTemplateResponse.class)
                .block();
    }




    private void addInternalHeaders(
            HttpHeaders headers,
            String authenticatedUserId
    ) {
        headers.set(SERVICE_TOKEN_HEADER, properties.getServiceToken());
        headers.set(AUTHENTICATION_USER_ID_HEADER, authenticatedUserId);
    }

}
