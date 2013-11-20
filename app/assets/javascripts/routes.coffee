define(
    ["angular", "jsRoutes", "app"]
    (angular) ->
        class Routes
            constructor: ($routeProvider) ->
                $routeProvider
                    .when '/:chatId',
                        templateUrl: jsRoutes.controllers.Assets.at("templates/chat.html").url
                        controller: 'ChatInfo'
                    .otherwise
                        redirectTo: '/'
        angular.module("chat").config ["$routeProvider", Routes]
)
