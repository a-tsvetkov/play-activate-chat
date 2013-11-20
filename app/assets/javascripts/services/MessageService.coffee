define(
    ['angular', 'jsRoutes'],
    (angular, jsRoutes) ->
        class MessageService
            constructor: ($resource) ->
                MessageService::channel = () ->
                    new WebSocket('ws://localhost:9000' + jsRoutes.controllers.MessageAPI.channel().url)
    )
