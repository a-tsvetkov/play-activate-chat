define(
    ["angular", "jsRoutes"]
    (angular, jsRoutes) ->
        class ChatService
            constructor: ($resource) ->
                ChatResource = $resource jsRoutes.controllers.ChatAPI.list().url + '/:chatId', {},
                    query:
                        method: 'GET'
                        isArray: false
                    get:
                        method: 'GET'
                        isArray: false
                    save:
                        method: 'POST'
                        isArray: false

                ChatService::query = () ->
                    ChatResource.query()

                ChatService::get = (id) ->
                    ChatResource.get
                        chatId: id

                ChatService::save = () ->
                    ChatResource.save()
)
