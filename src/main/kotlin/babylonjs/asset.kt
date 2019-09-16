@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals", "FunctionName")
package babylonjs

import org.khronos.webgl.ArrayBuffer
import org.w3c.dom.HTMLImageElement
import kotlin.js.Promise

/**
 * Defines the list of states available for a task inside a AssetsManager
 */
external enum class AssetTaskState {
    /**
     * Initialization
     */
    INIT /*= 0*/,
    /**
     * Running
     */
    RUNNING /*= 1*/,
    /**
     * Done
     */
    DONE /*= 2*/,
    /**
     * Error
     */
    ERROR /*= 3*/
}
/**
 * Define an abstract asset task used with a AssetsManager class to load assets into a scene
 */
abstract external class AbstractAssetTask {
    /**
     * Task name
     */
    var name: String
    /**
     * Callback called when the task is successful
     */
    var onSuccess: (task: Any) -> Unit
    /**
     * Callback called when the task is not successful
     */
    var onError: (task: Any, message: String?, exception: Any?) -> Unit
    /**
     * Creates a new AssetsManager
     * @param name defines the name of the task
     */
    constructor(
        /**
         * Task name
         */ name: String)
    /**
     * Get if the task is completed
     */
    val isCompleted: Boolean
    /**
     * Gets the current state of the task
     */
    val taskState: AssetTaskState
    /**
     * Gets the current error object (if task is in error)
     */
    val errorObject: ErrorObject
    /**
     * Execute the current task
     * @param scene defines the scene where you want your assets to be loaded
     * @param onSuccess is a callback called when the task is successfully executed
     * @param onError is a callback called if an error occurs
     */
    fun run(scene: Scene, onSuccess: () -> Unit, onError: (message: String?, exception: Any?) -> Unit)
    /**
     * Execute the current task
     * @param scene defines the scene where you want your assets to be loaded
     * @param onSuccess is a callback called when the task is successfully executed
     * @param onError is a callback called if an error occurs
     */
    fun runTask(scene: Scene, onSuccess: () -> Unit, onError: (message: String?, exception: Any?) -> Unit)
    /**
     * Reset will set the task state back to INIT, so the next load call of the assets manager will execute this task again.
     * This can be used with failed tasks that have the reason for failure fixed.
     */
    fun reset()
}
/**
 * Define the interface used by progress events raised during assets loading
 */
external interface IAssetsProgressEvent {
    /**
     * Defines the Number of remaining tasks to process
     */
    var remainingCount: Number
    /**
     * Defines the total Number of tasks
     */
    var totalCount: Number
    /**
     * Defines the task that was just processed
     */
    var task: AbstractAssetTask
}
/**
 * Class used to share progress information about assets loading
 */
external class AssetsProgressEvent: IAssetsProgressEvent {
    /**
     * Defines the Number of remaining tasks to process
     */
    override var remainingCount: Number
    /**
     * Defines the total Number of tasks
     */
    override var totalCount: Number
    /**
     * Defines the task that was just processed
     */
    override var task: AbstractAssetTask
    /**
     * Creates a AssetsProgressEvent
     * @param remainingCount defines the Number of remaining tasks to process
     * @param totalCount defines the total Number of tasks
     * @param task defines the task that was just processed
     */
    constructor(remainingCount: Number, totalCount: Number, task: AbstractAssetTask)
}
/**
 * Define a task used by AssetsManager to load meshes
 */
external class MeshAssetTask: AbstractAssetTask {
    /**
     * Defines the list of mesh's names you want to load
     */
    var meshesNames: Any
    /**
     * Defines the root url to use as a base to load your meshes and associated resources
     */
    var rootUrl: String
    /**
     * Defines the filename of the scene to load from
     */
    var sceneFilename: String
    /**
     * Gets the list of loaded meshes
     */
    var loadedMeshes: Array<AbstractMesh>
    /**
     * Gets the list of loaded particle systems
     */
    var loadedParticleSystems: Array<IParticleSystem>
    /**
     * Gets the list of loaded skeletons
     */
    var loadedSkeletons: Array<Skeleton>
    /**
     * Gets the list of loaded animation groups
     */
    var loadedAnimationGroups: Array<AnimationGroup>
    /**
     * Creates a new MeshAssetTask
     * @param name defines the name of the task
     * @param meshesNames defines the list of mesh's names you want to load
     * @param rootUrl defines the root url to use as a base to load your meshes and associated resources
     * @param sceneFilename defines the filename of the scene to load from
     */
    constructor(
        /**
         * Defines the name of the task
         */
        name: String,
        /**
         * Defines the list of mesh's names you want to load
         */
        meshesNames: Any,
        /**
         * Defines the root url to use as a base to load your meshes and associated resources
         */
        rootUrl: String,
        /**
         * Defines the filename of the scene to load from
         */
        sceneFilename: String)
}
/**
 * Define a task used by AssetsManager to load text content
 */
external class TextFileAssetTask: AbstractAssetTask {
    /**
     * Defines the location of the file to load
     */
    var url: String
    /**
     * Gets the loaded text String
     */
    var text: String
    /**
     * Creates a new TextFileAssetTask object
     * @param name defines the name of the task
     * @param url defines the location of the file to load
     */
    constructor(
        /**
         * Defines the name of the task
         */
        name: String,
        /**
         * Defines the location of the file to load
         */
        url: String)
}
/**
 * Define a task used by AssetsManager to load binary data
 */
external class BinaryFileAssetTask: AbstractAssetTask {
    /**
     * Defines the location of the file to load
     */
    var url: String
    /**
     * Gets the lodaded data (as an array buffer)
     */
    var data: ArrayBuffer
    /**
     * Creates a new BinaryFileAssetTask object
     * @param name defines the name of the new task
     * @param url defines the location of the file to load
     */
    constructor(
        /**
         * Defines the name of the task
         */
        name: String,
        /**
         * Defines the location of the file to load
         */
        url: String)
}
/**
 * Define a task used by AssetsManager to load images
 */
external class ImageAssetTask: AbstractAssetTask {
    /**
     * Defines the location of the image to load
     */
    var url: String
    /**
     * Gets the loaded images
     */
    var image: HTMLImageElement
    /**
     * Creates a new ImageAssetTask
     * @param name defines the name of the task
     * @param url defines the location of the image to load
     */
    constructor(
        /**
         * Defines the name of the task
         */
        name: String,
        /**
         * Defines the location of the image to load
         */
        url: String)
}
/**
 * Defines the interface used by texture loading tasks
 */
external interface ITextureAssetTask<TEX: BaseTexture> {
    /**
     * Gets the loaded texture
     */
    var texture: TEX
}
/**
 * Define a task used by AssetsManager to load 2D textures
 */
external class TextureAssetTask: AbstractAssetTask, ITextureAssetTask<Texture> {
    /**
     * Defines the location of the file to load
     */
    var url: String
    /**
     * Defines if mipmap should not be generated (default is false)
     */
    var noMipmap: Boolean?
    /**
     * Defines if texture must be inverted on Y axis (default is false)
     */
    var invertY: Boolean?
    /**
     * Defines the sampling mode to use (default is Texture.TRILINEAR_SAMPLINGMODE)
     */
    var samplingMode: Number
    /**
     * Gets the loaded texture
     */
    override var texture: Texture
    /**
     * Creates a new TextureAssetTask object
     * @param name defines the name of the task
     * @param url defines the location of the file to load
     * @param noMipmap defines if mipmap should not be generated (default is false)
     * @param invertY defines if texture must be inverted on Y axis (default is false)
     * @param samplingMode defines the sampling mode to use (default is Texture.TRILINEAR_SAMPLINGMODE)
     */
    constructor(
        /**
         * Defines the name of the task
         */
        name: String,
        /**
         * Defines the location of the file to load
         */
        url: String,
        /**
         * Defines if mipmap should not be generated (default is false)
         */
        noMipmap: Boolean?,
    /**
     * Defines if texture must be inverted on Y axis (default is false)
     */
    invertY: Boolean?,
    /**
     * Defines the sampling mode to use (default is Texture.TRILINEAR_SAMPLINGMODE)
     */
    samplingMode: Number?)
}
/**
 * Define a task used by AssetsManager to load cube textures
 */
external class CubeTextureAssetTask: AbstractAssetTask, ITextureAssetTask<CubeTexture> {
    /**
     * Defines the location of the files to load (You have to specify the folder where the files are + filename with no extension)
     */
    var url: String
    /**
     * Defines the extensions to use to load files (["_px", "_py", "_pz", "_nx", "_ny", "_nz"] by default)
     */
    var extensions: Array<String>?
    /**
     * Defines if mipmaps should not be generated (default is false)
     */
    var noMipmap: Boolean?
    /**
     * Defines the explicit list of files (undefined by default)
     */
    var files: Array<String>?
    /**
     * Gets the loaded texture
     */
    override var texture: CubeTexture
    /**
     * Creates a new CubeTextureAssetTask
     * @param name defines the name of the task
     * @param url defines the location of the files to load (You have to specify the folder where the files are + filename with no extension)
     * @param extensions defines the extensions to use to load files (["_px", "_py", "_pz", "_nx", "_ny", "_nz"] by default)
     * @param noMipmap defines if mipmaps should not be generated (default is false)
     * @param files defines the explicit list of files (undefined by default)
     */
    constructor(
        /**
         * Defines the name of the task
         */
        name: String,
        /**
         * Defines the location of the files to load (You have to specify the folder where the files are + filename with no extension)
         */
        url: String,
        /**
         * Defines the extensions to use to load files (["_px", "_py", "_pz", "_nx", "_ny", "_nz"] by default)
         */
        extensions: Array<String>?,
    /**
     * Defines if mipmaps should not be generated (default is false)
     */
    noMipmap: Boolean?,
    /**
     * Defines the explicit list of files (undefined by default)
     */
    files: Array<String>?)
}
/**
 * Define a task used by AssetsManager to load HDR cube textures
 */
external class HDRCubeTextureAssetTask: AbstractAssetTask, ITextureAssetTask<HDRCubeTexture> {
    /**
     * Defines the location of the file to load
     */
    var url: String
    /**
     * Defines the desired size (the more it increases the longer the generation will be)
     */
    var size: Number
    /**
     * Defines if mipmaps should not be generated (default is false)
     */
    var noMipmap: Boolean
    /**
     * Specifies whether you want to extract the polynomial harmonics during the generation process (default is true)
     */
    var generateHarmonics: Boolean
    /**
     * Specifies if the texture will be use in gamma or linear space (the PBR material requires those texture in linear space, but the standard material would require them in Gamma space) (default is false)
     */
    var gammaSpace: Boolean
    /**
     * Internal Use Only
     */
    var reserved: Boolean
    /**
     * Gets the loaded texture
     */
    override var texture: HDRCubeTexture
    /**
     * Creates a new HDRCubeTextureAssetTask object
     * @param name defines the name of the task
     * @param url defines the location of the file to load
     * @param size defines the desired size (the more it increases the longer the generation will be) If the size is omitted this implies you are using a preprocessed cubemap.
     * @param noMipmap defines if mipmaps should not be generated (default is false)
     * @param generateHarmonics specifies whether you want to extract the polynomial harmonics during the generation process (default is true)
     * @param gammaSpace specifies if the texture will be use in gamma or linear space (the PBR material requires those texture in linear space, but the standard material would require them in Gamma space) (default is false)
     * @param reserved Internal use only
     */
    constructor(
        /**
         * Defines the name of the task
         */
        name: String,
        /**
         * Defines the location of the file to load
         */
        url: String,
        /**
         * Defines the desired size (the more it increases the longer the generation will be)
         */
        size: Number,
        /**
         * Defines if mipmaps should not be generated (default is false)
         */
        noMipmap: Boolean?,
        /**
         * Specifies whether you want to extract the polynomial harmonics during the generation process (default is true)
         */
        generateHarmonics: Boolean?,
        /**
         * Specifies if the texture will be use in gamma or linear space (the PBR material requires those texture in linear space, but the standard material would require them in Gamma space) (default is false)
         */
        gammaSpace: Boolean?,
        /**
         * Internal Use Only
         */
        reserved: Boolean?)
}
/**
 * Define a task used by AssetsManager to load Equirectangular cube textures
 */
external class EquiRectangularCubeTextureAssetTask: AbstractAssetTask, ITextureAssetTask<EquiRectangularCubeTexture> {
    /**
     * Defines the location of the file to load
     */
    var url: String
    /**
     * Defines the desired size (the more it increases the longer the generation will be)
     */
    var size: Number
    /**
     * Defines if mipmaps should not be generated (default is false)
     */
    var noMipmap: Boolean
    /**
     * Specifies if the texture will be use in gamma or linear space (the PBR material requires those texture in linear space,
     * but the standard material would require them in Gamma space) (default is true)
     */
    var gammaSpace: Boolean
    /**
     * Gets the loaded texture
     */
    override var texture: EquiRectangularCubeTexture
    /**
     * Creates a new EquiRectangularCubeTextureAssetTask object
     * @param name defines the name of the task
     * @param url defines the location of the file to load
     * @param size defines the desired size (the more it increases the longer the generation will be)
     * If the size is omitted this implies you are using a preprocessed cubemap.
     * @param noMipmap defines if mipmaps should not be generated (default is false)
     * @param gammaSpace specifies if the texture will be used in gamma or linear space
     * (the PBR material requires those texture in linear space, but the standard material would require them in Gamma space)
     * (default is true)
     */
    constructor(
        /**
         * Defines the name of the task
         */
        name: String,
        /**
         * Defines the location of the file to load
         */
        url: String,
        /**
         * Defines the desired size (the more it increases the longer the generation will be)
         */
        size: Number,
        /**
         * Defines if mipmaps should not be generated (default is false)
         */
        noMipmap: Boolean?,
        /**
         * Specifies if the texture will be use in gamma or linear space (the PBR material requires those texture in linear space,
         * but the standard material would require them in Gamma space) (default is true)
         */
        gammaSpace: Boolean?)
}
/**
 * This class can be used to easily import assets into a scene
 * @see [http://doc.babylonjs.com/how_to/how_to_use_assetsmanager]
 */
external class AssetsManager {
    /**
     * Callback called when all tasks are processed
     */
    var onFinish: (tasks: Array<AbstractAssetTask>) -> Unit
    /**
     * Callback called when a task is successful
     */
    var onTaskSuccess: (task: AbstractAssetTask) -> Unit
    /**
     * Callback called when a task had an error
     */
    var onTaskError: (task: AbstractAssetTask) -> Unit
    /**
     * Callback called when a task is done (whatever the result is)
     */
    var onProgress: (remainingCount: Number, totalCount: Number, task: AbstractAssetTask) -> Unit
    /**
     * Observable called when all tasks are processed
     */
    var onTaskSuccessObservable: Observable<AbstractAssetTask>
    /**
     * Observable called when a task had an error
     */
    var onTaskErrorObservable: Observable<AbstractAssetTask>
    /**
     * Observable called when all tasks were executed
     */
    var onTasksDoneObservable: Observable<Array<AbstractAssetTask>>
    /**
     * Observable called when a task is done (whatever the result is)
     */
    var onProgressObservable: Observable<IAssetsProgressEvent>
    /**
     * Gets or sets a Boolean defining if the AssetsManager should use the default loading screen
     * @see [http://doc.babylonjs.com/how_to/creating_a_custom_loading_screen]
     */
    var useDefaultLoadingScreen: Boolean
    /**
     * Creates a new AssetsManager
     * @param scene defines the scene to work on
     */
    constructor(scene: Scene)
    /**
     * Add a MeshAssetTask to the list of active tasks
     * @param taskName defines the name of the new task
     * @param meshesNames defines the name of meshes to load
     * @param rootUrl defines the root url to use to locate files
     * @param sceneFilename defines the filename of the scene file
     * @returns a new MeshAssetTask object
     */
    fun addMeshTask(taskName: String, meshesNames: Any, rootUrl: String, sceneFilename: String): MeshAssetTask
    /**
     * Add a TextFileAssetTask to the list of active tasks
     * @param taskName defines the name of the new task
     * @param url defines the url of the file to load
     * @returns a new TextFileAssetTask object
     */
    fun addTextFileTask(taskName: String, url: String): TextFileAssetTask
    /**
     * Add a BinaryFileAssetTask to the list of active tasks
     * @param taskName defines the name of the new task
     * @param url defines the url of the file to load
     * @returns a new BinaryFileAssetTask object
     */
    fun addBinaryFileTask(taskName: String, url: String): BinaryFileAssetTask
    /**
     * Add a ImageAssetTask to the list of active tasks
     * @param taskName defines the name of the new task
     * @param url defines the url of the file to load
     * @returns a new ImageAssetTask object
     */
    fun addImageTask(taskName: String, url: String): ImageAssetTask
    /**
     * Add a TextureAssetTask to the list of active tasks
     * @param taskName defines the name of the new task
     * @param url defines the url of the file to load
     * @param noMipmap defines if the texture must not receive mipmaps (false by default)
     * @param invertY defines if you want to invert Y axis of the loaded texture (false by default)
     * @param samplingMode defines the sampling mode to use (Texture.TRILINEAR_SAMPLINGMODE by default)
     * @returns a new TextureAssetTask object
     */
    fun addTextureTask(taskName: String, url: String, noMipmap: Boolean?, invertY: Boolean?, samplingMode: Number?): TextureAssetTask
    /**
     * Add a CubeTextureAssetTask to the list of active tasks
     * @param taskName defines the name of the new task
     * @param url defines the url of the file to load
     * @param extensions defines the extension to use to load the cube map (can be null)
     * @param noMipmap defines if the texture must not receive mipmaps (false by default)
     * @param files defines the list of files to load (can be null)
     * @returns a new CubeTextureAssetTask object
     */
    fun addCubeTextureTask(taskName: String, url: String, extensions: Array<String>?, noMipmap: Boolean?, files: Array<String>?): CubeTextureAssetTask
    /**
     *
     * Add a HDRCubeTextureAssetTask to the list of active tasks
     * @param taskName defines the name of the new task
     * @param url defines the url of the file to load
     * @param size defines the size you want for the cubemap (can be null)
     * @param noMipmap defines if the texture must not receive mipmaps (false by default)
     * @param generateHarmonics defines if you want to automatically generate (true by default)
     * @param gammaSpace specifies if the texture will be use in gamma or linear space (the PBR material requires those texture in linear space, but the standard material would require them in Gamma space) (default is false)
     * @param reserved Internal use only
     * @returns a new HDRCubeTextureAssetTask object
     */
    fun addHDRCubeTextureTask(taskName: String, url: String, size: Number, noMipmap: Boolean?, generateHarmonics: Boolean?, gammaSpace: Boolean?, reserved: Boolean?): HDRCubeTextureAssetTask
    /**
     *
     * Add a EquiRectangularCubeTextureAssetTask to the list of active tasks
     * @param taskName defines the name of the new task
     * @param url defines the url of the file to load
     * @param size defines the size you want for the cubemap (can be null)
     * @param noMipmap defines if the texture must not receive mipmaps (false by default)
     * @param gammaSpace Specifies if the texture will be used in gamma or linear space
     * (the PBR material requires those textures in linear space, but the standard material would require them in Gamma space)
     * @returns a new EquiRectangularCubeTextureAssetTask object
     */
    fun addEquiRectangularCubeTextureAssetTask(taskName: String, url: String, size: Number, noMipmap: Boolean?, gammaSpace: Boolean?): EquiRectangularCubeTextureAssetTask
    /**
     * Remove a task from the assets manager.
     * @param task the task to remove
     */
    fun removeTask(task: AbstractAssetTask)
    /**
     * Reset the AssetsManager and remove all tasks
     * @return the current instance of the AssetsManager
     */
    fun reset(): AssetsManager
    /**
     * Start the loading process
     * @return the current instance of the AssetsManager
     */
    fun load(): AssetsManager
    /**
     * Start the loading process as an async operation
     * @return a promise returning the list of failed tasks
     */
    fun loadAsync(): Promise<Unit>
}

/**
 * Set of assets to keep when moving a scene into an asset container.
 */
external class KeepAssets: AbstractScene

/**
 * Container with a set of assets that can be added or removed from a scene.
 */
external class AssetContainer: AbstractScene {
    /**
     * The scene the AssetContainer belongs to.
     */
    var scene: Scene
    /**
     * Instantiates an AssetContainer.
     * @param scene The scene the AssetContainer belongs to.
     */
    constructor(scene: Scene)
    /**
     * Adds all the assets from the container to the scene.
     */
    fun addAllToScene()
    /**
     * Removes all the assets in the container from the scene
     */
    fun removeAllFromScene()
    /**
     * Disposes all the assets in the container
     */
    fun dispose()
    /**
     * Removes all the assets contained in the scene and adds them to the container.
     * @param keepAssets Set of assets to keep in the scene. (default: empty)
     */
    fun moveAllFromScene(keepAssets: KeepAssets?)
    /**
     * Adds all meshes in the asset container to a root mesh that can be used to position all the contained meshes. The root mesh is then added to the front of the meshes in the assetContainer.
     * @returns the root mesh
     */
    fun createRootMesh(): Mesh
}
