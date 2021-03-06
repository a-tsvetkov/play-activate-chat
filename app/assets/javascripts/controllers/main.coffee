define(
    ["angular", "app", "controllers/ChatList", "controllers/ChatInfo"],
    (angular, app, ChatList, ChatInfo) ->
        angular.module("chat").controller "ChatList", ['$scope', 'ChatService', ChatList]
        angular.module("chat").controller "ChatInfo",
            ['$scope', '$routeParams', 'ChatService', 'MessageService', ChatInfo]
)
