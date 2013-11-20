"use strict"

requirejs.config
    packages: ["controllers", "services"]
    shim:
        "jquery":
            exports: "$"
        "angular":
            deps: ["jquery"]
            exports: "angular"
        "angular-resource":
            deps: ["angular"]
        "angular-route":
            deps: ["angular"]
    priority: ["angular"]

# requirejs.onError = (err) ->
#     console.log(err)

define("jquery", ["webjars!jquery.js"], () -> $)
define("bootstrap", ["webjars!bootstrap.js"])
define("angular", ["webjars!angular.js"], () -> angular)
define("angular-route", ["webjars!angular-route.js"])
define("angular-resource", ["webjars!angular-resource.js"])

define("jsRoutes", ["/routes.js"], () -> jsRoutes)

require(
    ["angular", "angular-route", "angular-resource", "app", "routes", "controllers", "services"],
    (angular) ->
        angular.bootstrap document, ["chat"]
)
