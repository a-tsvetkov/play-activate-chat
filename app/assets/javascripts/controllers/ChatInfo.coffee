define(
    ["angular", "services/ChatService"],
    (angular) ->
        class ChatInfo
            constructor: ($scope, $routeParams, ChatService, MessageService) ->
                chat_id = $routeParams.chatId
                $scope.chat = ChatService.get(chat_id)

                $scope.channel = MessageService.channel()
                $scope.channel.onmessage = (message) ->
                    $scope.$apply ()->
                        $scope.chat.messages.unshift(JSON.parse(message.data))

                $scope.sendMessage = () ->
                    this.message.chat = chat_id
                    $scope.channel.send(JSON.stringify(this.message))
                    $scope.message.body = ''
)
