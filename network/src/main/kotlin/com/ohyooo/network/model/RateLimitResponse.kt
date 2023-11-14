package com.ohyooo.network.model


data class RateLimitResponse(var rate: Rate? = null, var resources: Resources? = null) : BaseResponse()


data class Rate(var limit: Int?, var remaining: Int?, var reset: Int?)


data class Resources(var core: Core?, var graphql: Graphql?, var integration_manifest: IntegrationManifest?, var search: Search?, var source_import: SourceImport?)


data class Core(var limit: Int?, var remaining: Int?, var reset: Int?)


data class Graphql(var limit: Int?, var remaining: Int?, var reset: Int?)


data class IntegrationManifest(var limit: Int?, var remaining: Int?, var reset: Int?)


data class Search(var limit: Int?, var remaining: Int?, var reset: Int?)


data class SourceImport(var limit: Int?, var remaining: Int?, var reset: Int?)
