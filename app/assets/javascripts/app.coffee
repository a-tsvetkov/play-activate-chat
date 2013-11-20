define(
    ["angular", "angular-route", "angular-resource"],
    (angular) ->
        angular.module "chat", ["ngRoute", "ngResource"]
)
