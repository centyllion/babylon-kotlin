@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals")
@file:JsModule("babylonjs")
@file:JsNonModule
package babylonjs

/**
 * Size options for a post process
 */
external class PostProcessOptions {
    var width: Number
    var height: Number
}

/**
 * PostProcess can be used to apply a shader to a texture after it has been rendered
 * See https://doc.babylonjs.com/how_to/how_to_use_postprocesses
 */
abstract external class PostProcess {
    /** Name of the PostProcess. */
    val name: String
    /**
     * Gets or sets the unique id of the post process
     */
    val uniqueId: Number
    /**
     * Width of the texture to apply the post process on
     */
    val width: Number
    /**
     * Height of the texture to apply the post process on
     */
    val height: Number
    /**
     * Sampling mode used by the shader
     * See https://doc.babylonjs.com/classes/3.1/texture
     */
    val renderTargetSamplingMode: Number
    /**
     * Clear color to use when screen clearing
     */
    val clearColor: Color4
    /**
     * If the buffer needs to be cleared before applying the post process. (default: true)
     * Should be set to false if shader will overwrite all previous pixels.
     */
    val autoClear: Boolean
    /**
     * Type of alpha mode to use when performing the post process (default: Engine.ALPHA_DISABLE)
     */
    val alphaMode: Number
    /**
     * Sets the setAlphaBlendConstants of the babylon engine
     */
    val alphaConstants: Color4
    /**
     * Animations to be used for the post processing
     */
    val animations: Array<Animation>
    /**
     * Enable Pixel Perfect mode where texture is not scaled to be power of 2.
     * Can only be used on a single postprocess or on the last one of a chain. (default: false)
     */
    val enablePixelPerfectMode: Boolean
    /**
     * Force the postprocess to be applied without taking in account viewport
     */
    val forceFullscreenViewport: Boolean
    /**
     * List of inspectable custom properties (used by the Inspector)
     * @see [https://doc.babylonjs.com/how_to/debug_layer#extensibility]
     */
    val inspectableCustomProperties: Array<IInspectable>
    /**
     * Scale mode for the post process (default: Engine.SCALEMODE_FLOOR)
     *
     * | Value | Type                                | Description |
     * | ----- | ----------------------------------- | ----------- |
     * | 1     | SCALEMODE_FLOOR                     | [engine.scalemode_floor](http://doc.babylonjs.com/api/classes/babylon.engine#scalemode_floor) |
     * | 2     | SCALEMODE_NEAREST                   | [engine.scalemode_nearest](http://doc.babylonjs.com/api/classes/babylon.engine#scalemode_nearest) |
     * | 3     | SCALEMODE_CEILING                   | [engine.scalemode_ceiling](http://doc.babylonjs.com/api/classes/babylon.engine#scalemode_ceiling) |
     *
     */
    val scaleMode: Number
    /**
     * Force textures to be a power of two (default: false)
     */
    val alwaysForcePOT: Boolean
    /**
     * Number of sample textures (default: 1)
     */
    val samples: Number
    /**
     * Modify the scale of the post process to be the same as the viewport (default: false)
     */
    val adaptScaleToCurrentViewport: Boolean
    /**
     * Returns the fragment url or shader name used in the post process.
     * @returns the fragment url or name in the shader store.
     */
    fun getEffectName(): String
    /**
     * An event triggered when the postprocess is activated.
     */
    var onActivateObservable: Observable<Camera>
    /**
     * A function that is added to the onActivateObservable
     */
    var onActivate: ((camera: Camera) -> Unit)?
    /**
     * An event triggered when the postprocess changes its size.
     */
    var onSizeChangedObservable: Observable<PostProcess>
    /**
     * A function that is added to the onSizeChangedObservable
     */
    var onSizeChanged: (postProcess: PostProcess) -> Unit
    /**
     * An event triggered when the postprocess applies its effect.
     */
    var onApplyObservable: Observable<Effect>
    /**
     * A function that is added to the onApplyObservable
     */
    var onApply: (effect: Effect) -> Unit
    /**
     * An event triggered before rendering the postprocess
     */
    var onBeforeRenderObservable: Observable<Effect>
    /**
     * A function that is added to the onBeforeRenderObservable
     */
    var onBeforeRender: (effect: Effect) -> Unit
    /**
     * An event triggered after rendering the postprocess
     */
    var onAfterRenderObservable: Observable<Effect>
    /**
     * A function that is added to the onAfterRenderObservable
     */
    var onAfterRender: (effect: Effect) -> Unit
    /**
     * The input texture for this post process and the output texture of the previous post process. When added to a pipeline the previous post process will
     * render it's output into this texture and this texture will be used as textureSampler in the fragment shader of this post process.
     */
    val inputTexture: InternalTexture
    /**
     * Gets the camera which post process is applied to.
     * @returns The camera the post process is applied to.
     */
    fun getCamera(): Camera
    /**
     * Gets the texel size of the postprocess.
     * See https://en.wikipedia.org/wiki/Texel_(graphics)
     */
    val texelSize: Vector2
    /**
     * Creates a new instance PostProcess
     * @param name The name of the PostProcess.
     * @param fragmentUrl The url of the fragment shader to be used.
     * @param parameters Array of the names of uniform non-sampler2D variables that will be passed to the shader.
     * @param samplers Array of the names of uniform sampler2D variables that will be passed to the shader.
     * @param options The required width/height ratio to downsize to before computing the render pass. (Use 1.0 for full size)
     * @param camera The camera to apply the render pass to.
     * @param samplingMode The sampling mode to be used when computing the pass. (default: 0)
     * @param engine The engine which the post process will be applied. (default: current engine)
     * @param reusable If the post process can be reused on the same frame. (default: false)
     * @param defines String of defines that will be set when running the fragment shader. (default: null)
     * @param textureType Type of textures used when performing the post process. (default: 0)
     * @param vertexUrl The url of the vertex shader to be used. (default: "postprocess")
     * @param indexParameters The index parameters to be used for babylons include syntax "#include<kernelBlurVaryingDeclaration>[0..varyingCount]". (default: undefined) See usage in babylon.blurPostProcess.ts and kernelBlur.vertex.fx
     * @param blockCompilation If the shader should not be compiled imediatly. (default: false)
     */
    constructor(
        /** Name of the PostProcess. */
        name: String, fragmentUrl: String, parameters: Array<String>?, samplers: Array<String>?, options: Number /*| PostProcessOptions*/, camera: Camera?, samplingMode: Number?, engine: Engine?, reusable: Boolean?, defines: String?, textureType: Number?, vertexUrl: String?, indexParameters: Any?, blockCompilation: Boolean?)
    /**
     * Gets a String idenfifying the name of the class
     * @returns "PostProcess" String
     */
    fun getClassName(): String
    /**
     * Gets the engine which this post process belongs to.
     * @returns The engine the post process was enabled with.
     */
    fun getEngine(): Engine
    /**
     * The effect that is created when initializing the post process.
     * @returns The created effect corrisponding the the postprocess.
     */
    fun getEffect(): Effect
    /**
     * To avoid multiple redundant textures for multiple post process, the output the output texture for this post process can be shared with another.
     * @param postProcess The post process to share the output with.
     * @returns This post process.
     */
    fun shareOutputWith(postProcess: PostProcess): PostProcess
    /**
     * Reverses the effect of calling shareOutputWith and returns the post process back to its original state.
     * This should be called if the post process that shares output with this post process is disabled/disposed.
     */
    fun useOwnOutput()
    /**
     * Updates the effect with the current post process compile time values and recompiles the shader.
     * @param defines Define statements that should be added at the beginning of the shader. (default: null)
     * @param uniforms Set of uniform variables that will be passed to the shader. (default: null)
     * @param samplers Set of Texture2D variables that will be passed to the shader. (default: null)
     * @param indexParameters The index parameters to be used for babylons include syntax "#include<kernelBlurVaryingDeclaration>[0..varyingCount]". (default: undefined) See usage in babylon.blurPostProcess.ts and kernelBlur.vertex.fx
     * @param onCompiled Called when the shader has been compiled.
     * @param onError Called if there is an error when compiling a shader.
     */
    fun updateEffect(defines: String?, uniforms: Array<String>?, samplers: Array<String>?, indexParameters: Any?, onCompiled: ((effect: Effect) -> Unit)?, onError: ((effect: Effect, errors: String) -> Unit)?)
    /**
     * The post process is reusable if it can be used multiple times within one frame.
     * @returns If the post process is reusable
     */
    fun isReusable(): Boolean
    /** invalidate frameBuffer to hint the postprocess to create a depth buffer */
    fun markTextureDirty()
    /**
     * Activates the post process by intializing the textures to be used when executed. Notifies onActivateObservable.
     * When this post process is used in a pipeline, this is call will bind the input texture of this post process to the output of the previous.
     * @param camera The camera that will be used in the post process. This camera will be used when calling onActivateObservable.
     * @param sourceTexture The source texture to be inspected to get the width and height if not specified in the post process constructor. (default: null)
     * @param forceDepthStencil If true, a depth and stencil buffer will be generated. (default: false)
     * @returns The target texture that was bound to be written to.
     */
    fun activate(camera: Camera?, sourceTexture: InternalTexture?, forceDepthStencil: Boolean?): InternalTexture
    /**
     * If the post process is supported.
     */
    val isSupported: Boolean
    /**
     * The aspect ratio of the output texture.
     */
    val aspectRatio: Number
    /**
     * Get a value indicating if the post-process is ready to be used
     * @returns true if the post-process is ready (shader is compiled)
     */
    fun isReady(): Boolean
    /**
     * Binds all textures and uniforms to the shader, this will be run on every pass.
     * @returns the effect corrisponding to this post process. Null if not compiled or not ready.
     */
    fun apply(): Effect?
    /**
     * Disposes the post process.
     * @param camera The camera to dispose the post process on.
     */
    fun dispose(camera: Camera?)
}

/**
 * The Blur Post Process which blurs an image based on a kernel and direction.
 * Can be used twice in x and y directions to perform a guassian blur in two passes.
 */
external class BlurPostProcess: PostProcess {
    /** The direction in which to blur the image. */
    val direction: Vector2
    /**
     * Sets the length in pixels of the blur sample region
     */
    /**
     * Gets the length in pixels of the blur sample region
     */
    var kernel: Number
    /**
     * Sets wether or not the blur needs to unpack/repack floats
     */
    /**
     * Gets wether or not the blur is unpacking/repacking floats
     */
    var packedFloat: Boolean
    /**
     * Creates a new instance BlurPostProcess
     * @param name The name of the effect.
     * @param direction The direction in which to blur the image.
     * @param kernel The size of the kernel to be used when computing the blur. eg. Size of 3 will blur the center pixel by 2 pixels surrounding it.
     * @param options The required width/height ratio to downsize to before computing the render pass. (Use 1.0 for full size)
     * @param camera The camera to apply the render pass to.
     * @param samplingMode The sampling mode to be used when computing the pass. (default: 0)
     * @param engine The engine which the post process will be applied. (default: current engine)
     * @param reusable If the post process can be reused on the same frame. (default: false)
     * @param textureType Type of textures used when performing the post process. (default: 0)
     * @param blockCompilation If compilation of the shader should not be done in the constructor. The updateEffect method can be used to compile the shader at a later time. (default: false)
     */
    constructor(
        name: String,
        /** The direction in which to blur the image. */
        direction: Vector2, kernel: Number,
        options: Number /*| PostProcessOptions*/,
        camera: Camera? = definedExternally, samplingMode: Number? = definedExternally,
        engine: Engine? = definedExternally, reusable: Boolean? = definedExternally,
        textureType: Number? = definedExternally, defines: String? = definedExternally,
        blockCompilation: Boolean? = definedExternally
    )
}

/**
 * This represents a set of one or more post processes in Babylon.
 * A post process can be used to apply a shader to a texture after it is rendered.
 * @example https://doc.babylonjs.com/how_to/how_to_use_postprocessrenderpipeline
 */
open external class PostProcessRenderEffect {
    /**
     * Instantiates a post process render effect.
     * A post process can be used to apply a shader to a texture after it is rendered.
     * @param engine The engine the effect is tied to
     * @param name The name of the effect
     * @param getPostProcesses A function that returns a set of post processes which the effect will run in order to be run.
     * @param singleInstance False if this post process can be run on multiple cameras. (default: true)
     */
    constructor(engine: Engine, name: String, getPostProcesses: (() -> PostProcess)?, singleInstance: Boolean?)
    /**
     * Instantiates a post process render effect.
     * A post process can be used to apply a shader to a texture after it is rendered.
     * @param engine The engine the effect is tied to
     * @param name The name of the effect
     * @param getPostProcesses A function that returns a set of post processes which the effect will run in order to be run.
     * @param singleInstance False if this post process can be run on multiple cameras. (default: true)
     */
    constructor(engine: Engine, name: String, getPostProcesses: (() ->Array<PostProcess>)?, singleInstance: Boolean?)
    /**
     * Checks if all the post processes in the effect are supported.
     */
    val isSupported: Boolean
    /**
     * Gets a list of the post processes contained in the effect.
     * @param camera The camera to get the post processes on.
     * @returns The list of the post processes in the effect.
     */
    fun getPostProcesses(camera: Camera?): Array<PostProcess>?
}

/**
 * The bloom effect spreads bright areas of an image to simulate artifacts seen in cameras
 */
external class BloomEffect: PostProcessRenderEffect {
    /**
     * The luminance threshold to find bright areas of the image to bloom.
     */
    var threshold: Number
    /**
     * The strength of the bloom.
     */
    var weight: Number
    /**
     * Specifies the size of the bloom blur kernel, relative to the final output size
     */
    var kernel: Number
    /**
     * Creates a new instance of @see BloomEffect
     * @param scene The scene the effect belongs to.
     * @param bloomScale The ratio of the blur texture to the input texture that should be used to compute the bloom.
     * @param bloomKernel The size of the kernel to be used when applying the blur.
     * @param bloomWeight The the strength of bloom.
     * @param pipelineTextureType The type of texture to be used when performing the post processing.
     * @param blockCompilation If compilation of the shader should not be done in the constructor. The updateEffect method can be used to compile the shader at a later time. (default: false)
     */
    constructor(scene: Scene, bloomScale: Number, bloomWeight: Number, bloomKernel: Number, pipelineTextureType: Number?, blockCompilation: Boolean?)
    /**
     * Disposes each of the internal effects for a given camera.
     * @param camera The camera to dispose the effect on.
     */
    fun disposeEffects(camera: Camera)
}

/**
 * PostProcessRenderPipeline
 * @see [https://doc.babylonjs.com/how_to/how_to_use_postprocessrenderpipeline]
 */
open external class PostProcessRenderPipeline {
    /**
     * List of inspectable custom properties (used by the Inspector)
     * @see [https://doc.babylonjs.com/how_to/debug_layer#extensibility]
     */
    var inspectableCustomProperties: Array<IInspectable>
    /**
     * Gets pipeline name
     */
    val name: String
    /**
     * Initializes a PostProcessRenderPipeline
     * @param engine engine to add the pipeline to
     * @param name name of the pipeline
     */
    constructor(engine: Engine, name: String)
    /**
     * Gets the class name
     * @returns "PostProcessRenderPipeline"
     */
    fun getClassName(): String
    /**
     * If all the render effects in the pipeline are supported
     */
    val isSupported: Boolean
    /**
     * Adds an effect to the pipeline
     * @param renderEffect the effect to add
     */
    fun addEffect(renderEffect: PostProcessRenderEffect)
    /**
     * Disposes of the pipeline
     */
    fun dispose()
}

/**
 * The default rendering pipeline can be added to a scene to apply common post processing effects such as anti-aliasing or depth of field.
 * See https://doc.babylonjs.com/how_to/using_default_rendering_pipeline
 */
external class DefaultRenderingPipeline: PostProcessRenderPipeline, IDisposable, IAnimatable {
    /**
     * @ignore
     * ID of the image processing post process
     */
    val ImageProcessingPostProcessId: String
    /**
     * @ignore
     * ID of the Fast Approximate Anti-Aliasing post process
     */
    val FxaaPostProcessId: String
    /**
     * Sharpen post process which will apply a sharpen convolution to enhance edges
     */
    var sharpen: SharpenPostProcess
    /**
     * Depth of field effect, applies a blur based on how far away objects are from the focus distance.
     */
    var depthOfField: DepthOfFieldEffect
    /**
     * The Fast Approximate Anti-Aliasing post process which attemps to remove aliasing from an image.
     */
    var fxaa: FxaaPostProcess
    /**
     * Image post processing pass used to perform operations such as tone mapping or color grading.
     */
    var imageProcessing: ImageProcessingPostProcess
    /**
     * Chromatic aberration post process which will shift rgb colors in the image
     */
    var chromaticAberration: ChromaticAberrationPostProcess
    /**
     * Grain post process which add noise to the image
     */
    var grain: GrainPostProcess
    /**
     * Animations which can be used to tweak settings over a period of time
     */
    override var animations: Array<Animation>?
    /**
     * Gets active scene
     */
    val scene: Scene
    /**
     * Enable or disable the sharpen process from the pipeline
     */
    var sharpenEnabled: Boolean
    /**
     * Specifies the size of the bloom blur kernel, relative to the final output size
     */
    var bloomKernel: Number
    /**
     * The strength of the bloom.
     */
    var bloomWeight: Number
    /**
     * The strength of the bloom.
     */
    var bloomThreshold: Number
    /**
     * The scale of the bloom, lower value will provide better performance.
     */
    var bloomScale: Number
    /**
     * Enable or disable the bloom from the pipeline
     */
    var bloomEnabled: Boolean
    /**
     * If the depth of field is enabled.
     */
    var depthOfFieldEnabled: Boolean
    /**
     * Blur level of the depth of field effect. (Higher blur will effect performance)
     */
    var depthOfFieldBlurLevel: DepthOfFieldEffectBlurLevel
    /**
     * If the anti aliasing is enabled.
     */
    var fxaaEnabled: Boolean
    /**
     * MSAA sample count, setting this to 4 will provide 4x anti aliasing. (default: 1)
     */
    var samples: Number
    /**
     * If image processing is enabled.
     */
    var imageProcessingEnabled: Boolean
    /**
     * If glow layer is enabled. (Adds a glow effect to emmissive materials)
     */
    var glowLayerEnabled: Boolean
    /**
     * Gets the glow layer (or null if not defined)
     */
    val glowLayer: GlowLayer?
    /**
     * Enable or disable the chromaticAberration process from the pipeline
     */
    var chromaticAberrationEnabled: Boolean
    /**
     * Enable or disable the grain process from the pipeline
     */
    var grainEnabled: Boolean
    /**
     * @constructor
     * @param name - The rendering pipeline name (default: "")
     * @param hdr - If high dynamic range textures should be used (default: true)
     * @param scene - The scene linked to this pipeline (default: the last created scene)
     * @param cameras - The array of cameras that the rendering pipeline will be attached to (default: scene.cameras)
     * @param automaticBuild - if false, you will have to manually call prepare() to update the pipeline (default: true)
     */
    constructor(name: String? = definedExternally, hdr: Boolean? = definedExternally, scene: Scene? = definedExternally, cameras: Array<Camera>? = definedExternally, automaticBuild: Boolean? = definedExternally)
    /**
     * Force the compilation of the entire pipeline.
     */
    fun prepare()
    /**
     * Adds a camera to the pipeline
     * @param camera the camera to be added
     */
    fun addCamera(camera: Camera)
    /**
     * Removes a camera from the pipeline
     * @param camera the camera to remove
     */
    fun removeCamera(camera: Camera)
    /**
     * Serialize the rendering pipeline (Used when exporting)
     * @returns the serialized object
     */
    fun serialize(): Any
    
    companion object {
        /**
         * Parse the serialized pipeline
         * @param source Source pipeline.
         * @param scene The scene to load the pipeline to.
         * @param rootUrl The URL of the serialized pipeline.
         * @returns An instantiated pipeline from the serialized object.
         */
        fun Parse(source: Any, scene: Scene, rootUrl: String): DefaultRenderingPipeline
    }
}

external class PostProcessManager

external class SharpenPostProcess
external class FxaaPostProcess
external class ImageProcessingPostProcess
external class ChromaticAberrationPostProcess
external class GrainPostProcess

external class DepthOfFieldEffect
external class DepthOfFieldEffectBlurLevel
external class GlowLayer
