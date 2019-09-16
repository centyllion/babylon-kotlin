@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals", "FunctionName")
package babylonjs

import org.w3c.files.File
import org.w3c.xhr.ProgressEvent
import kotlin.js.Promise

/**
 * Class used to represent data loading progression
 */
external class SceneLoaderProgressEvent {
    /** defines if data length to load can be evaluated */
    val lengthComputable: Boolean
    /** defines the loaded data length */
    val loaded: String
    /** defines the data length to load */
    val total: String
    /**
     * Create a new progress event
     * @param lengthComputable defines if data length to load can be evaluated
     * @param loaded defines the loaded data length
     * @param total defines the data length to load
     */
    constructor(
        /** defines if data length to load can be evaluated */
        lengthComputable: Boolean,
        /** defines the loaded data length */
        loaded: String,
        /** defines the data length to load */
        total: String)

    companion object {
        /**
         * Creates a new SceneLoaderProgressEvent from a ProgressEvent
         * @param event defines the source event
         * @returns a new SceneLoaderProgressEvent
         */
        fun FromProgressEvent(event: ProgressEvent): SceneLoaderProgressEvent
    }
}
/**
 * Interface used by SceneLoader plugins to define supported file extensions
 */
external interface ISceneLoaderPluginExtensions {
    /**
     * Defines the list of supported extensions
     */
    val extension: Map<String, ExtensionDescription>
}
/**
 * Interface used by SceneLoader plugin factory
 */
external interface ISceneLoaderPluginFactory {
    /**
     * Defines the name of the factory
     */
    val name: String
    /**
     * Function called to create a new plugin
     * @return the new plugin
     */
    fun createPlugin(): ISceneLoaderPlugin /*| ISceneLoaderPluginAsync*/
    /**
     * Boolean indicating if the plugin can direct load specific data
     */
    var canDirectLoad: ((data: String) -> Boolean)?
}

/**
 * Interface used to define a SceneLoader plugin
 */
external interface ISceneLoaderPlugin {
    /**
     * The friendly name of this plugin.
     */
    val name: String
    /**
     * The file extensions supported by this plugin.
     */
    val extensions: String /*| ISceneLoaderPluginExtensions*/
    /**
     * Import meshes into a scene.
     * @param meshesNames An array of mesh names, a single mesh name, or empty String for all meshes that filter what meshes are imported
     * @param scene The scene to import into
     * @param data The data to import
     * @param rootUrl The root url for scene and resources
     * @param meshes The meshes array to import into
     * @param particleSystems The particle systems array to import into
     * @param skeletons The skeletons array to import into
     * @param onError The callback when import fails
     * @returns True if successful or false otherwise
     */
    fun importMesh(meshesNames: Any, scene: Scene, data: Any, rootUrl: String, meshes: Array<AbstractMesh>, particleSystems: Array<IParticleSystem>, skeletons: Array<Skeleton>, onError: ((message: String, exception: Any?) -> Unit)?): Boolean
    /**
     * Load into a scene.
     * @param scene The scene to load into
     * @param data The data to import
     * @param rootUrl The root url for scene and resources
     * @param onError The callback when import fails
     * @returns true if successful or false otherwise
     */
    fun load(scene: Scene, data: String, rootUrl: String, onError: ((message: String, exception: Any?) -> Unit)?): Boolean
    /**
     * The callback that returns true if the data can be directly loaded.
     */
    var canDirectLoad: ((data: String) -> Boolean)?
    /**
     * The callback that allows custom handling of the root url based on the response url.
     */
    var rewriteRootURL: ((rootUrl: String, responseURL: String?) -> String)?
    /**
     * Load into an asset container.
     * @param scene The scene to load into
     * @param data The data to import
     * @param rootUrl The root url for scene and resources
     * @param onError The callback when import fails
     * @returns The loaded asset container
     */
    fun loadAssetContainer(scene: Scene, data: String, rootUrl: String, onError: ((message: String, exception: Any?) -> Unit)?): AssetContainer
}
/**
 * Interface used to define an async SceneLoader plugin
 */
external interface ISceneLoaderPluginAsync {
    /**
     * The friendly name of this plugin.
     */
    val name: String
    /**
     * The file extensions supported by this plugin.
     */
    var extensions: String /*| ISceneLoaderPluginExtensions*/
    /**
     * Import meshes into a scene.
     * @param meshesNames An array of mesh names, a single mesh name, or empty String for all meshes that filter what meshes are imported
     * @param scene The scene to import into
     * @param data The data to import
     * @param rootUrl The root url for scene and resources
     * @param onProgress The callback when the load progresses
     * @param fileName Defines the name of the file to load
     * @returns The loaded meshes, particle systems, skeletons, and animation groups
     */
    fun importMeshAsync(meshesNames: Any, scene: Scene, data: Any, rootUrl: String, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, fileName: String?): Promise<ImportedMesh>
    /**
     * Load into a scene.
     * @param scene The scene to load into
     * @param data The data to import
     * @param rootUrl The root url for scene and resources
     * @param onProgress The callback when the load progresses
     * @param fileName Defines the name of the file to load
     * @returns Nothing
     */
    fun loadAsync(scene: Scene, data: String, rootUrl: String, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, fileName: String?): Promise<Unit>
    /**
     * The callback that returns true if the data can be directly loaded.
     */
    var canDirectLoad: ((data: String) -> Boolean)?
    /**
     * The callback that allows custom handling of the root url based on the response url.
     */
    var rewriteRootURL: ((rootUrl: String, responseURL: String?) -> String)?
    /**
     * Load into an asset container.
     * @param scene The scene to load into
     * @param data The data to import
     * @param rootUrl The root url for scene and resources
     * @param onProgress The callback when the load progresses
     * @param fileName Defines the name of the file to load
     * @returns The loaded asset container
     */
    fun loadAssetContainerAsync(scene: Scene, data: String, rootUrl: String, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, fileName: String?): Promise<AssetContainer>
}
/**
 * Class used to load scene from various file formats using registered plugins
 * @see [http://doc.babylonjs.com/how_to/load_from_any_file_type]
 */
external class SceneLoader {
    companion object {
        /**
         * No logging while loading
         */
        val NO_LOGGING: String
        /**
         * Minimal logging while loading
         */
        val MINIMAL_LOGGING: String
        /**
         * Summary logging while loading
         */
        val SUMMARY_LOGGING: String
        /**
         * Detailled logging while loading
         */
        val DETAILED_LOGGING: String
        /**
         * Gets or sets a Boolean indicating if entire scene must be loaded even if scene contains incremental data
         */
        var ForceFullSceneLoadingForIncremental: Boolean
        /**
         * Gets or sets a Boolean indicating if loading screen must be displayed while loading a scene
         */
        var ShowLoadingScreen: Boolean
        /**
         * Defines the current logging level (while loading the scene)
         * @ignorenaming
         */
        var loggingLevel: String
        /**
         * Gets or set a Boolean indicating if matrix weights must be cleaned upon loading
         */
        var CleanBoneMatrixWeights: Boolean
        /**
         * Event raised when a plugin is used to load a scene
         */
        var OnPluginActivatedObservable: Observable<ISceneLoaderPlugin /*| ISceneLoaderPluginAsync*/>
        /**
         * Gets a plugin that can load the given extension
         * @param extension defines the extension to load
         * @returns a plugin or null if none works
         */
        fun GetPluginForExtension(extension: String): ISceneLoaderPlugin /*| ISceneLoaderPluginAsync | ISceneLoaderPluginFactory*/
        /**
         * Gets a Boolean indicating that the given extension can be loaded
         * @param extension defines the extension to load
         * @returns true if the extension is supported
         */
        fun IsPluginForExtensionAvailable(extension: String): Boolean
        /**
         * Adds a new plugin to the list of registered plugins
         * @param plugin defines the plugin to add
         */
        fun RegisterPlugin(plugin: ISceneLoaderPlugin /*| ISceneLoaderPluginAsync*/)
        /**
         * Import meshes into a scene
         * @param meshNames an array of mesh names, a single mesh name, or empty String for all meshes that filter what meshes are imported
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param scene the instance of BABYLON.Scene to append to
         * @param onSuccess a callback with a list of imported meshes, particleSystems, and skeletons when import succeeds
         * @param onProgress a callback with a progress event for each file being loaded
         * @param onError a callback with the scene, a message, and possibly an exception when import fails
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded plugin
         */
        fun ImportMesh(
            meshNames: Any, rootUrl: String, sceneFilename: String? /*| File?*/, scene: Scene?,
            onSuccess: ((meshes: Array<AbstractMesh>, particleSystems: Array<IParticleSystem>, skeletons: Array<Skeleton>, animationGroups: Array<AnimationGroup>) -> Unit)?,
            onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, onError:((scene: Scene, message: String, exception: Any?) -> Unit),
            pluginExtension: String?
        ): ISceneLoaderPlugin? // ISceneLoaderPluginAsync?
        /**
         * Import meshes into a scene
         * @param meshNames an array of mesh names, a single mesh name, or empty String for all meshes that filter what meshes are imported
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param scene the instance of BABYLON.Scene to append to
         * @param onSuccess a callback with a list of imported meshes, particleSystems, and skeletons when import succeeds
         * @param onProgress a callback with a progress event for each file being loaded
         * @param onError a callback with the scene, a message, and possibly an exception when import fails
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded plugin
         */
        fun ImportMesh(
            meshNames: Any, rootUrl: String, sceneFilename: File?, scene: Scene?,
            onSuccess: ((meshes: Array<AbstractMesh>, particleSystems: Array<IParticleSystem>, skeletons: Array<Skeleton>, animationGroups: Array<AnimationGroup>) -> Unit)?,
            onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, onError:((scene: Scene, message: String, exception: Any?) -> Unit),
            pluginExtension: String?
        ): ISceneLoaderPlugin? // ISceneLoaderPluginAsync?
        /**
         * Import meshes into a scene
         * @param meshNames an array of mesh names, a single mesh name, or empty String for all meshes that filter what meshes are imported
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param scene the instance of BABYLON.Scene to append to
         * @param onProgress a callback with a progress event for each file being loaded
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded list of imported meshes, particle systems, skeletons, and animation groups
         */
        fun ImportMeshAsync(meshNames: Any, rootUrl: String, sceneFilename: String? /*| File?*/, scene:Scene?, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, pluginExtension: String?): Promise<ImportedMesh>
        /**
         * Import meshes into a scene
         * @param meshNames an array of mesh names, a single mesh name, or empty String for all meshes that filter what meshes are imported
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param scene the instance of BABYLON.Scene to append to
         * @param onProgress a callback with a progress event for each file being loaded
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded list of imported meshes, particle systems, skeletons, and animation groups
         */
        fun ImportMeshAsync(meshNames: Any, rootUrl: String, sceneFilename: File?, scene:Scene?, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, pluginExtension: String?): Promise<ImportedMesh>
        /**
         * Load a scene
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param engine is the instance of BABYLON.Engine to use to create the scene
         * @param onSuccess a callback with the scene when import succeeds
         * @param onProgress a callback with a progress event for each file being loaded
         * @param onError a callback with the scene, a message, and possibly an exception when import fails
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded plugin
         */
        fun Load(rootUrl: String, sceneFilename: String?, engine:Engine?, onSuccess: ((scene: Scene) -> Unit)?, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, onError: ((scene: Scene, message: String, exception: Any?) -> Unit)?, pluginExtension: String?): ISceneLoaderPlugin? // | ISceneLoaderPluginAsync
        /**
         * Load a scene
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param engine is the instance of BABYLON.Engine to use to create the scene
         * @param onSuccess a callback with the scene when import succeeds
         * @param onProgress a callback with a progress event for each file being loaded
         * @param onError a callback with the scene, a message, and possibly an exception when import fails
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded plugin
         */
        fun Load(rootUrl: String, sceneFilename: File?, engine:Engine?, onSuccess: ((scene: Scene) -> Unit)?, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, onError: ((scene: Scene, message: String, exception: Any?) -> Unit)?, pluginExtension: String?): ISceneLoaderPlugin? // | ISceneLoaderPluginAsync
        /**
         * Load a scene
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param engine is the instance of BABYLON.Engine to use to create the scene
         * @param onProgress a callback with a progress event for each file being loaded
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded scene
         */
        fun LoadAsync(rootUrl: String, sceneFilename: String?, engine: Engine?, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, pluginExtension: String?): Promise<Scene>
        /**
         * Load a scene
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param engine is the instance of BABYLON.Engine to use to create the scene
         * @param onProgress a callback with a progress event for each file being loaded
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded scene
         */
        fun LoadAsync(rootUrl: String, sceneFilename: File?, engine: Engine?, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, pluginExtension: String?): Promise<Scene>
        /**
         * Append a scene
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param scene is the instance of BABYLON.Scene to append to
         * @param onSuccess a callback with the scene when import succeeds
         * @param onProgress a callback with a progress event for each file being loaded
         * @param onError a callback with the scene, a message, and possibly an exception when import fails
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded plugin
         */
        fun Append(rootUrl: String, sceneFilename: String?, scene: Scene?, onSuccess: ((scene: Scene) -> Unit)?, onProgress: ((event: SceneLoaderProgressEvent) -> Unit), onError: ((scene: Scene, message: String, exception: Any?) -> Unit), pluginExtension: String?): ISceneLoaderPlugin?  /*| ISceneLoaderPluginAsync */
        /**
         * Append a scene
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param scene is the instance of BABYLON.Scene to append to
         * @param onSuccess a callback with the scene when import succeeds
         * @param onProgress a callback with a progress event for each file being loaded
         * @param onError a callback with the scene, a message, and possibly an exception when import fails
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded plugin
         */
        fun Append(rootUrl: String, sceneFilename: File, scene: Scene?, onSuccess: ((scene: Scene) -> Unit)?, onProgress: ((event: SceneLoaderProgressEvent) -> Unit), onError: ((scene: Scene, message: String, exception: Any?) -> Unit), pluginExtension: String?): ISceneLoaderPlugin?  /*| ISceneLoaderPluginAsync */
        /**
         * Append a scene
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param scene is the instance of BABYLON.Scene to append to
         * @param onProgress a callback with a progress event for each file being loaded
         * @param pluginExtension the extension used to determine the plugin
         * @returns The given scene
         */
        fun AppendAsync(rootUrl: String, sceneFilename: String?, scene: Scene?, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, pluginExtension: String?): Promise<Scene>
        /**
         * Append a scene
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param scene is the instance of BABYLON.Scene to append to
         * @param onProgress a callback with a progress event for each file being loaded
         * @param pluginExtension the extension used to determine the plugin
         * @returns The given scene
         */
        fun AppendAsync(rootUrl: String, sceneFilename: File?, scene: Scene?, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, pluginExtension: String?): Promise<Scene>
        /**
         * Load a scene into an asset container
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param scene is the instance of BABYLON.Scene to append to (default: last created scene)
         * @param onSuccess a callback with the scene when import succeeds
         * @param onProgress a callback with a progress event for each file being loaded
         * @param onError a callback with the scene, a message, and possibly an exception when import fails
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded plugin
         */
        fun LoadAssetContainer(rootUrl: String, sceneFilename: String? /*| File*/, scene: Scene?, onSuccess: ((assets: AssetContainer) -> Unit)?, onProgress:((event: SceneLoaderProgressEvent) -> Unit)?, onError: ((scene: Scene, message: String, exception: Any?) -> Unit), pluginExtension: String?): ISceneLoaderPlugin? /* | ISceneLoaderPluginAsync */
        /**
         * Load a scene into an asset container
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene or a File object (default: empty String)
         * @param scene is the instance of BABYLON.Scene to append to (default: last created scene)
         * @param onSuccess a callback with the scene when import succeeds
         * @param onProgress a callback with a progress event for each file being loaded
         * @param onError a callback with the scene, a message, and possibly an exception when import fails
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded plugin
         */
        fun LoadAssetContainer(rootUrl: String, sceneFilename: File?, scene: Scene?, onSuccess: ((assets: AssetContainer) -> Unit)?, onProgress:((event: SceneLoaderProgressEvent) -> Unit)?, onError: ((scene: Scene, message: String, exception: Any?) -> Unit), pluginExtension: String?): ISceneLoaderPlugin? /* | ISceneLoaderPluginAsync */
        /**
         * Load a scene into an asset container
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene (default: empty String)
         * @param scene is the instance of Scene to append to
         * @param onProgress a callback with a progress event for each file being loaded
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded asset container
         */
        fun LoadAssetContainerAsync(rootUrl: String, sceneFilename: String?, scene: Scene?, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, pluginExtension: String?): Promise<AssetContainer>
        /**
         * Load a scene into an asset container
         * @param rootUrl a String that defines the root url for the scene and resources or the concatenation of rootURL and filename (e.g. [http://example.com/test.glb)]
         * @param sceneFilename a String that defines the name of the scene file or starts with "data:" following by the stringified version of the scene (default: empty String)
         * @param scene is the instance of Scene to append to
         * @param onProgress a callback with a progress event for each file being loaded
         * @param pluginExtension the extension used to determine the plugin
         * @returns The loaded asset container
         */
        fun LoadAssetContainerAsync(rootUrl: String, sceneFilename: File?, scene: Scene?, onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?, pluginExtension: String?): Promise<AssetContainer>
    }
}
