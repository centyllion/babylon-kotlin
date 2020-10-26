@file:JsModule("babylonjs-loaders")
@file:JsNonModule
package babylonjs.loaders

import babylonjs.AssetContainer
import babylonjs.IDisposable
import babylonjs.ISceneLoaderPlugin
import babylonjs.ISceneLoaderPluginAsync
import babylonjs.ISceneLoaderPluginFactory
import babylonjs.ImportedMesh
import babylonjs.Scene
import babylonjs.SceneLoaderProgressEvent
import kotlin.js.Promise

external class GLTFFileLoader: IDisposable, ISceneLoaderPluginAsync, ISceneLoaderPluginFactory {

    override fun createPlugin(): ISceneLoaderPlugin

    override val name: String
    override var canDirectLoad: ((data: String) -> Boolean)?
    override var extensions: String
    override var rewriteRootURL: ((rootUrl: String, responseURL: String?) -> String)?

    override fun importMeshAsync(
        meshesNames: Any,
        scene: Scene,
        data: Any,
        rootUrl: String,
        onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?,
        fileName: String?
    ): Promise<ImportedMesh>

    override fun loadAsync(
        scene: Scene,
        data: String,
        rootUrl: String,
        onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?,
        fileName: String?
    ): Promise<Unit>

    override fun loadAssetContainerAsync(
        scene: Scene,
        data: String,
        rootUrl: String,
        onProgress: ((event: SceneLoaderProgressEvent) -> Unit)?,
        fileName: String?
    ): Promise<AssetContainer>

    override fun dispose()
}
