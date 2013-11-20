define(
    ["angular", "services/ChatService"]
    (angular) ->
        class ChatList
            constructor: ($scope, ChatService) ->
                $scope.chats = ChatService.query()
)
