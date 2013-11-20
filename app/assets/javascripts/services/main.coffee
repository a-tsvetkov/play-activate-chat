define(
    ["angular", "app", "services/ChatService", "services/MessageService"]
    (angular, app, ChatService, MessageService) ->
        angular.module("chat").service 'ChatService', ["$resource", ChatService]
        angular.module("chat").service 'MessageService', ["$resource", MessageService]
)
