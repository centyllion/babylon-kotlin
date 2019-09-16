@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals")
package babylonjs

import org.w3c.dom.pointerevents.PointerEvent
import kotlin.js.Promise
import kotlin.js.RegExp

/**
 * Define an interface for all classes that will hold resources
 */
external interface IDisposable {
    /**
     * Releases all held resources
     */
    fun dispose()
}

open external class AbstractScene {
    /**
     * The list of sounds used in the scene.
     */
    var sounds: Array<Sound>?
}

external class Scene: AbstractScene, IAnimatable {

    override var animations: Array<Animation>?

    /** Define this parameter if you are using multiple cameras and you want to specify which one should be used for pointer position */
    var cameraToUseForPointers: Camera?
    /**
     * Gets or sets a Boolean that indicates if the scene must clear the render buffer before rendering a frame
     */
    var autoClear: Boolean
    /**
     * Gets or sets a Boolean that indicates if the scene must clear the depth and stencil buffers before rendering a frame
     */
    var autoClearDepthAndStencil: Boolean
    /**
     * Defines the color used to clear the render buffer (Default is (0.2, 0.2, 0.3, 1.0))
     */
    var clearColor: Color4
    /**
     * Defines the color used to simulate the ambient color (Default is (0, 0, 0))
     */
    var ambientColor: Color3
    /**
     * This is use to store the default BRDF lookup for PBR materials in your scene.
     * It should only be one of the following (if not the default embedded one):
     * * For uncorrelated BRDF (pbr.brdf.useEnergyConservation = false and pbr.brdf.useSmithVisibilityHeightCorrelated = false) : [assets.babylonjs.com/environments/uncorrelatedBRDF.dds]
     * * For correlated BRDF (pbr.brdf.useEnergyConservation = false and pbr.brdf.useSmithVisibilityHeightCorrelated = true) : [assets.babylonjs.com/environments/correlatedBRDF.dds]
     * * For correlated multi scattering BRDF (pbr.brdf.useEnergyConservation = true and pbr.brdf.useSmithVisibilityHeightCorrelated = true) : [assets.babylonjs.com/environments/correlatedMSBRDF.dds]
     * The material properties need to be setup according to the type of texture in use.
     */
    var environmentBRDFTexture: BaseTexture
    /**
     * Texture used in all pbr material as the reflection texture.
     * As in the majority of the scene they are the same (exception for multi room and so on),
     * this is easier to reference from here than from all the materials.
     */
    /**
     * Texture used in all pbr material as the reflection texture.
     * As in the majority of the scene they are the same (exception for multi room and so on),
     * this is easier to set here than in all the materials.
     */
    var environmentTexture: BaseTexture
    /**
     * Default image processing configuration used either in the rendering
     * Forward main pass or through the imageProcessingPostProcess if present.
     * As in the majority of the scene they are the same (exception for multi camera),
     * this is easier to reference from here than from all the materials and post process.
     *
     * No setter as we it is a shared configuration, you can set the values instead.
     */
    val imageProcessingConfiguration: ImageProcessingConfiguration
    /**
     * Gets or sets a Boolean indicating if all rendering must be done in wireframe
     */
    var forceWireframe: Boolean
    /**
     * Gets or sets a Boolean indicating if all rendering must be done in point cloud
     */
    var forcePointsCloud: Boolean
    /**
     * Gets or sets the active clipplane 1
     */
    var clipPlane: Plane
    /**
     * Gets or sets the active clipplane 2
     */
    var clipPlane2: Plane
    /**
     * Gets or sets the active clipplane 3
     */
    var clipPlane3: Plane
    /**
     * Gets or sets the active clipplane 4
     */
    var clipPlane4: Plane
    /**
     * Gets or sets a Boolean indicating if animations are enabled
     */
    var animationsEnabled: Boolean
    /**
     * Gets or sets the animation properties override
     */
    var animationPropertiesOverride: AnimationPropertiesOverride
    /**
     * Gets or sets a Boolean indicating if a constant deltatime has to be used
     * This is mostly useful for testing purposes when you do not want the animations to scale with the framerate
     */
    var useConstantAnimationDeltaTime: Boolean
    /**
     * Gets or sets a Boolean indicating if the scene must keep the meshUnderPointer property updated
     * Please note that it requires to run a ray cast through the scene on every frame
     */
    var constantlyUpdateMeshUnderPointer: Boolean
    /**
     * Defines the HTML cursor to use when hovering over interactive elements
     */
    var hoverCursor: String
    /**
     * Defines the HTML default cursor to use (empty by default)
     */
    var defaultCursor: String
    /**
     * This is used to call preventDefault() on pointer down
     * in order to block unwanted artifacts like system double clicks
     */
    var preventDefaultOnPointerDown: Boolean
    /**
     * This is used to call preventDefault() on pointer up
     * in order to block unwanted artifacts like system double clicks
     */
    var preventDefaultOnPointerUp: Boolean
    /**
     * Gets or sets user defined metadata
     */
    var metadata: Any
    /**
     * For internal use only. Please do not use.
     */
    var reservedDataStore: Any
    /**
     * Gets the name of the plugin used to load this scene (null by default)
     */
    var loadingPluginName: String
    /**
     * Use this array to add regular expressions used to disable offline support for specific urls
     */
    fun disableOfflineSupportExceptionRules(a: Array<RegExp>)
    /**
     * An event triggered when the scene is disposed.
     */
    fun onDisposeObservable(obs: Observable<Scene>)
    /** Sets a function to be executed when this scene is disposed. */
    var onDispose: () -> Unit
    /**
     * An event triggered before rendering the scene (right after animations and physics)
     */
    fun onBeforeRenderObservable(obs: Observable<Scene>)
    /** Sets a function to be executed before rendering this scene */
    var beforeRender: (() -> Unit)?
    /**
     * An event triggered after rendering the scene
     */
    fun onAfterRenderObservable(obs: Observable<Scene>)
    /** Sets a function to be executed after rendering this scene */
    var afterRender: (() -> Unit)?
    /**
     * An event triggered before animating the scene
     */
    fun onBeforeAnimationsObservable(obs: Observable<Scene>)
    /**
     * An event triggered after animations processing
     */
    fun onAfterAnimationsObservable(obs: Observable<Scene>)
    /**
     * An event triggered before draw calls are ready to be sent
     */
    fun onBeforeDrawPhaseObservable(obs: Observable<Scene>)
    /**
     * An event triggered after draw calls have been sent
     */
    fun onAfterDrawPhaseObservable(obs: Observable<Scene>)
    /**
     * An event triggered when the scene is ready
     */
    fun onReadyObservable(obs: Observable<Scene>)
    /**
     * An event triggered before rendering a camera
     */
    fun onBeforeCameraRenderObservable(obs: Observable<Camera>)
    /** Sets a function to be executed before rendering a camera*/
    var beforeCameraRender: () -> Unit
    /**
     * An event triggered after rendering a camera
     */
    fun onAfterCameraRenderObservable(obs: Observable<Camera>)
    /** Sets a function to be executed after rendering a camera*/
    var afterCameraRender: () -> Unit
    /**
     * An event triggered when active meshes evaluation is about to start
     */
    fun onBeforeActiveMeshesEvaluationObservable(obs: Observable<Scene>)
    /**
     * An event triggered when active meshes evaluation is done
     */
    fun onAfterActiveMeshesEvaluationObservable(obs: Observable<Scene>)
    /**
     * An event triggered when particles rendering is about to start
     * Note: This event can be trigger more than once per frame (because particles can be rendered by render target textures as well)
     */
    fun onBeforeParticlesRenderingObservable(obs: Observable<Scene>)
    /**
     * An event triggered when particles rendering is done
     * Note: This event can be trigger more than once per frame (because particles can be rendered by render target textures as well)
     */
    fun onAfterParticlesRenderingObservable(obs: Observable<Scene>)
    /**
     * An event triggered when SceneLoader.Append or SceneLoader.Load or SceneLoader.ImportMesh were successfully executed
     */
    fun onDataLoadedObservable(obs: Observable<Scene>)
    /**
     * An event triggered when a camera is created
     */
    fun onNewCameraAddedObservable(obs: Observable<Camera>)
    /**
     * An event triggered when a camera is removed
     */
    fun onCameraRemovedObservable(obs: Observable<Camera>)
    /**
     * An event triggered when a light is created
     */
    fun onNewLightAddedObservable(obs: Observable<Light>)
    /**
     * An event triggered when a light is removed
     */
    fun onLightRemovedObservable(obs: Observable<Light>)
    /**
     * An event triggered when a geometry is created
     */
    fun onNewGeometryAddedObservable(obs: Observable<Geometry>)
    /**
     * An event triggered when a geometry is removed
     */
    fun onGeometryRemovedObservable(obs: Observable<Geometry>)
    /**
     * An event triggered when a transform node is created
     */
    fun onNewTransformNodeAddedObservable(obs: Observable<TransformNode>)
    /**
     * An event triggered when a transform node is removed
     */
    fun onTransformNodeRemovedObservable(obs: Observable<TransformNode>)
    /**
     * An event triggered when a mesh is created
     */
    fun onNewMeshAddedObservable(obs: Observable<AbstractMesh>)
    /**
     * An event triggered when a mesh is removed
     */
    fun onMeshRemovedObservable(obs: Observable<AbstractMesh>)
    /**
     * An event triggered when a skeleton is created
     */
    fun onNewSkeletonAddedObservable(obs: Observable<Skeleton>)
    /**
     * An event triggered when a skeleton is removed
     */
    fun onSkeletonRemovedObservable(obs: Observable<Skeleton>)
    /**
     * An event triggered when a material is created
     */
    fun onNewMaterialAddedObservable(obs: Observable<Material>)
    /**
     * An event triggered when a material is removed
     */
    fun onMaterialRemovedObservable(obs: Observable<Material>)
    /**
     * An event triggered when a texture is created
     */
    fun onNewTextureAddedObservable(obs: Observable<BaseTexture>)
    /**
     * An event triggered when a texture is removed
     */
    fun onTextureRemovedObservable(obs: Observable<BaseTexture>)
    /**
     * An event triggered when render targets are about to be rendered
     * Can happen multiple times per frame.
     */
    fun onBeforeRenderTargetsRenderObservable(obs: Observable<Scene>)
    /**
     * An event triggered when render targets were rendered.
     * Can happen multiple times per frame.
     */
    fun onAfterRenderTargetsRenderObservable(obs: Observable<Scene>)
    /**
     * An event triggered before calculating deterministic simulation step
     */
    fun onBeforeStepObservable(obs: Observable<Scene>)
    /**
     * An event triggered after calculating deterministic simulation step
     */
    fun onAfterStepObservable(obs: Observable<Scene>)
    /**
     * An event triggered when the activeCamera property is updated
     */
    fun onActiveCameraChanged(obs: Observable<Scene>)
    /**
     * This Observable will be triggered before rendering each renderingGroup of each rendered camera.
     * The RenderinGroupInfo class contains all the information about the context in which the observable is called
     * If you wish to register an Observer only for a given set of renderingGroup, use the mask with a combination of the renderingGroup index elevated to the power of two (1 for renderingGroup 0, 2 for renderingrOup1, 4 for 2 and 8 for 3)
     */
    fun onBeforeRenderingGroupObservable(obs: Observable<RenderingGroupInfo>)
    /**
     * This Observable will be triggered after rendering each renderingGroup of each rendered camera.
     * The RenderinGroupInfo class contains all the information about the context in which the observable is called
     * If you wish to register an Observer only for a given set of renderingGroup, use the mask with a combination of the renderingGroup index elevated to the power of two (1 for renderingGroup 0, 2 for renderingrOup1, 4 for 2 and 8 for 3)
     */
    fun onAfterRenderingGroupObservable(obs: Observable<RenderingGroupInfo>)
    /**
     * This Observable will when a mesh has been imported into the scene.
     */
    fun onMeshImportedObservable(obs: Observable<AbstractMesh>)
    /**
     * Gets or sets a user defined funtion to select LOD from a mesh and a camera.
     * By default this function is undefined and Babylon.js will select LOD based on distance to camera
     */
    var customLODSelector: (mesh: AbstractMesh, camera: Camera) -> AbstractMesh?
    /**
     * Gets or sets a predicate used to select candidate meshes for a pointer down event
     */
    var pointerDownPredicate: (Mesh: AbstractMesh) -> Boolean
    /**
     * Gets or sets a predicate used to select candidate meshes for a pointer up event
     */
    var pointerUpPredicate: (Mesh: AbstractMesh) -> Boolean
    /**
     * Gets or sets a predicate used to select candidate meshes for a pointer move event
     */
    var pointerMovePredicate: (Mesh: AbstractMesh) -> Boolean
    /** Callback called when a pointer move is detected */
    var onPointerMove: (evt: PointerEvent, pickInfo: PickingInfo, type: PointerEventTypes) -> Unit
    /** Callback called when a pointer down is detected  */
    var onPointerDown: (evt: PointerEvent, pickInfo: PickingInfo, type: PointerEventTypes) -> Unit
    /** Callback called when a pointer up is detected  */
    var onPointerUp: (evt: PointerEvent, pickInfo: PickingInfo?, type: PointerEventTypes) -> Unit
    /** Callback called when a pointer pick is detected */
    var onPointerPick: (evt: PointerEvent, pickInfo: PickingInfo) -> Unit
    /**
     * This observable event is triggered when Any pointer event is triggered. It is registered during Scene.attachControl() and it is called BEFORE the 3D engine process anything (mesh/sprite picking for instance).
     * You have the possibility to skip the process and the call to onPointerObservable by setting PointerInfoPre.skipOnPointerObservable to true
     */
    fun onPrePointerObservable(obs: Observable<PointerInfoPre>)
    /**
     * Observable event triggered each time an input event is received from the rendering canvas
     */
    fun onPointerObservable(obs: Observable<PointerInfo>)
    /**
     * Gets the pointer coordinates without Any translation (ie. straight out of the pointer event)
     */
    val unTranslatedPointer: Vector2
    /**
     * This observable event is triggered when Any keyboard event si raised and registered during Scene.attachControl()
     * You have the possibility to skip the process and the call to onKeyboardObservable by setting KeyboardInfoPre.skipOnPointerObservable to true
     */
    fun onPreKeyboardObservable(obs: Observable<KeyboardInfoPre>)
    /**
     * Observable event triggered each time an keyboard event is received from the hosting window
     */
    fun onKeyboardObservable(obs: Observable<KeyboardInfo>)
    /**
     * Gets or sets a Boolean indicating if the scene must use right-handed coordinates system
     */
    var useRightHandedSystem: Boolean
    /**
     * Sets the step Id used by deterministic lock step
     * @see [doc.babylonjs.com/babylon101/animations#deterministic-lockstep]
     * @param newStepId defines the step Id
     */
    fun setStepId(newStepId: Number)
    /**
     * Gets the step Id used by deterministic lock step
     * @see [doc.babylonjs.com/babylon101/animations#deterministic-lockstep]
     * @returns the step Id
     */
    fun getStepId(): Number
    /**
     * Gets the internal step used by deterministic lock step
     * @see [doc.babylonjs.com/babylon101/animations#deterministic-lockstep]
     * @returns the internal step
     */
    fun getInternalStep(): Number
    /**
     * Gets or sets a Boolean indicating if fog is enabled on this scene
     * @see [doc.babylonjs.com/babylon101/environment#fog]
     * (Default is true)
     */
    var fogEnabled: Boolean
    /**
     * Gets or sets the fog mode to use
     * @see [doc.babylonjs.com/babylon101/environment#fog]
     * | mode | value |
     * | --- | --- |
     * | FOGMODE_NONE | 0 |
     * | FOGMODE_EXP | 1 |
     * | FOGMODE_EXP2 | 2 |
     * | FOGMODE_LINEAR | 3 |
     */
    var fogMode: Number
    /**
     * Gets or sets the fog color to use
     * @see [doc.babylonjs.com/babylon101/environment#fog]
     * (Default is Color3(0.2, 0.2, 0.3))
     */
    var fogColor: Color3
    /**
     * Gets or sets the fog density to use
     * @see [doc.babylonjs.com/babylon101/environment#fog]
     * (Default is 0.1)
     */
    var fogDensity: Number
    /**
     * Gets or sets the fog start distance to use
     * @see [doc.babylonjs.com/babylon101/environment#fog]
     * (Default is 0)
     */
    var fogStart: Number
    /**
     * Gets or sets the fog end distance to use
     * @see [doc.babylonjs.com/babylon101/environment#fog]
     * (Default is 1000)
     */
    var fogEnd: Number
    /**
     * Gets or sets a Boolean indicating if shadows are enabled on this scene
     */
    var shadowsEnabled: Boolean
    /**
     * Gets or sets a Boolean indicating if lights are enabled on this scene
     */
    var lightsEnabled: Boolean
    /** All of the active cameras added to this scene. */
    fun activeCameras(cameras: Array<Camera>)
    /** Gets or sets the current active camera */
    var activeCamera: Camera
    /** The default material used on meshes when no material is affected */
    /** The default material used on meshes when no material is affected */
    var defaultMaterial: Material
    /**
     * Gets or sets a Boolean indicating if textures are enabled on this scene
     */
    var texturesEnabled: Boolean
    /**
     * Gets or sets a Boolean indicating if particles are enabled on this scene
     */
    var particlesEnabled: Boolean
    /**
     * Gets or sets a Boolean indicating if sprites are enabled on this scene
     */
    var spritesEnabled: Boolean
    /**
     * Gets or sets a Boolean indicating if skeletons are enabled on this scene
     */
    var skeletonsEnabled: Boolean
    /**
     * Gets or sets a Boolean indicating if lens flares are enabled on this scene
     */
    var lensFlaresEnabled: Boolean
    /**
     * Gets or sets a Boolean indicating if collisions are enabled on this scene
     * @see [doc.babylonjs.com/babylon101/cameras,_mesh_collisions_and_gravity]
     */
    var collisionsEnabled: Boolean
    /**
     * Defines the gravity applied to this scene (used only for collisions)
     * @see [doc.babylonjs.com/babylon101/cameras,_mesh_collisions_and_gravity]
     */
    var gravity: Vector3
    /**
     * Gets or sets a Boolean indicating if postprocesses are enabled on this scene
     */
    var postProcessesEnabled: Boolean
    /**
     * The list of postprocesses added to the scene
     */
    fun postProcesses(a: Array<PostProcess>)
    /**
     * Gets the current postprocess manager
     */
    var postProcessManager: PostProcessManager
    /**
     * Gets or sets a Boolean indicating if render targets are enabled on this scene
     */
    var renderTargetsEnabled: Boolean
    /**
     * Gets or sets a Boolean indicating if next render targets must be dumped as image for debugging purposes
     * We recommend not using it and instead rely on Spector.js: [http://spector.babylonjs.com]
     */
    var dumpNextRenderTargets: Boolean
    /**
     * The list of user defined render targets added to the scene
     */
    fun customRenderTargets(a: Array<RenderTargetTexture>)
    /**
     * Defines if texture loading must be delayed
     * If true, textures will only be loaded when they need to be rendered
     */
    var useDelayedTextureLoading: Boolean
    /**
     * Gets the list of meshes imported to the scene through SceneLoader
     */
    fun importedMeshesFiles(a: Array<String>)
    /**
     * Gets or sets a Boolean indicating if probes are enabled on this scene
     */
    var probesEnabled: Boolean
    /**
     * Gets or sets the current offline provider to use to store scene data
     * @see [doc.babylonjs.com/how_to/caching_resources_in_indexeddb]
     */
    var offlineProvider: IOfflineProvider
    /**
     * Gets or sets the action manager associated with the scene
     * @see [doc.babylonjs.com/how_to/how_to_use_actions]
     */
    var actionManager: AbstractActionManager
    /**
     * Gets or sets a Boolean indicating if procedural textures are enabled on this scene
     */
    var proceduralTexturesEnabled: Boolean

    /**
     * Gets or sets a general scale for animation speed
     * @see [www.babylonjs-playground.com/#IBU2W7#3]
     */
    var animationTimeScale: Number

    /**
     * Gets or sets a Boolean indicating that all submeshes of active meshes must be rendered
     * Use this Boolean to avoid computing frustum clipping on submeshes (This could help when you are CPU bound)
     */
    var dispatchAllSubMeshesOfActiveMeshes: Boolean

    /**
     * Gets the list of frustum planes (built from the active camera)
     */
    val frustumPlanes: Array<Plane>
    /**
     * Gets or sets a Boolean indicating if lights must be sorted by priority (off by default)
     * This is useful if there are more lights that the maximum simulteanous authorized
     */
    var requireLightSorting: Boolean

    /**
     * an optional map from Geometry Id to Geometry index in the 'geometries' array
     */
    /**
     * Creates a new Scene
     * @param engine defines the engine to use to render this scene
     * @param options defines the scene options
     */
    constructor(engine: Engine, options: SceneOptions? = definedExternally)

    /**
     * Gets a String idenfifying the name of the class
     * @returns "Scene" String
     */
    fun getClassName(): String
    /**
     * Sets the default candidate providers for the scene.
     * This sets the getActiveMeshCandidates, getActiveSubMeshCandidates, getIntersectingSubMeshCandidates
     * and getCollidingSubMeshCandidates to their default function
     */
    fun setDefaultCandidateProviders()
    /**
     * Gets the mesh that is currently under the pointer
     */
    val meshUnderPointer: AbstractMesh?
    /**
     * Gets or sets the current on-screen X position of the pointer
     */
    var pointerX: Number
    /**
     * Gets or sets the current on-screen Y position of the pointer
     */
    var pointerY: Number
    /**
     * Gets the cached material (ie. the latest rendered one)
     * @returns the cached material
     */
    fun getCachedMaterial(): Material?
    /**
     * Gets the cached effect (ie. the latest rendered one)
     * @returns the cached effect
     */
    fun getCachedEffect(): Effect?
    /**
     * Gets the cached visibility state (ie. the latest rendered one)
     * @returns the cached visibility state
     */
    fun getCachedVisibility(): Number?
    /**
     * Gets a Boolean indicating if the current material / effect / visibility must be bind again
     * @param material defines the current material
     * @param effect defines the current effect
     * @param visibility defines the current visibility state
     * @returns true if one parameter is not cached
     */
    fun isCachedMaterialInvalid(material: Material, effect: Effect, visibility: Number?): Boolean
    /**
     * Gets the engine associated with the scene
     * @returns an Engine
     */
    fun getEngine(): Engine
    /**
     * Gets the total Number of vertices rendered per frame
     * @returns the total Number of vertices rendered per frame
     */
    fun getTotalVertices(): Number
    /**
     * Gets the performance counter for total vertices
     * @see [doc.babylonjs.com/how_to/optimizing_your_scene#instrumentation]
     */
    val totalVerticesPerfCounter: PerfCounter
    /**
     * Gets the total Number of active indices rendered per frame (You can deduce the Number of rendered triangles by dividing this Number by 3)
     * @returns the total Number of active indices rendered per frame
     */
    fun getActiveIndices(): Number
    /**
     * Gets the performance counter for active indices
     * @see [doc.babylonjs.com/how_to/optimizing_your_scene#instrumentation]
     */
    val totalActiveIndicesPerfCounter: PerfCounter
    /**
     * Gets the total Number of active particles rendered per frame
     * @returns the total Number of active particles rendered per frame
     */
    fun getActiveParticles(): Number
    /**
     * Gets the performance counter for active particles
     * @see [doc.babylonjs.com/how_to/optimizing_your_scene#instrumentation]
     */
    val activeParticlesPerfCounter: PerfCounter
    /**
     * Gets the total Number of active bones rendered per frame
     * @returns the total Number of active bones rendered per frame
     */
    fun getActiveBones(): Number
    /**
     * Gets the performance counter for active bones
     * @see [doc.babylonjs.com/how_to/optimizing_your_scene#instrumentation]
     */
    val activeBonesPerfCounter: PerfCounter
    /**
     * Gets the array of active meshes
     * @returns an array of AbstractMesh
     */
    fun getActiveMeshes(): SmartArray<AbstractMesh>
    /**
     * Gets the animation ratio (which is 1.0 is the scene renders at 60fps and 2 if the scene renders at 30fps, etc.)
     * @returns a Number
     */
    fun getAnimationRatio(): Number
    /**
     * Gets an unique Id for the current render phase
     * @returns a Number
     */
    fun getRenderId(): Number
    /**
     * Gets an unique Id for the current frame
     * @returns a Number
     */
    fun getFrameId(): Number
    /** Call this function if you want to manually increment the render Id*/
    fun incrementRenderId()
    /**
     * Use this method to simulate a pointer move on a mesh
     * The pickResult parameter can be obtained from a scene.pick or scene.pickWithRay
     * @param pickResult pickingInfo of the object wished to simulate pointer event on
     * @param pointerEventInit pointer event state to be used when simulating the pointer event (eg. pointer id for multitouch)
     * @returns the current scene
     */
    fun simulatePointerMove(pickResult: PickingInfo, pointerEventInit: PointerEventInit?): Scene
    /**
     * Use this method to simulate a pointer down on a mesh
     * The pickResult parameter can be obtained from a scene.pick or scene.pickWithRay
     * @param pickResult pickingInfo of the object wished to simulate pointer event on
     * @param pointerEventInit pointer event state to be used when simulating the pointer event (eg. pointer id for multitouch)
     * @returns the current scene
     */
    fun simulatePointerDown(pickResult: PickingInfo, pointerEventInit: PointerEventInit?): Scene
    /**
     * Use this method to simulate a pointer up on a mesh
     * The pickResult parameter can be obtained from a scene.pick or scene.pickWithRay
     * @param pickResult pickingInfo of the object wished to simulate pointer event on
     * @param pointerEventInit pointer event state to be used when simulating the pointer event (eg. pointer id for multitouch)
     * @param doubleTap indicates that the pointer up event should be considered as part of a double click (false by default)
     * @returns the current scene
     */
    fun simulatePointerUp(pickResult: PickingInfo, pointerEventInit: PointerEventInit?, doubleTap: Boolean?): Scene
    /**
     * Gets a Boolean indicating if the current pointer event is captured (meaning that the scene has already handled the pointer down)
     * @param pointerId defines the pointer id to use in a multi-touch scenario (0 by default)
     * @returns true if the pointer was captured
     */
    fun isPointerCaptured(pointerId: Number?): Boolean
    /**
     * Attach events to the canvas (To handle actionManagers triggers and raise onPointerMove, onPointerDown and onPointerUp
     * @param attachUp defines if you want to attach events to pointerup
     * @param attachDown defines if you want to attach events to pointerdown
     * @param attachMove defines if you want to attach events to pointermove
     */
    fun attachControl(attachUp: Boolean?, attachDown: Boolean?, attachMove: Boolean?)
    /** Detaches all event handlers*/
    fun detachControl()
    /**
     * This function will check if the scene can be rendered (textures are loaded, shaders are compiled)
     * Delay loaded resources are not taking in account
     * @return true if all required resources are ready
     */
    fun isReady(): Boolean
    /** Resets all cached information relative to material (including effect and visibility) */
    fun resetCachedMaterial()
    /**
     * Registers a function to be called before every frame render
     * @param func defines the function to register
     */
    fun registerBeforeRender(func: () -> Unit)
    /**
     * Unregisters a function called before every frame render
     * @param func defines the function to unregister
     */
    fun unregisterBeforeRender(func: () -> Unit)
    /**
     * Registers a function to be called after every frame render
     * @param func defines the function to register
     */
    fun registerAfterRender(func: () -> Unit)
    /**
     * Unregisters a function called after every frame render
     * @param func defines the function to unregister
     */
    fun unregisterAfterRender(func: () -> Unit)
    /**
     * The provided function will run before render once and will be disposed afterwards.
     * A timeout delay can be provided so that the function will be executed in N ms.
     * The timeout is using the browser's native setTimeout so time precision cannot be guaranteed.
     * @param func The function to be executed.
     * @param timeout optional delay in ms
     */
    fun executeOnceBeforeRender(func: () -> Unit, timeout: Number? = definedExternally)
    /**
     * Returns the Number of items waiting to be loaded
     * @returns the Number of items waiting to be loaded
     */
    fun getWaitingItemsCount(): Number
    /**
     * Returns a Boolean indicating if the scene is still loading data
     */
    val isLoading: Boolean
    /**
     * Registers a function to be executed when the scene is ready
     * @param {Function} func - the function to be executed
     */
    fun executeWhenReady(func: () -> Unit)
    /**
     * Returns a promise that resolves when the scene is ready
     * @returns A promise that resolves when the scene is ready
     */
    fun whenReadyAsync(): Promise<Unit>
    /**
     * Gets all animatable attached to the scene
     */
    val animatables: Array<Animatable>
    /**
     * Resets the last animation time frame.
     * Useful to override when animations start running when loading a scene for the first time.
     */
    fun resetLastAnimationTimeFrame()
    /**
     * Gets the current view matrix
     * @returns a Matrix
     */
    fun getViewMatrix(): Matrix
    /**
     * Gets the current projection matrix
     * @returns a Matrix
     */
    fun getProjectionMatrix(): Matrix
    /**
     * Gets the current transform matrix
     * @returns a Matrix made of View * Projection
     */
    fun getTransformMatrix(): Matrix
    /**
     * Sets the current transform matrix
     * @param viewL defines the View matrix to use
     * @param projectionL defines the Projection matrix to use
     * @param viewR defines the right View matrix to use (if provided)
     * @param projectionR defines the right Projection matrix to use (if provided)
     */
    fun setTransformMatrix(viewL: Matrix, projectionL: Matrix, viewR: Matrix?, projectionR: Matrix?)
    /**
     * Gets the uniform buffer used to store scene data
     * @returns a UniformBuffer
     */
    fun getSceneUniformBuffer(): UniformBuffer
    /**
     * Gets an unique (relatively to the current scene) Id
     * @returns an unique Number for the scene
     */
    fun getUniqueId(): Number
    /**
     * Add a mesh to the list of scene's meshes
     * @param newMesh defines the mesh to add
     * @param recursive if all child meshes should also be added to the scene
     */
    fun addMesh(newMesh: AbstractMesh, recursive: Boolean? = definedExternally)
    /**
     * Remove a mesh for the list of scene's meshes
     * @param toRemove defines the mesh to remove
     * @param recursive if all child meshes should also be removed from the scene
     * @returns the index where the mesh was in the mesh list
     */
    fun removeMesh(toRemove: AbstractMesh, recursive: Boolean? = definedExternally): Number
    /**
     * Add a transform node to the list of scene's transform nodes
     * @param newTransformNode defines the transform node to add
     */
    fun addTransformNode(newTransformNode: TransformNode)
    /**
     * Remove a transform node for the list of scene's transform nodes
     * @param toRemove defines the transform node to remove
     * @returns the index where the transform node was in the transform node list
     */
    fun removeTransformNode(toRemove: TransformNode): Number
    /**
     * Remove a skeleton for the list of scene's skeletons
     * @param toRemove defines the skeleton to remove
     * @returns the index where the skeleton was in the skeleton list
     */
    fun removeSkeleton(toRemove: Skeleton): Number
    /**
     * Remove a morph target for the list of scene's morph targets
     * @param toRemove defines the morph target to remove
     * @returns the index where the morph target was in the morph target list
     */
    fun removeMorphTargetManager(toRemove: MorphTargetManager): Number
    /**
     * Remove a light for the list of scene's lights
     * @param toRemove defines the light to remove
     * @returns the index where the light was in the light list
     */
    fun removeLight(toRemove: Light): Number
    /**
     * Remove a camera for the list of scene's cameras
     * @param toRemove defines the camera to remove
     * @returns the index where the camera was in the camera list
     */
    fun removeCamera(toRemove: Camera): Number
    /**
     * Remove a particle system for the list of scene's particle systems
     * @param toRemove defines the particle system to remove
     * @returns the index where the particle system was in the particle system list
     */ 
    fun removeParticleSystem(toRemove: IParticleSystem): Number
    /**
     * Remove a animation for the list of scene's animations
     * @param toRemove defines the animation to remove
     * @returns the index where the animation was in the animation list
     */
    fun removeAnimation(toRemove: Animation): Number
    /**
     * Will stop the animation of the given target
     * @param target - the target
     * @param animationName - the name of the animation to stop (all animations will be stopped if both this and targetMask are empty)
     * @param targetMask - a function that determines if the animation should be stopped based on its target (all animations will be stopped if both this and animationName are empty)
     */
    fun stopAnimation(target: Any, animationName: String?, targetMask: ((target: Any) -> Boolean)?)
    /**
     * Removes the given animation group from this scene.
     * @param toRemove The animation group to remove
     * @returns The index of the removed animation group
     */
    fun removeAnimationGroup(toRemove: AnimationGroup): Number
    /**
     * Removes the given multi-material from this scene.
     * @param toRemove The multi-material to remove
     * @returns The index of the removed multi-material
     */
    fun removeMultiMaterial(toRemove: MultiMaterial): Number
    /**
     * Removes the given material from this scene.
     * @param toRemove The material to remove
     * @returns The index of the removed material
     */
    fun removeMaterial(toRemove: Material): Number
    /**
     * Removes the given action manager from this scene.
     * @param toRemove The action manager to remove
     * @returns The index of the removed action manager
     */
    fun removeActionManager(toRemove: AbstractActionManager): Number
    /**
     * Removes the given texture from this scene.
     * @param toRemove The texture to remove
     * @returns The index of the removed texture
     */
    fun removeTexture(toRemove: BaseTexture): Number
    /**
     * Adds the given light to this scene
     * @param newLight The light to add
     */
    fun addLight(newLight: Light)
    /**
     * Sorts the list list based on light priorities
     */
    fun sortLightsByPriority()
    /**
     * Adds the given camera to this scene
     * @param newCamera The camera to add
     */
    fun addCamera(newCamera: Camera)
    /**
     * Adds the given skeleton to this scene
     * @param newSkeleton The skeleton to add
     */
    fun addSkeleton(newSkeleton: Skeleton)
    /**
     * Adds the given particle system to this scene
     * @param newParticleSystem The particle system to add
     */
    fun addParticleSystem(newParticleSystem: IParticleSystem)
    /**
     * Adds the given animation to this scene
     * @param newAnimation The animation to add
     */
    fun addAnimation(newAnimation: Animation)
    /**
     * Adds the given animation group to this scene.
     * @param newAnimationGroup The animation group to add
     */
    fun addAnimationGroup(newAnimationGroup: AnimationGroup)
    /**
     * Adds the given multi-material to this scene
     * @param newMultiMaterial The multi-material to add
     */
    fun addMultiMaterial(newMultiMaterial: MultiMaterial)
    /**
     * Adds the given material to this scene
     * @param newMaterial The material to add
     */
    fun addMaterial(newMaterial: Material)
    /**
     * Adds the given morph target to this scene
     * @param newMorphTargetManager The morph target to add
     */
    fun addMorphTargetManager(newMorphTargetManager: MorphTargetManager)
    /**
     * Adds the given geometry to this scene
     * @param newGeometry The geometry to add
     */
    fun addGeometry(newGeometry: Geometry)
    /**
     * Adds the given action manager to this scene
     * @param newActionManager The action manager to add
     */
    fun addActionManager(newActionManager: AbstractActionManager)
    /**
     * Adds the given texture to this scene.
     * @param newTexture The texture to add
     */
    fun addTexture(newTexture: BaseTexture)
    /**
     * Switch active camera
     * @param newCamera defines the new active camera
     * @param attachControl defines if attachControl must be called for the new active camera (default: true)
     */
    fun switchActiveCamera(newCamera: Camera, attachControl: Boolean?)
    /**
     * sets the active camera of the scene using its ID
     * @param id defines the camera's ID
     * @return the new active camera or null if none found.
     */
    fun setActiveCameraByID(id: String): Camera?
    /**
     * sets the active camera of the scene using its name
     * @param name defines the camera's name
     * @returns the new active camera or null if none found.
     */
    fun setActiveCameraByName(name: String): Camera?
    /**
     * get an animation group using its name
     * @param name defines the material's name
     * @return the animation group or null if none found.
     */
    fun getAnimationGroupByName(name: String): AnimationGroup?
    /**
     * Get a material using its unique id
     * @param uniqueId defines the material's unique id
     * @return the material or null if none found.
     */
    fun getMaterialByUniqueID(uniqueId: Number): Material?
    /**
     * get a material using its id
     * @param id defines the material's ID
     * @return the material or null if none found.
     */
    fun getMaterialByID(id: String): Material?
    /**
     * Gets a material using its name
     * @param name defines the material's name
     * @return the material or null if none found.
     */
    fun getMaterialByName(name: String): Material?
    /**
     * Gets a camera using its id
     * @param id defines the id to look for
     * @returns the camera or null if not found
     */
    fun getCameraByID(id: String): Camera?
    /**
     * Gets a camera using its unique id
     * @param uniqueId defines the unique id to look for
     * @returns the camera or null if not found
     */
    fun getCameraByUniqueID(uniqueId: Number): Camera?
    /**
     * Gets a camera using its name
     * @param name defines the camera's name
     * @return the camera or null if none found.
     */
    fun getCameraByName(name: String): Camera?
    /**
     * Gets a bone using its id
     * @param id defines the bone's id
     * @return the bone or null if not found
     */
    fun getBoneByID(id: String): Bone?
    /**
     * Gets a bone using its id
     * @param name defines the bone's name
     * @return the bone or null if not found
     */
    fun getBoneByName(name: String): Bone?
    /**
     * Gets a light node using its name
     * @param name defines the the light's name
     * @return the light or null if none found.
     */
    fun getLightByName(name: String): Light?
    /**
     * Gets a light node using its id
     * @param id defines the light's id
     * @return the light or null if none found.
     */
    fun getLightByID(id: String): Light?
    /**
     * Gets a light node using its scene-generated unique ID
     * @param uniqueId defines the light's unique id
     * @return the light or null if none found.
     */
    fun getLightByUniqueID(uniqueId: Number): Light?
    /**
     * Gets a particle system by id
     * @param id defines the particle system id
     * @return the corresponding system or null if none found
     */
    fun getParticleSystemByID(id: String): IParticleSystem?
    /**
     * Gets a geometry using its ID
     * @param id defines the geometry's id
     * @return the geometry or null if none found.
     */
    fun getGeometryByID(id: String): Geometry?
    /**
     * Add a new geometry to this scene
     * @param geometry defines the geometry to be added to the scene.
     * @param force defines if the geometry must be pushed even if a geometry with this id already exists
     * @return a Boolean defining if the geometry was added or not
     */
    fun pushGeometry(geometry: Geometry, force: Boolean?): Boolean
    /**
     * Removes an existing geometry
     * @param geometry defines the geometry to be removed from the scene
     * @return a Boolean defining if the geometry was removed or not
     */
    fun removeGeometry(geometry: Geometry): Boolean
    /**
     * Gets the list of geometries attached to the scene
     * @returns an array of Geometry
     */
    fun getGeometries(): Array<Geometry>
    /**
     * Gets the first added mesh found of a given ID
     * @param id defines the id to search for
     * @return the mesh found or null if not found at all
     */
    fun getMeshByID(id: String): AbstractMesh?
    /**
     * Gets a list of meshes using their id
     * @param id defines the id to search for
     * @returns a list of meshes
     */
    fun getMeshesByID(id: String): Array<AbstractMesh>
    /**
     * Gets the first added transform node found of a given ID
     * @param id defines the id to search for
     * @return the found transform node or null if not found at all.
     */
    fun getTransformNodeByID(id: String): TransformNode?
    /**
     * Gets a transform node with its auto-generated unique id
     * @param uniqueId efines the unique id to search for
     * @return the found transform node or null if not found at all.
     */
    fun getTransformNodeByUniqueID(uniqueId: Number): TransformNode?
    /**
     * Gets a list of transform nodes using their id
     * @param id defines the id to search for
     * @returns a list of transform nodes
     */
    fun getTransformNodesByID(id: String): Array<TransformNode>
    /**
     * Gets a mesh with its auto-generated unique id
     * @param uniqueId defines the unique id to search for
     * @return the found mesh or null if not found at all.
     */
    fun getMeshByUniqueID(uniqueId: Number): AbstractMesh?
    /**
     * Gets a the last added mesh using a given id
     * @param id defines the id to search for
     * @return the found mesh or null if not found at all.
     */
    fun getLastMeshByID(id: String): AbstractMesh?
    /**
     * Gets a the last added node (Mesh, Camera, Light) using a given id
     * @param id defines the id to search for
     * @return the found node or null if not found at all
     */
    fun getLastEntryByID(id: String): Node?
    /**
     * Gets a node (Mesh, Camera, Light) using a given id
     * @param id defines the id to search for
     * @return the found node or null if not found at all
     */
    fun getNodeByID(id: String): Node?
    /**
     * Gets a node (Mesh, Camera, Light) using a given name
     * @param name defines the name to search for
     * @return the found node or null if not found at all.
     */
    fun getNodeByName(name: String): Node?
    /**
     * Gets a mesh using a given name
     * @param name defines the name to search for
     * @return the found mesh or null if not found at all.
     */
    fun getMeshByName(name: String): AbstractMesh?
    /**
     * Gets a transform node using a given name
     * @param name defines the name to search for
     * @return the found transform node or null if not found at all.
     */
    fun getTransformNodeByName(name: String): TransformNode?
    /**
     * Gets a skeleton using a given id (if many are found, this function will pick the last one)
     * @param id defines the id to search for
     * @return the found skeleton or null if not found at all.
     */
    fun getLastSkeletonByID(id: String): Skeleton?
    /**
     * Gets a skeleton using a given auto generated unique id
     * @param  uniqueId defines the unique id to search for
     * @return the found skeleton or null if not found at all.
     */
    fun getSkeletonByUniqueId(uniqueId: Number): Skeleton?
    /**
     * Gets a skeleton using a given id (if many are found, this function will pick the first one)
     * @param id defines the id to search for
     * @return the found skeleton or null if not found at all.
     */
    fun getSkeletonById(id: String): Skeleton?
    /**
     * Gets a skeleton using a given name
     * @param name defines the name to search for
     * @return the found skeleton or null if not found at all.
     */
    fun getSkeletonByName(name: String): Skeleton?
    /**
     * Gets a morph target manager  using a given id (if many are found, this function will pick the last one)
     * @param id defines the id to search for
     * @return the found morph target manager or null if not found at all.
     */
    fun getMorphTargetManagerById(id: Number): MorphTargetManager?
    /**
     * Gets a morph target using a given id (if many are found, this function will pick the first one)
     * @param id defines the id to search for
     * @return the found morph target or null if not found at all.
     */
    fun getMorphTargetById(id: String): MorphTarget?
    /**
     * Gets a Boolean indicating if the given mesh is active
     * @param mesh defines the mesh to look for
     * @returns true if the mesh is in the active list
     */
    fun isActiveMesh(mesh: AbstractMesh): Boolean
    /**
     * Return a unique id as a String which can serve as an identifier for the scene
     */
    val uid: String
    /**
     * Add an externaly attached data from its key.
     * This method call will fail and return false, if such key already exists.
     * If you don't care and just want to get the data no matter what, use the more convenient getOrAddExternalDataWithFactory() method.
     * @param key the unique key that identifies the data
     * @param data the data object to associate to the key for this Engine instance
     * @return true if no such key were already present and the data was added successfully, false otherwise
     */
    fun <T>addExternalData(key: String, data: T): Boolean
    /**
     * Get an externaly attached data from its key
     * @param key the unique key that identifies the data
     * @return the associated data, if present (can be null), or undefined if not present
     */
    fun <T>getExternalData(key: String): T?
    /**
     * Get an externaly attached data from its key, create it using a factory if it's not already present
     * @param key the unique key that identifies the data
     * @param factory the factory that will be called to create the instance if and only if it doesn't exists
     * @return the associated data, can be null if the factory returned null.
     */
    fun <T>getOrAddExternalDataWithFactory(key: String, factory: (k: String) -> T): T
    /**
     * Remove an externaly attached data from the Engine instance
     * @param key the unique key that identifies the data
     * @return true if the data was successfully removed, false if it doesn't exist
     */
    fun removeExternalData(key: String): Boolean
    /**
     * Clear the processed materials smart array preventing retention point in material dispose.
     */
    fun freeProcessedMaterials()
    /** Gets or sets a Boolean blocking all the calls to freeActiveMeshes and freeRenderingGroups
     * It can be used in order to prevent going through methods freeRenderingGroups and freeActiveMeshes several times to improve performance
     * when disposing several meshes in a row or a hierarchy of meshes.
     * When used, it is the responsability of the user to blockfreeActiveMeshesAndRenderingGroups back to false.
     */
    var blockfreeActiveMeshesAndRenderingGroups: Boolean
    /**
     * Clear the active meshes smart array preventing retention point in mesh dispose.
     */
    fun freeActiveMeshes()
    /**
     * Clear the info related to rendering groups preventing retention points during dispose.
     */
    fun freeRenderingGroups()
     /**
     * Lambda returning the list of potentially active meshes.
     */
    var getActiveMeshCandidates: () -> ISmartArrayLike<AbstractMesh>
    /**
     * Lambda returning the list of potentially active sub meshes.
     */
    var getActiveSubMeshCandidates: (mesh: AbstractMesh) -> ISmartArrayLike<SubMesh>
    /**
     * Lambda returning the list of potentially intersecting sub meshes.
     */
    var getIntersectingSubMeshCandidates: (mesh: AbstractMesh, localRay: Ray) -> ISmartArrayLike<SubMesh>
    /**
     * Lambda returning the list of potentially colliding sub meshes.
     */
    var getCollidingSubMeshCandidates: (mesh: AbstractMesh, collider: Collider) -> ISmartArrayLike<SubMesh>
    /**
     * Use this function to stop evaluating active meshes. The current list will be keep alive between frames
     * @returns the current scene
     */
    fun freezeActiveMeshes(): Scene
    /**
     * Use this function to restart evaluating active meshes on every frame
     * @returns the current scene
     */
    fun unfreezeActiveMeshes(): Scene
    /**
     * Update the transform matrix to update from the current active camera
     * @param force defines a Boolean used to force the update even if cache is up to date
     */
    fun updateTransformMatrix(force: Boolean?)
    /**
     * User updatable function that will return a deterministic frame time when engine is in deterministic lock step mode
     */
    var getDeterministicFrameTime: () -> Number

    /** Execute all animations (for a frame) */
    fun animate()
    /**
     * Render the scene
     * @param updateCameras defines a Boolean indicating if cameras must update according to their inputs (true by default)
     * @param ignoreAnimations defines a Boolean indicating if animations should not be executed (false by default)
     */
    fun render(updateCameras: Boolean? = definedExternally, ignoreAnimations: Boolean? = definedExternally)
    /**
     * Freeze all materials
     * A frozen material will not be updatable but should be faster to render
     */
    fun freezeMaterials()
    /**
     * Unfreeze all materials
     * A frozen material will not be updatable but should be faster to render
     */
    fun unfreezeMaterials()
    /**
     * Releases all held ressources
     */
    fun dispose()
    /**
     * Gets if the scene is already disposed
     */
    val isDisposed: Boolean
    /**
     * Call this function to reduce memory footprint of the scene.
     * Vertex buffers will not store CPU data anymore (this will prevent picking, collisions or physics to work correctly)
     */
    fun clearCachedVertexData()
    /**
     * This function will remove the local cached buffer data from texture.
     * It will save memory but will prevent the texture from being rebuilt
     */
    fun cleanCachedTextureBuffer()
    /**
     * Get the world extend vectors with an optional filter
     *
     * @param filterPredicate the predicate - which meshes should be included when calculating the world size
     * @returns {{ min: Vector3; max: Vector3 }} min and max vectors
     */
    fun getWorldExtends(filterPredicate: ((mesh: AbstractMesh) -> Boolean)?): Any
    /*{
        var min: Vector3
        var max: Vector3
    }*/
    /**
     * Creates a ray that can be used to pick in the scene
     * @param x defines the x coordinate of the origin (on-screen)
     * @param y defines the y coordinate of the origin (on-screen)
     * @param world defines the world matrix to use if you want to pick in object space (instead of world space)
     * @param camera defines the camera to use for the picking
     * @param cameraViewSpace defines if picking will be done in view space (false by default)
     * @returns a Ray
     */
    fun createPickingRay(x: Number, y: Number, world: Matrix, camera: Camera?, cameraViewSpace: Boolean?): Ray
    /**
     * Creates a ray that can be used to pick in the scene
     * @param x defines the x coordinate of the origin (on-screen)
     * @param y defines the y coordinate of the origin (on-screen)
     * @param world defines the world matrix to use if you want to pick in object space (instead of world space)
     * @param result defines the ray where to store the picking ray
     * @param camera defines the camera to use for the picking
     * @param cameraViewSpace defines if picking will be done in view space (false by default)
     * @returns the current scene
     */
    fun createPickingRayToRef(x: Number, y: Number, world: Matrix, result: Ray, camera: Camera?, cameraViewSpace: Boolean?): Scene
    /**
     * Creates a ray that can be used to pick in the scene
     * @param x defines the x coordinate of the origin (on-screen)
     * @param y defines the y coordinate of the origin (on-screen)
     * @param camera defines the camera to use for the picking
     * @returns a Ray
     */
    fun createPickingRayInCameraSpace(x: Number, y: Number, camera: Camera?): Ray
    /**
     * Creates a ray that can be used to pick in the scene
     * @param x defines the x coordinate of the origin (on-screen)
     * @param y defines the y coordinate of the origin (on-screen)
     * @param result defines the ray where to store the picking ray
     * @param camera defines the camera to use for the picking
     * @returns the current scene
     */
    fun createPickingRayInCameraSpaceToRef(x: Number, y: Number, result: Ray, camera: Camera?): Scene
    /** Launch a ray to try to pick a mesh in the scene
     * @param x position on screen
     * @param y position on screen
     * @param predicate Predicate function used to determine eligible meshes. Can be set to null. In this case, a mesh must be enabled, visible and with isPickable set to true
     * @param fastCheck Launch a fast check only using the bounding boxes. Can be set to null.
     * @param camera to use for computing the picking ray. Can be set to null. In this case, the scene.activeCamera will be used
     * @param trianglePredicate defines an optional predicate used to select faces when a mesh intersection is detected
     * @returns a PickingInfo
     */
    fun pick(x: Number, y: Number, predicate: ((mesh: AbstractMesh) -> Boolean)? = definedExternally, fastCheck: Boolean? = definedExternally, camera: Camera? = definedExternally, trianglePredicate: ((p0: Vector3, p1: Vector3, p2: Vector3) -> Boolean)? = definedExternally): PickingInfo?
    /** Use the given ray to pick a mesh in the scene
     * @param ray The ray to use to pick meshes
     * @param predicate Predicate function used to determine eligible meshes. Can be set to null. In this case, a mesh must have isPickable set to true
     * @param fastCheck Launch a fast check only using the bounding boxes. Can be set to null
     * @param trianglePredicate defines an optional predicate used to select faces when a mesh intersection is detected
     * @returns a PickingInfo
     */
    fun pickWithRay(ray: Ray, predicate: ((mesh: AbstractMesh) -> Boolean)?, fastCheck: Boolean?, trianglePredicate: TrianglePickingPredicate?): PickingInfo?
    /**
     * Launch a ray to try to pick a mesh in the scene
     * @param x X position on screen
     * @param y Y position on screen
     * @param predicate Predicate function used to determine eligible meshes. Can be set to null. In this case, a mesh must be enabled, visible and with isPickable set to true
     * @param camera camera to use for computing the picking ray. Can be set to null. In this case, the scene.activeCamera will be used
     * @param trianglePredicate defines an optional predicate used to select faces when a mesh intersection is detected
     * @returns an array of PickingInfo
     */
    fun multiPick(x: Number, y: Number, predicate: ((mesh: AbstractMesh) -> Boolean)?, camera: Camera?, trianglePredicate: TrianglePickingPredicate?): Array<PickingInfo>?
    /**
     * Launch a ray to try to pick a mesh in the scene
     * @param ray Ray to use
     * @param predicate Predicate function used to determine eligible meshes. Can be set to null. In this case, a mesh must be enabled, visible and with isPickable set to true
     * @param trianglePredicate defines an optional predicate used to select faces when a mesh intersection is detected
     * @returns an array of PickingInfo
     */
    fun multiPickWithRay(ray: Ray, predicate: (mesh: AbstractMesh) -> Boolean, trianglePredicate: TrianglePickingPredicate?): Array<PickingInfo>?
    /**
     * Force the value of meshUnderPointer
     * @param mesh defines the mesh to use
     */
    fun setPointerOverMesh(mesh: AbstractMesh?)
    /**
     * Gets the mesh under the pointer
     * @returns a Mesh or null if no mesh is under the pointer
     */
    fun getPointerOverMesh(): AbstractMesh?
    /**
     * Get a list of meshes by tags
     * @param tagsQuery defines the tags query to use
     * @param forEach defines a predicate used to filter results
     * @returns an array of Mesh
     */
    fun getMeshesByTags(tagsQuery: String, forEach: ((mesh: AbstractMesh) -> Unit)?): Array<Mesh>
    /**
     * Get a list of cameras by tags
     * @param tagsQuery defines the tags query to use
     * @param forEach defines a predicate used to filter results
     * @returns an array of Camera
     */
    fun getCamerasByTags(tagsQuery: String, forEach: ((camera: Camera) -> Unit)?): Array<Camera>
    /**
     * Get a list of lights by tags
     * @param tagsQuery defines the tags query to use
     * @param forEach defines a predicate used to filter results
     * @returns an array of Light
     */
    fun getLightsByTags(tagsQuery: String, forEach: ((light: Light) -> Unit)?): Array<Light>
    /**
     * Get a list of materials by tags
     * @param tagsQuery defines the tags query to use
     * @param forEach defines a predicate used to filter results
     * @returns an array of Material
     */
    fun getMaterialByTags(tagsQuery: String, forEach: ((material: Material) -> Unit)?): Array<Material>
    /**
     * Overrides the default sort function applied in the renderging group to prepare the meshes.
     * This allowed control for front to back rendering or reversly depending of the special needs.
     *
     * @param renderingGroupId The rendering group id corresponding to its index
     * @param opaqueSortCompareFn The opaque queue comparison function use to sort.
     * @param alphaTestSortCompareFn The alpha test queue comparison function use to sort.
     * @param transparentSortCompareFn The transparent queue comparison function use to sort.
     */
    fun setRenderingOrder(renderingGroupId: Number, opaqueSortCompareFn: ((a: SubMesh, b: SubMesh) -> Number)?, alphaTestSortCompareFn: ((a: SubMesh, b: SubMesh) -> Number)?, transparentSortCompareFn: ((a: SubMesh, b: SubMesh) -> Number)?)
    /**
     * Specifies whether or not the stencil and depth buffer are cleared between two rendering groups.
     *
     * @param renderingGroupId The rendering group id corresponding to its index
     * @param autoClearDepthStencil Automatically clears depth and stencil between groups if true.
     * @param depth Automatically clears depth between groups if true and autoClear is true.
     * @param stencil Automatically clears stencil between groups if true and autoClear is true.
     */
    fun setRenderingAutoClearDepthStencil(renderingGroupId: Number, autoClearDepthStencil: Boolean, depth: Boolean?, stencil: Boolean?)
    /**
     * Gets the current auto clear configuration for one rendering group of the rendering
     * manager.
     * @param index the rendering group index to get the information for
     * @returns The auto clear setup for the requested rendering group
     */
    fun getAutoClearDepthStencilSetup(index: Number): IRenderingManagerAutoClearSetup
    /** Gets or sets a Boolean blocking all the calls to markAllMaterialsAsDirty (ie. the materials won't be updated if they are out of sync) */
    var blockMaterialDirtyMechanism: Boolean
    /**
     * Will flag all materials as dirty to trigger new shader compilation
     * @param flag defines the flag used to specify which material part must be marked as dirty
     * @param predicate If not null, it will be used to specifiy if a material has to be marked as dirty
     */
    fun markAllMaterialsAsDirty(flag: Number, predicate: ((mat: Material) -> Boolean)?)

    companion object {
        /** The fog is deactivated */
        val FOGMODE_NONE: Number
        /** The fog density is following an exponential function */
        val FOGMODE_EXP: Number
        /** The fog density is following an exponential function faster than FOGMODE_EXP */
        val FOGMODE_EXP2: Number
        /** The fog density is following a linear function. */
        val FOGMODE_LINEAR: Number
        /**
         * Gets or sets the minimum deltatime when deterministic lock step is enabled
         * @see [doc.babylonjs.com/babylon101/animations#deterministic-lockstep]
         */
        var MinDeltaTime: Number
        /**
         * Gets or sets the maximum deltatime when deterministic lock step is enabled
         * @see [doc.babylonjs.com/babylon101/animations#deterministic-lockstep]
         */
        var MaxDeltaTime: Number
        /**
         * Factory used to create the default material.
         * @param scene The scene to create the material for
         * @returns The default material
         */
        fun DefaultMaterialFactory(scene: Scene): Material
        /**
         * Factory used to create the a collision coordinator.
         * @returns The collision coordinator
         */
        fun CollisionCoordinatorFactory(): ICollisionCoordinator

        /**
         * Gets or sets the distance in pixel that you have to move to prevent some events. Default is 10 pixels
         */
        var DragMovementThreshold: Number
        /**
         * Time in milliseconds to wait to raise long press events if button is still pressed. Default is 500 ms
         */
        var LongPressDelay: Number
        /**
         * Time in milliseconds to wait to raise long press events if button is still pressed. Default is 300 ms
         */
        var DoubleClickDelay: Number
        /** If you need to check double click without raising a single click at first click, enable this flag */
        var ExclusiveDoubleClickMode: Boolean
    }
}
