object Compose {
    const val composeVersion = "1.1.0-rc01"
    const val composeCompilerVersion = "1.3.1"
    const val material = "androidx.compose.material:material:$composeVersion"
    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
    const val compiler = "androidx.compose.compiler:compiler:$composeCompilerVersion"

    private const val navigationVersion = "2.4.0-beta02"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0-beta01"
    const val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"

    private const val activityComposeVersion = "1.4.0"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"

    private const val viewModelComposeVersion = "2.5.1"
    const val viewModelCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:$viewModelComposeVersion"

    private const val lifecycleComposeVersion = "2.6.0-alpha03"
    const val lifecycleCompose =
        "androidx.lifecycle:lifecycle-runtime-compose:$lifecycleComposeVersion"
}
