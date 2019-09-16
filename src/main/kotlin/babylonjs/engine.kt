@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals")
package babylonjs

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int32Array
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.WebGLBuffer
import org.khronos.webgl.WebGLContextAttributes
import org.khronos.webgl.WebGLContextEvent
import org.khronos.webgl.WebGLProgram
import org.khronos.webgl.WebGLRenderingContext
import org.khronos.webgl.WebGLUniformLocation
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.pointerevents.PointerEvent
import org.w3c.files.Blob
import org.w3c.media.WebGLPipelineContext
import org.w3c.media.WebGLVertexArrayObject

/** Interface defining initialization parameters for Engine class */
external interface EngineOptions: WebGLContextAttributes {
    /**
     * Defines if the engine should no exceed a specified device ratio
     * @see [https://developer.mozilla.org/en-US/docs/Web/API/Window/devicePixelRatio]
     */
    var limitDeviceRatio: Number?
    /**
     * Defines if webvr should be enabled automatically
     * @see [http://doc.babylonjs.com/how_to/webvr_camera]
     */
    var autoEnableWebVR: Boolean?
    /**
     * Defines if webgl2 should be turned off even if supported
     * @see [http://doc.babylonjs.com/features/webgl2]
     */
    var disableWebGL2Support: Boolean?
    /**
     * Defines if webaudio should be initialized as well
     * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music]
     */
    var audioEngine: Boolean?
    /**
     * Defines if animations should run using a deterministic lock step
     * @see [http://doc.babylonjs.com/babylon101/animations#deterministic-lockstep]
     */
    var deterministicLockstep: Boolean?
    /** Defines the maximum steps to use with deterministic lock step mode */
    var lockstepMaxSteps: Number?
    /**
     * Defines that engine should ignore context lost events
     * If this event happens when this parameter is true, you will have to reload the page to restore rendering
     */
    var doNotHandleContextLost: Boolean?
    /**
     * Defines that engine should ignore modifying touch action attribute and style
     * If not handle, you might need to set it up on your side for expected touch devices behavior.
     */
    var doNotHandleTouchAction: Boolean?
    /**
     * Defines that engine should compile shaders with high precision floats (if supported). True by default
     */
    var useHighPrecisionFloats: Boolean?
}

/**
 * The engine class is responsible for interfacing with all lower-level APIs such as WebGL and Audio
 */
external class Engine {

    /**
     * Creates a new engine
     * @param canvas defines the canvas use for rendering.
     * @param antialias defines enable antialiasing (default: false)
     * @param options defines further options to be sent to the getContext() function
     * @param adaptToDeviceRatio defines whether to adapt to the device's viewport characteristics (default: false)
     */
    constructor(canvas: HTMLCanvasElement?, antialias: Boolean? = definedExternally, options: EngineOptions? = definedExternally, adaptToDeviceRatio: Boolean? = definedExternally)

    /**
     * Creates a new engine
     * @param context defines WebGL context to use for rendering. Babylon.js will not hook events on the canvas (like pointers, keyboards, etc...) so no event observables will be available. This is mostly used when Babylon.js is used as a plugin on a system which alreay used the WebGL context
     * @param antialias defines enable antialiasing (default: false)
     * @param options defines further options to be sent to the getContext() function
     * @param adaptToDeviceRatio defines whether to adapt to the device's viewport characteristics (default: false)
     */
    constructor(context: WebGLRenderingContext, antialias: Boolean? = definedExternally, options: EngineOptions? = definedExternally, adaptToDeviceRatio: Boolean? = definedExternally)
    /**
     * Returns a String describing the current engine
     */
    val description: String
    /**
     * Gets or sets a Boolean that indicates if textures must be forced to power of 2 size even if not required
     */
    var forcePOTTextures: Boolean
    /**
     * Gets a Boolean indicating if the engine is currently rendering in fullscreen mode
     */
    var isFullscreen: Boolean
    /**
     * Gets a Boolean indicating if the pointer is currently locked
     */
    var isPointerLock: Boolean
    /**
     * Gets or sets a Boolean indicating if back faces must be culled (true by default)
     */
    var cullBackFaces: Boolean
    /**
     * Gets or sets a Boolean indicating if the engine must keep rendering even if the window is not in foregroun
     */
    var renderEvenInBackground: Boolean
    /**
     * Gets or sets a Boolean indicating that cache can be kept between frames
     */
    var preventCacheWipeBetweenFrames: Boolean
    /**
     * Gets or sets a Boolean to enable/disable IndexedDB support and avoid XHR on .manifest
     **/
    var enableOfflineSupport: Boolean
    /**
     * Gets or sets a Boolean to enable/disable checking manifest if IndexedDB support is enabled (js will always consider the database is up to date)
     **/
    var disableManifestCheck: Boolean
    /**
     * Gets the list of created scenes
     */
    var scenes: Array<Scene>
    /**
     * Event raised when a new scene is created
     */
    var onNewSceneAddedObservable: Observable<Scene>
    /**
     * Gets the list of created postprocesses
     */
    var postProcesses: Array<PostProcess>
    /** Gets or sets a Boolean indicating if the engine should validate programs after compilation */
    var validateShaderPrograms: Boolean
    /**
     * Observable event triggered each time the rendering canvas is resized
     */
    var onResizeObservable: Observable<Engine>
    /**
     * Observable event triggered each time the canvas loses focus
     */
    var onCanvasBlurObservable: Observable<Engine>
    /**
     * Observable event triggered each time the canvas gains focus
     */
    var onCanvasFocusObservable: Observable<Engine>
    /**
     * Observable event triggered each time the canvas receives pointerout event
     */
    var onCanvasPointerOutObservable: Observable<PointerEvent>
    /**
     * Observable event triggered before each texture is initialized
     */
    var onBeforeTextureInitObservable: Observable<Texture>
    /**
     * Gets or sets a Boolean indicating that uniform buffers must be disabled even if they are supported
     */
    var disableUniformBuffers: Boolean
    /**
     * Gets a Boolean indicating that the engine supports uniform buffers
     * @see [http://doc.babylonjs.com/features/webgl2#uniform-buffer-objets]
     */
    val supportsUniformBuffers: Boolean
    /**
     * Observable raised when the engine begins a new frame
     */
    var onBeginFrameObservable: Observable<Engine>
    /**
     * If set, will be used to request the next animation frame for the render loop
     */
    var customAnimationFrameRequester: ICustomAnimationFrameRequester?
    /**
     * Observable raised when the engine ends the current frame
     */
    var onEndFrameObservable: Observable<Engine>
    /**
     * Observable raised when the engine is about to compile a shader
     */
    var onBeforeShaderCompilationObservable: Observable<Engine>
    /**
     * Observable raised when the engine has jsut compiled a shader
     */
    var onAfterShaderCompilationObservable: Observable<Engine>
    /**
     * Gets a Boolean indicating that only power of 2 textures are supported
     * Please note that you can still use non power of 2 textures but in this case the engine will forcefully convert them
     */
    val needPOTTextures: Boolean
    /**
     * Observable signaled when a context lost event is raised
     */
    var onContextLostObservable: Observable<Engine>
    /**
     * Observable signaled when a context restored event is raised
     */
    var onContextRestoredObservable: Observable<Engine>
    var _doNotHandleContextLost: Boolean
    /**
     * Gets or sets a Boolean indicating if resources should be retained to be able to handle context lost events
     * @see [http://doc.babylonjs.com/how_to/optimizing_your_scene#handling-webgl-context-lost]
     */
    var doNotHandleContextLost: Boolean
    /**
     * Turn this value on if you want to pause FPS computation when in background
     */
    var disablePerformanceMonitorInBackground: Boolean
    /**
     * Gets the performance monitor attached to this engine
     * @see [http://doc.babylonjs.com/how_to/optimizing_your_scene#engineinstrumentation]
     */
    val performanceMonitor: PerformanceMonitor
    /**
     * Gets or sets a Boolean indicating that vertex array object must be disabled even if they are supported
     */
    var disableVertexArrayObjects: Boolean
    /**
     * Gets the list of texture formats supported
     */
    val texturesSupported: Array<String>
    /**
     * Gets the list of texture formats in use
     */
    val textureFormatInUse: String?
    /**
     * Gets the current viewport
     */
    val currentViewport: Viewport?
    /**
     * Gets the default empty texture
     */
    val emptyTexture: InternalTexture
    /**
     * Gets the default empty 3D texture
     */
    val emptyTexture3D: InternalTexture
    /**
     * Gets the default empty cube texture
     */
    val emptyCubeTexture: InternalTexture
    /**
     * Defines whether the engine has been created with the premultipliedAlpha option on or not.
     */
    val premultipliedAlpha: Boolean
    
    /**
     * Initializes a webVR display and starts listening to display change events
     * The onVRDisplayChangedObservable will be notified upon these changes
     * @returns The onVRDisplayChangedObservable
     */
    fun initWebVR(): Observable<IDisplayChangedEventArgs>
    /**
     * Call this function to leave webVR mode
     * Will do nothing if webVR is not supported or if there is no webVR device
     * @see [http://doc.babylonjs.com/how_to/webvr_camera]
     */
    fun disableVR()
    /**
     * Gets a Boolean indicating that the system is in VR mode and is presenting
     * @returns true if VR mode is engaged
     */
    fun isVRPresenting(): Boolean
    /**
     * Gets a Boolean indicating if all created effects are ready
     * @returns true if all effects are ready
     */
    fun areAllEffectsReady(): Boolean
    /**
     * Gets version of the current webGL context
     */
    val webGLVersion: Number
    /**
     * Returns true if the stencil buffer has been enabled through the creation option of the context.
     */
    val isStencilEnable: Boolean
    /**
     * Reset the texture cache to empty state
     */
    fun resetTextureCache()
    /**
     * Gets a Boolean indicating that the engine is running in deterministic lock step mode
     * @see [http://doc.babylonjs.com/babylon101/animations#deterministic-lockstep]
     * @returns true if engine is in deterministic lock step mode
     */
    fun isDeterministicLockStep(): Boolean
    /**
     * Gets the max steps when engine is running in deterministic lock step
     * @see [http://doc.babylonjs.com/babylon101/animations#deterministic-lockstep]
     * @returns the max steps
     */
    fun getLockstepMaxSteps(): Number
    
    /**
     * Gets an object containing information about the current webGL context
     * @returns an object containing the vender, the renderer and the version of the current webGL context
     */
    fun getGlInfo(): Any /*{
        var vendor: String
        var renderer: String
        var version: String
    }*/
    /**
     * Gets current aspect ratio
     * @param camera defines the camera to use to get the aspect ratio
     * @param useScreen defines if screen size must be used (or the current render target if Any)
     * @returns a Number defining the aspect ratio
     */
    fun getAspectRatio(camera: Camera, useScreen: Boolean?): Number
    /**
     * Gets current screen aspect ratio
     * @returns a Number defining the aspect ratio
     */
    fun getScreenAspectRatio(): Number
    /**
     * Gets the current render width
     * @param useScreen defines if screen size must be used (or the current render target if Any)
     * @returns a Number defining the current render width
     */
    fun getRenderWidth(useScreen: Boolean?): Number
    /**
     * Gets the current render height
     * @param useScreen defines if screen size must be used (or the current render target if Any)
     * @returns a Number defining the current render height
     */
    fun getRenderHeight(useScreen: Boolean?): Number
    /**
     * Gets the HTML canvas attached with the current webGL context
     * @returns a HTML canvas
     */
    fun getRenderingCanvas(): HTMLCanvasElement?
    /**
     * Gets the client rect of the HTML canvas attached with the current webGL context
     * @returns a client rectanglee
     */
    fun getRenderingCanvasClientRect(): ClientRect?
    /**
     * Defines the hardware scaling level.
     * By default the hardware scaling level is computed from the window device ratio.
     * if level = 1 then the engine will render at the exact resolution of the canvas. If level = 0.5 then the engine will render at twice the size of the canvas.
     * @param level defines the level to use
     */
    fun setHardwareScalingLevel(level: Number)
    /**
     * Gets the current hardware scaling level.
     * By default the hardware scaling level is computed from the window device ratio.
     * if level = 1 then the engine will render at the exact resolution of the canvas. If level = 0.5 then the engine will render at twice the size of the canvas.
     * @returns a Number indicating the current hardware scaling level
     */
    fun getHardwareScalingLevel(): Number
    /**
     * Gets the list of loaded textures
     * @returns an array containing all loaded textures
     */
    fun getLoadedTexturesCache(): Array<InternalTexture>
    /**
     * Gets the object containing all engine capabilities
     * @returns the EngineCapabilities object
     */
    fun getCaps(): EngineCapabilities
    /**
     * Gets the current depth function
     * @returns a Number defining the depth function
     */
    fun getDepthFunction(): Number?
    /**
     * Sets the current depth function
     * @param depthFunc defines the function to use
     */
    fun setDepthFunction(depthFunc: Number)
    /**
     * Sets the current depth function to GREATER
     */
    fun setDepthFunctionToGreater()
    /**
     * Sets the current depth function to GEQUAL
     */
    fun setDepthFunctionToGreaterOrEqual()
    /**
     * Sets the current depth function to LESS
     */
    fun setDepthFunctionToLess()
    /**
     * Caches the the state of the stencil buffer
     */
    fun cacheStencilState()
    /**
     * Restores the state of the stencil buffer
     */
    fun restoreStencilState()
    /**
     * Sets the current depth function to LEQUAL
     */
    fun setDepthFunctionToLessOrEqual()
    /**
     * Gets a Boolean indicating if stencil buffer is enabled
     * @returns the current stencil buffer state
     */
    fun getStencilBuffer(): Boolean
    /**
     * Enable or disable the stencil buffer
     * @param enable defines if the stencil buffer must be enabled or disabled
     */
    fun setStencilBuffer(enable: Boolean)
    /**
     * Gets the current stencil mask
     * @returns a Number defining the new stencil mask to use
     */
    fun getStencilMask(): Number
    /**
     * Sets the current stencil mask
     * @param mask defines the new stencil mask to use
     */
    fun setStencilMask(mask: Number)
    /**
     * Gets the current stencil function
     * @returns a Number defining the stencil function to use
     */
    fun getStencilFunction(): Number
    /**
     * Gets the current stencil reference value
     * @returns a Number defining the stencil reference value to use
     */
    fun getStencilFunctionReference(): Number
    /**
     * Gets the current stencil mask
     * @returns a Number defining the stencil mask to use
     */
    fun getStencilFunctionMask(): Number
    /**
     * Sets the current stencil function
     * @param stencilFunc defines the new stencil function to use
     */
    fun setStencilFunction(stencilFunc: Number)
    /**
     * Sets the current stencil reference
     * @param reference defines the new stencil reference to use
     */
    fun setStencilFunctionReference(reference: Number)
    /**
     * Sets the current stencil mask
     * @param mask defines the new stencil mask to use
     */
    fun setStencilFunctionMask(mask: Number)
    /**
     * Gets the current stencil operation when stencil fails
     * @returns a Number defining stencil operation to use when stencil fails
     */
    fun getStencilOperationFail(): Number
    /**
     * Gets the current stencil operation when depth fails
     * @returns a Number defining stencil operation to use when depth fails
     */
    fun getStencilOperationDepthFail(): Number
    /**
     * Gets the current stencil operation when stencil passes
     * @returns a Number defining stencil operation to use when stencil passes
     */
    fun getStencilOperationPass(): Number
    /**
     * Sets the stencil operation to use when stencil fails
     * @param operation defines the stencil operation to use when stencil fails
     */
    fun setStencilOperationFail(operation: Number)
    /**
     * Sets the stencil operation to use when depth fails
     * @param operation defines the stencil operation to use when depth fails
     */
    fun setStencilOperationDepthFail(operation: Number)
    /**
     * Sets the stencil operation to use when stencil passes
     * @param operation defines the stencil operation to use when stencil passes
     */
    fun setStencilOperationPass(operation: Number)
    /**
     * Sets a Boolean indicating if the dithering state is enabled or disabled
     * @param value defines the dithering state
     */
    fun setDitheringState(value: Boolean)
    /**
     * Sets a Boolean indicating if the rasterizer state is enabled or disabled
     * @param value defines the rasterizer state
     */
    fun setRasterizerState(value: Boolean)
    /**
     * stop executing a render loop function and remove it from the execution array
     * @param renderFunction defines the function to be removed. If not provided all functions will be removed.
     */
    fun stopRenderLoop(renderFunction: (() -> Unit)?)
    /**
     * Register and execute a render loop. The engine can have more than one render function
     * @param renderFunction defines the function to continuously execute
     */
    fun runRenderLoop(renderFunction: () -> Unit)
    /**
     * Toggle full screen mode
     * @param requestPointerLock defines if a pointer lock should be requested from the user
     */
    fun switchFullscreen(requestPointerLock: Boolean)
    /**
     * Enters full screen mode
     * @param requestPointerLock defines if a pointer lock should be requested from the user
     */
    fun enterFullscreen(requestPointerLock: Boolean)
    /**
     * Exits full screen mode
     */
    fun exitFullscreen()
    /**
     * Clear the current render buffer or the current render target (if Any is set up)
     * @param color defines the color to use
     * @param backBuffer defines if the back buffer must be cleared
     * @param depth defines if the depth buffer must be cleared
     * @param stencil defines if the stencil buffer must be cleared
     */
    fun clear(color: Color4?, backBuffer: Boolean, depth: Boolean, stencil: Boolean?)
    /**
     * Executes a scissor clear (ie. a clear on a specific portion of the screen)
     * @param x defines the x-coordinate of the top left corner of the clear rectangle
     * @param y defines the y-coordinate of the corner of the clear rectangle
     * @param width defines the width of the clear rectangle
     * @param height defines the height of the clear rectangle
     * @param clearColor defines the clear color
     */
    fun scissorClear(x: Number, y: Number, width: Number, height: Number, clearColor: Color4)
    /**
     * Enable scissor test on a specific rectangle (ie. render will only be executed on a specific portion of the screen)
     * @param x defines the x-coordinate of the top left corner of the clear rectangle
     * @param y defines the y-coordinate of the corner of the clear rectangle
     * @param width defines the width of the clear rectangle
     * @param height defines the height of the clear rectangle
     */
    fun enableScissor(x: Number, y: Number, width: Number, height: Number)
    /**
     * Disable previously set scissor test rectangle
     */
    fun disableScissor()
    /**
     * Set the WebGL's viewport
     * @param viewport defines the viewport element to be used
     * @param requiredWidth defines the width required for rendering. If not provided the rendering canvas' width is used
     * @param requiredHeight defines the height required for rendering. If not provided the rendering canvas' height is used
     */
    fun setViewport(viewport: Viewport, requiredWidth: Number?, requiredHeight: Number?)
    /**
     * Directly set the WebGL Viewport
     * @param x defines the x coordinate of the viewport (in screen space)
     * @param y defines the y coordinate of the viewport (in screen space)
     * @param width defines the width of the viewport (in screen space)
     * @param height defines the height of the viewport (in screen space)
     * @return the current viewport Object (if Any) that is being replaced by this call. You can restore this viewport later on to go back to the original state
     */
    fun setDirectViewport(x: Number, y: Number, width: Number, height: Number): Viewport?
    /**
     * Begin a new frame
     */
    fun beginFrame()
    /**
     * Enf the current frame
     */
    fun endFrame()
    /**
     * Resize the view according to the canvas' size
     */
    fun resize()
    /**
     * Force a specific size of the canvas
     * @param width defines the new canvas' width
     * @param height defines the new canvas' height
     */
    fun setSize(width: Number, height: Number)
    /**
     * Binds the frame buffer to the specified texture.
     * @param texture The texture to render to or null for the default canvas
     * @param faceIndex The face of the texture to render to in case of cube texture
     * @param requiredWidth The width of the target to render to
     * @param requiredHeight The height of the target to render to
     * @param forceFullscreenViewport Forces the viewport to be the entire texture/screen if true
     * @param depthStencilTexture The depth stencil texture to use to render
     * @param lodLevel defines le lod level to bind to the frame buffer
     */
    fun bindFramebuffer(texture: InternalTexture, faceIndex: Number?, requiredWidth: Number?, requiredHeight: Number?, forceFullscreenViewport: Boolean?, depthStencilTexture: InternalTexture?, lodLevel: Number?)
    /**
     * Unbind the current render target texture from the webGL context
     * @param texture defines the render target texture to unbind
     * @param disableGenerateMipMaps defines a Boolean indicating that mipmaps must not be generated
     * @param onBeforeUnbind defines a function which will be called before the effective unbind
     */
    fun unBindFramebuffer(texture: InternalTexture, disableGenerateMipMaps: Boolean?, onBeforeUnbind: (() -> Unit)?)
    /**
     * Force the mipmap generation for the given render target texture
     * @param texture defines the render target texture to use
     */
    fun generateMipMapsForCubemap(texture: InternalTexture)
    /**
     * Force a webGL flush (ie. a flush of all waiting webGL commands)
     */
    fun flushFramebuffer()
    /**
     * Unbind the current render target and bind the default framebuffer
     */
    fun restoreDefaultFramebuffer()
    /**
     * Create an uniform buffer
     * @see [http://doc.babylonjs.com/features/webgl2#uniform-buffer-objets]
     * @param elements defines the content of the uniform buffer
     * @returns the webGL uniform buffer
     */
    fun createUniformBuffer(elements: FloatArray): DataBuffer
    /**
     * Create a dynamic uniform buffer
     * @see [http://doc.babylonjs.com/features/webgl2#uniform-buffer-objets]
     * @param elements defines the content of the uniform buffer
     * @returns the webGL uniform buffer
     */
    fun createDynamicUniformBuffer(elements: FloatArray): DataBuffer
    /**
     * Update an existing uniform buffer
     * @see [http://doc.babylonjs.com/features/webgl2#uniform-buffer-objets]
     * @param uniformBuffer defines the target uniform buffer
     * @param elements defines the content to update
     * @param offset defines the offset in the uniform buffer where update should start
     * @param count defines the size of the data to update
     */
    fun updateUniformBuffer(uniformBuffer: DataBuffer, elements: FloatArray, offset: Number?, count: Number?)
    /**
     * Creates a vertex buffer
     * @param data the data for the vertex buffer
     * @returns the new WebGL static buffer
     */
    fun createVertexBuffer(data: DataArray): DataBuffer
    /**
     * Creates a dynamic vertex buffer
     * @param data the data for the dynamic vertex buffer
     * @returns the new WebGL dynamic buffer
     */
    fun createDynamicVertexBuffer(data: DataArray): DataBuffer
    /**
     * Update a dynamic index buffer
     * @param indexBuffer defines the target index buffer
     * @param indices defines the data to update
     * @param offset defines the offset in the target index buffer where update should start
     */
    fun updateDynamicIndexBuffer(indexBuffer: DataBuffer, indices: IndicesArray, offset: Number?)
    /**
     * Updates a dynamic vertex buffer.
     * @param vertexBuffer the vertex buffer to update
     * @param data the data used to update the vertex buffer
     * @param byteOffset the byte offset of the data
     * @param byteLength the byte length of the data
     */
    fun updateDynamicVertexBuffer(vertexBuffer: DataBuffer, data: DataArray, byteOffset: Number?, byteLength: Number?)
    /**
     * Creates a new index buffer
     * @param indices defines the content of the index buffer
     * @param updatable defines if the index buffer must be updatable
     * @returns a new webGL buffer
     */
    fun createIndexBuffer(indices: IndicesArray, updatable: Boolean?): DataBuffer
    /**
     * Bind a webGL buffer to the webGL context
     * @param buffer defines the buffer to bind
     */
    fun bindArrayBuffer(buffer: DataBuffer?)
    /**
     * Bind an uniform buffer to the current webGL context
     * @param buffer defines the buffer to bind
     */
    fun bindUniformBuffer(buffer: DataBuffer?)
    /**
     * Bind a buffer to the current webGL context at a given location
     * @param buffer defines the buffer to bind
     * @param location defines the index where to bind the buffer
     */
    fun bindUniformBufferBase(buffer: DataBuffer, location: Number)
    /**
     * Bind a specific block at a given index in a specific shader program
     * @param pipelineContext defines the pipeline context to use
     * @param blockName defines the block name
     * @param index defines the index where to bind the block
     */
    fun bindUniformBlock(pipelineContext: IPipelineContext, blockName: String, index: Number)
    /**
     * update the bound buffer with the given data
     * @param data defines the data to update
     */
    fun updateArrayBuffer(data: Float32Array)
    /**
     * Records a vertex array object
     * @see [http://doc.babylonjs.com/features/webgl2#vertex-array-objects]
     * @param vertexBuffers defines the list of vertex buffers to store
     * @param indexBuffer defines the index buffer to store
     * @param effect defines the effect to store
     * @returns the new vertex array object
     */
    fun recordVertexArrayObject(vertexBuffers: Any /*{ [key: String]: VertexBuffer; }*/, indexBuffer: DataBuffer?, effect: Effect): WebGLVertexArrayObject
    /**
     * Bind a specific vertex array object
     * @see [http://doc.babylonjs.com/features/webgl2#vertex-array-objects]
     * @param vertexArrayObject defines the vertex array object to bind
     * @param indexBuffer defines the index buffer to bind
     */
    fun bindVertexArrayObject(vertexArrayObject: WebGLVertexArrayObject, indexBuffer: DataBuffer?)
    /**
     * Bind webGl buffers directly to the webGL context
     * @param vertexBuffer defines the vertex buffer to bind
     * @param indexBuffer defines the index buffer to bind
     * @param vertexDeclaration defines the vertex declaration to use with the vertex buffer
     * @param vertexStrideSize defines the vertex stride of the vertex buffer
     * @param effect defines the effect associated with the vertex buffer
     */
    fun bindBuffersDirectly(vertexBuffer: DataBuffer, indexBuffer: DataBuffer, vertexDeclaration: Array<Number>, vertexStrideSize: Number, effect: Effect)
    /**
     * Bind a list of vertex buffers to the webGL context
     * @param vertexBuffers defines the list of vertex buffers to bind
     * @param indexBuffer defines the index buffer to bind
     * @param effect defines the effect associated with the vertex buffers
     */
    fun bindBuffers(vertexBuffers: Any/*{ [key: String]: VertexBuffer?; }*/, indexBuffer: DataBuffer?, effect: Effect)
    /**
     * Unbind all instance attributes
     */
    fun unbindInstanceAttributes()
    /**
     * Release and free the memory of a vertex array object
     * @param vao defines the vertex array object to delete
     */
    fun releaseVertexArrayObject(vao: WebGLVertexArrayObject)
    /**
     * Creates a webGL buffer to use with instanciation
     * @param capacity defines the size of the buffer
     * @returns the webGL buffer
     */
    fun createInstancesBuffer(capacity: Number): DataBuffer
    /**
     * Delete a webGL buffer used with instanciation
     * @param buffer defines the webGL buffer to delete
     */
    fun deleteInstancesBuffer(buffer: WebGLBuffer)
    /**
     * Update the content of a webGL buffer used with instanciation and bind it to the webGL context
     * @param instancesBuffer defines the webGL buffer to update and bind
     * @param data defines the data to store in the buffer
     * @param offsetLocations defines the offsets or attributes information used to determine where data must be stored in the buffer
     */
    fun updateAndBindInstancesBuffer(instancesBuffer: DataBuffer, data: Float32Array, offsetLocations: Array<Number>)
    /**
     * Update the content of a webGL buffer used with instanciation and bind it to the webGL context
     * @param instancesBuffer defines the webGL buffer to update and bind
     * @param data defines the data to store in the buffer
     * @param offsetLocations defines the offsets or attributes information used to determine where data must be stored in the buffer
     */
    fun updateAndBindInstancesBuffer(instancesBuffer: DataBuffer, data: Float32Array, offsetLocations: Array<InstancingAttributeInfo>)
    /**
     * Apply all cached states (depth, culling, stencil and alpha)
     */
    fun applyStates()
    /**
     * Send a draw order
     * @param useTriangles defines if triangles must be used to draw (else wireframe will be used)
     * @param indexStart defines the starting index
     * @param indexCount defines the Number of index to draw
     * @param instancesCount defines the Number of instances to draw (if instanciation is enabled)
     */
    fun draw(useTriangles: Boolean, indexStart: Number, indexCount: Number, instancesCount: Number?)
    /**
     * Draw a list of points
     * @param verticesStart defines the index of first vertex to draw
     * @param verticesCount defines the count of vertices to draw
     * @param instancesCount defines the Number of instances to draw (if instanciation is enabled)
     */
    fun drawPointClouds(verticesStart: Number, verticesCount: Number, instancesCount: Number?)
    /**
     * Draw a list of unindexed primitives
     * @param useTriangles defines if triangles must be used to draw (else wireframe will be used)
     * @param verticesStart defines the index of first vertex to draw
     * @param verticesCount defines the count of vertices to draw
     * @param instancesCount defines the Number of instances to draw (if instanciation is enabled)
     */
    fun drawUnIndexed(useTriangles: Boolean, verticesStart: Number, verticesCount: Number, instancesCount: Number?)
    /**
     * Draw a list of indexed primitives
     * @param fillMode defines the primitive to use
     * @param indexStart defines the starting index
     * @param indexCount defines the Number of index to draw
     * @param instancesCount defines the Number of instances to draw (if instanciation is enabled)
     */
    fun drawElementsType(fillMode: Number, indexStart: Number, indexCount: Number, instancesCount: Number?)
    /**
     * Draw a list of unindexed primitives
     * @param fillMode defines the primitive to use
     * @param verticesStart defines the index of first vertex to draw
     * @param verticesCount defines the count of vertices to draw
     * @param instancesCount defines the Number of instances to draw (if instanciation is enabled)
     */
    fun drawArraysType(fillMode: Number, verticesStart: Number, verticesCount: Number, instancesCount: Number?)
    /**
     * Create a new effect (used to store vertex/fragment shaders)
     * @param baseName defines the base name of the effect (The name of file without .fragment.fx or .vertex.fx)
     * @param attributesNamesOrOptions defines either a list of attribute names or an EffectCreationOptions object
     * @param uniformsNamesOrEngine defines either a list of uniform names or the engine to use
     * @param samplers defines an array of String used to represent textures
     * @param defines defines the String containing the defines to use to compile the shaders
     * @param fallbacks defines the list of potential fallbacks to use if shader conmpilation fails
     * @param onCompiled defines a function to call when the effect creation is successful
     * @param onError defines a function to call when the effect creation has failed
     * @param indexParameters defines an object containing the index values to use to compile shaders (like the maximum Number of simultaneous lights)
     * @returns the new Effect
     */
    fun createEffect(
        baseName: Any, rattributesNamesOrOptions: Array<String> /*| EffectCreationOptions*/,
        uniformsNamesOrEngine: Array<String> /*| Engine*/, samplers: Array<String>?,
        defines: String?, fallbacks: EffectFallbacks?,
        onCompiled: ((effect: Effect) -> Unit)?, onError: ((effect: Effect, errors: String) -> Unit)?,
        indexParameters: Any?
    ): Effect
    /**
     * Directly creates a webGL program
     * @param pipelineContext  defines the pipeline context to attach to
     * @param vertexCode defines the vertex shader code to use
     * @param fragmentCode defines the fragment shader code to use
     * @param context defines the webGL context to use (if not set, the current one will be used)
     * @param transformFeedbackVaryings defines the list of transform feedback varyings to use
     * @returns the new webGL program
     */
    fun createRawShaderProgram(pipelineContext: IPipelineContext, vertexCode: String, fragmentCode: String, context: WebGLRenderingContext?, transformFeedbackVaryings: Array<String>?): WebGLProgram
    /**
     * Creates a webGL program
     * @param pipelineContext  defines the pipeline context to attach to
     * @param vertexCode  defines the vertex shader code to use
     * @param fragmentCode defines the fragment shader code to use
     * @param defines defines the String containing the defines to use to compile the shaders
     * @param context defines the webGL context to use (if not set, the current one will be used)
     * @param transformFeedbackVaryings defines the list of transform feedback varyings to use
     * @returns the new webGL program
     */
    fun createShaderProgram(pipelineContext: IPipelineContext, vertexCode: String, fragmentCode: String, defines: String?, context: WebGLRenderingContext?, transformFeedbackVaryings: Array<String>?): WebGLProgram
    /**
     * Creates a new pipeline context
     * @returns the new pipeline
     */
    fun createPipelineContext(): WebGLPipelineContext
    /**
     * Gets the list of webGL uniform locations associated with a specific program based on a list of uniform names
     * @param pipelineContext defines the pipeline context to use
     * @param uniformsNames defines the list of uniform names
     * @returns an array of webGL uniform locations
     */
    fun getUniforms(pipelineContext: IPipelineContext, uniformsNames: Array<String>): Array<WebGLUniformLocation>?
    /**
     * Gets the lsit of active attributes for a given webGL program
     * @param pipelineContext defines the pipeline context to use
     * @param attributesNames defines the list of attribute names to get
     * @returns an array of indices indicating the offset of each attribute
     */
    fun getAttributes(pipelineContext: IPipelineContext, attributesNames: Array<String>): Array<Number>
    /**
     * Activates an effect, mkaing it the current one (ie. the one used for rendering)
     * @param effect defines the effect to activate
     */
    fun enableEffect(effect: Effect?)
    /**
     * Set the value of an uniform to an array of int32
     * @param uniform defines the webGL uniform location where to store the value
     * @param array defines the array of int32 to store
     */
    fun setIntArray(uniform: WebGLUniformLocation?, array: Int32Array)
    /**
     * Set the value of an uniform to an array of int32 (stored as vec2)
     * @param uniform defines the webGL uniform location where to store the value
     * @param array defines the array of int32 to store
     */
    fun setIntArray2(uniform: WebGLUniformLocation?, array: Int32Array)
    /**
     * Set the value of an uniform to an array of int32 (stored as vec3)
     * @param uniform defines the webGL uniform location where to store the value
     * @param array defines the array of int32 to store
     */
    fun setIntArray3(uniform: WebGLUniformLocation?, array: Int32Array)
    /**
     * Set the value of an uniform to an array of int32 (stored as vec4)
     * @param uniform defines the webGL uniform location where to store the value
     * @param array defines the array of int32 to store
     */
    fun setIntArray4(uniform: WebGLUniformLocation?, array: Int32Array)
    /**
     * Set the value of an uniform to an array of float32
     * @param uniform defines the webGL uniform location where to store the value
     * @param array defines the array of float32 to store
     */
    fun setFloatArray(uniform: WebGLUniformLocation?, array: Float32Array)
    /**
     * Set the value of an uniform to an array of float32 (stored as vec2)
     * @param uniform defines the webGL uniform location where to store the value
     * @param array defines the array of float32 to store
     */
    fun setFloatArray2(uniform: WebGLUniformLocation?, array: Float32Array)
    /**
     * Set the value of an uniform to an array of float32 (stored as vec3)
     * @param uniform defines the webGL uniform location where to store the value
     * @param array defines the array of float32 to store
     */
    fun setFloatArray3(uniform: WebGLUniformLocation?, array: Float32Array)
    /**
     * Set the value of an uniform to an array of float32 (stored as vec4)
     * @param uniform defines the webGL uniform location where to store the value
     * @param array defines the array of float32 to store
     */
    fun setFloatArray4(uniform: WebGLUniformLocation?, array: Float32Array)
    /**
     * Set the value of an uniform to an array of Number
     * @param uniform defines the webGL uniform location where to store the value
     * @param array defines the array of Number to store
     */
    fun setArray(uniform: WebGLUniformLocation?, array: Array<Number>)
    /**
     * Set the value of an uniform to an array of Number (stored as vec2)
     * @param uniform defines the webGL uniform location where to store the value
     * @param array defines the array of Number to store
     */
    fun setArray2(uniform: WebGLUniformLocation?, array: Array<Number>)
    /**
     * Set the value of an uniform to an array of Number (stored as vec3)
     * @param uniform defines the webGL uniform location where to store the value
     * @param array defines the array of Number to store
     */
    fun setArray3(uniform: WebGLUniformLocation?, array: Array<Number>)
    /**
     * Set the value of an uniform to an array of Number (stored as vec4)
     * @param uniform defines the webGL uniform location where to store the value
     * @param array defines the array of Number to store
     */
    fun setArray4(uniform: WebGLUniformLocation?, array: Array<Number>)
    /**
     * Set the value of an uniform to an array of float32 (stored as matrices)
     * @param uniform defines the webGL uniform location where to store the value
     * @param matrices defines the array of float32 to store
     */
    fun setMatrices(uniform: WebGLUniformLocation?, matrices: Float32Array)
    /**
     * Set the value of an uniform to a matrix
     * @param uniform defines the webGL uniform location where to store the value
     * @param matrix defines the matrix to store
     */
    fun setMatrix(uniform: WebGLUniformLocation?, matrix: Matrix)
    /**
     * Set the value of an uniform to a matrix (3x3)
     * @param uniform defines the webGL uniform location where to store the value
     * @param matrix defines the Float32Array representing the 3x3 matrix to store
     */
    fun setMatrix3x3(uniform: WebGLUniformLocation?, matrix: Float32Array)
    /**
     * Set the value of an uniform to a matrix (2x2)
     * @param uniform defines the webGL uniform location where to store the value
     * @param matrix defines the Float32Array representing the 2x2 matrix to store
     */
    fun setMatrix2x2(uniform: WebGLUniformLocation?, matrix: Float32Array)
    /**
     * Set the value of an uniform to a Number (int)
     * @param uniform defines the webGL uniform location where to store the value
     * @param value defines the int Number to store
     */
    fun setInt(uniform: WebGLUniformLocation?, value: Number)
    /**
     * Set the value of an uniform to a Number (float)
     * @param uniform defines the webGL uniform location where to store the value
     * @param value defines the float Number to store
     */
    fun setFloat(uniform: WebGLUniformLocation?, value: Number)
    /**
     * Set the value of an uniform to a vec2
     * @param uniform defines the webGL uniform location where to store the value
     * @param x defines the 1st component of the value
     * @param y defines the 2nd component of the value
     */
    fun setFloat2(uniform: WebGLUniformLocation?, x: Number, y: Number)
    /**
     * Set the value of an uniform to a vec3
     * @param uniform defines the webGL uniform location where to store the value
     * @param x defines the 1st component of the value
     * @param y defines the 2nd component of the value
     * @param z defines the 3rd component of the value
     */
    fun setFloat3(uniform: WebGLUniformLocation?, x: Number, y: Number, z: Number)
    /**
     * Set the value of an uniform to a Boolean
     * @param uniform defines the webGL uniform location where to store the value
     * @param bool defines the Boolean to store
     */
    fun setBool(uniform: WebGLUniformLocation?, bool: Number)
    /**
     * Set the value of an uniform to a vec4
     * @param uniform defines the webGL uniform location where to store the value
     * @param x defines the 1st component of the value
     * @param y defines the 2nd component of the value
     * @param z defines the 3rd component of the value
     * @param w defines the 4th component of the value
     */
    fun setFloat4(uniform: WebGLUniformLocation?, x: Number, y: Number, z: Number, w: Number)
    /**
     * Set the value of an uniform to a Color3
     * @param uniform defines the webGL uniform location where to store the value
     * @param color3 defines the color to store
     */
    fun setColor3(uniform: WebGLUniformLocation?, color3: Color3)
    /**
     * Set the value of an uniform to a Color3 and an alpha value
     * @param uniform defines the webGL uniform location where to store the value
     * @param color3 defines the color to store
     * @param alpha defines the alpha component to store
     */
    fun setColor4(uniform: WebGLUniformLocation?, color3: Color3, alpha: Number)
    /**
     * Sets a Color4 on a uniform variable
     * @param uniform defines the uniform location
     * @param color4 defines the value to be set
     */
    fun setDirectColor4(uniform: WebGLUniformLocation?, color4: Color4)
    /**
     * Set various states to the webGL context
     * @param culling defines backface culling state
     * @param zOffset defines the value to apply to zOffset (0 by default)
     * @param force defines if states must be applied even if cache is up to date
     * @param reverseSide defines if culling must be reversed (CCW instead of CW and CW instead of CCW)
     */
    fun setState(culling: Boolean, zOffset: Number?, force: Boolean?, reverseSide: Boolean?)
    /**
     * Set the z offset to apply to current rendering
     * @param value defines the offset to apply
     */
    fun setZOffset(value: Number)
    /**
     * Gets the current value of the zOffset
     * @returns the current zOffset state
     */
    fun getZOffset(): Number
    /**
     * Enable or disable depth buffering
     * @param enable defines the state to set
     */
    fun setDepthBuffer(enable: Boolean)
    /**
     * Gets a Boolean indicating if depth writing is enabled
     * @returns the current depth writing state
     */
    fun getDepthWrite(): Boolean
    /**
     * Enable or disable depth writing
     * @param enable defines the state to set
     */
    fun setDepthWrite(enable: Boolean)
    /**
     * Enable or disable color writing
     * @param enable defines the state to set
     */
    fun setColorWrite(enable: Boolean)
    /**
     * Gets a Boolean indicating if color writing is enabled
     * @returns the current color writing state
     */
    fun getColorWrite(): Boolean
    /**
     * Sets alpha constants used by some alpha blending modes
     * @param r defines the red component
     * @param g defines the green component
     * @param b defines the blue component
     * @param a defines the alpha component
     */
    fun setAlphaConstants(r: Number, g: Number, b: Number, a: Number)
    /**
     * Sets the current alpha mode
     * @param mode defines the mode to use (one of the Engine.ALPHA_XXX)
     * @param noDepthWriteChange defines if depth writing state should remains unchanged (false by default)
     * @see [http://doc.babylonjs.com/resources/transparency_and_how_meshes_are_rendered]
     */
    fun setAlphaMode(mode: Number, noDepthWriteChange: Boolean?)
    /**
     * Gets the current alpha mode
     * @see [http://doc.babylonjs.com/resources/transparency_and_how_meshes_are_rendered]
     * @returns the current alpha mode
     */
    fun getAlphaMode(): Number
    /**
     * Clears the list of texture accessible through engine.
     * This can help preventing texture load conflict due to name collision.
     */
    fun clearInternalTexturesCache()
    /**
     * Force the entire cache to be cleared
     * You should not have to use this function unless your engine needs to share the webGL context with another engine
     * @param bruteForce defines a Boolean to force clearing ALL caches (including stencil, detoh and alpha states)
     */
    fun wipeCaches(bruteForce: Boolean?)
    /**
     * Set the compressed texture format to use, based on the formats you have, and the formats
     * supported by the hardware / browser.
     *
     * Khronos Texture Container (.ktx) files are used to support this.  This format has the
     * advantage of being specifically designed for OpenGL.  Header elements directly correspond
     * to API arguments needed to compressed textures.  This puts the burden on the container
     * generator to house the arcane code for determining these for current & future formats.
     *
     * for description see [https://www.khronos.org/opengles/sdk/tools/KTX/]
     * for file layout see [https://www.khronos.org/opengles/sdk/tools/KTX/file_format_spec/]
     *
     * Note: The result of this call is not taken into account when a texture is base64.
     *
     * @param formatsAvailable defines the list of those format families you have created
     * on your server.  Syntax: '-' + format family + '.ktx'.  (Case and order do not matter.)
     *
     * Current families are astc, dxt, pvrtc, etc2, & etc1.
     * @returns The extension selected.
     */
    fun setTextureFormatToUse(formatsAvailable: Array<String>): String?
    /**
     * Usually called from Texture.ts.
     * Passed information to create a WebGLTexture
     * @param urlArg defines a value which contains one of the following:
     * * A conventional http URL, e.g. '[http://...' or 'file://...']
     * * A base64 String of in-line texture data, e.g. 'data:image/jpg;base64,/...'
     * * An indicator that data being passed using the buffer parameter, e.g. 'data:mytexture.jpg'
     * @param noMipmap defines a Boolean indicating that no mipmaps shall be generated.  Ignored for compressed textures.  They must be in the file
     * @param invertY when true, image is flipped when loaded.  You probably want true. Certain compressed textures may invert this if their default is inverted (eg. ktx)
     * @param scene needed for loading to the correct scene
     * @param samplingMode mode with should be used sample / access the texture (Default: Texture.TRILINEAR_SAMPLINGMODE)
     * @param onLoad optional callback to be called upon successful completion
     * @param onError optional callback to be called upon failure
     * @param buffer a source of a file previously fetched as either a base64 String, an ArrayBuffer (compressed or image format), HTMLImageElement (image format), or a Blob
     * @param fallback an internal argument in case the function must be called again, due to etc1 not having alpha capabilities
     * @param format internal format.  Default: RGB when extension is '.jpg' else RGBA.  Ignored for compressed textures
     * @param forcedExtension defines the extension to use to pick the right loader
     * @param excludeLoaders array of texture loaders that should be excluded when picking a loader for the texture (default: empty array)
     * @returns a InternalTexture for assignment back into BABYLON.Texture
     */
    fun createTexture(
        urlArg: String?, noMipmap: Boolean, invertY: Boolean, scene: Scene?, samplingMode: Number?,
        onLoad: (() -> Unit)?, onError: ((message: String, exception: Any) -> Unit)?, buffer: String?,
        fallback: InternalTexture?, format: Number?, forcedExtension: String?, excludeLoaders: Array<IInternalTextureLoader>?): InternalTexture
    fun createTexture(
        urlArg: String?, noMipmap: Boolean, invertY: Boolean, scene: Scene?, samplingMode: Number?,
        onLoad: (() -> Unit)?, onError: ((message: String, exception: Any) -> Unit)?, buffer: ArrayBuffer?,
        fallback: InternalTexture?, format: Number?, forcedExtension: String?, excludeLoaders: Array<IInternalTextureLoader>?): InternalTexture
    fun createTexture(
        urlArg: String?, noMipmap: Boolean, invertY: Boolean, scene: Scene?, samplingMode: Number?,
        onLoad: (() -> Unit)?, onError: ((message: String, exception: Any) -> Unit)?, buffer: HTMLImageElement?,
        fallback: InternalTexture?, format: Number?, forcedExtension: String?, excludeLoaders: Array<IInternalTextureLoader>?): InternalTexture
    fun createTexture(
        urlArg: String?, noMipmap: Boolean, invertY: Boolean, scene: Scene?, samplingMode: Number?,
        onLoad: (() -> Unit)?, onError: ((message: String, exception: Any) -> Unit)?, buffer: Blob?,
        fallback: InternalTexture?, format: Number?, forcedExtension: String?, excludeLoaders: Array<IInternalTextureLoader>?): InternalTexture
    /**
     * In case you are sharing the context with other applications, it might
     * be interested to not cache the unpack flip y state to ensure a consistent
     * value would be set.
     */
    var enableUnpackFlipYCached: Boolean
    /**
     * Creates a dynamic texture
     * @param width defines the width of the texture
     * @param height defines the height of the texture
     * @param generateMipMaps defines if the engine should generate the mip levels
     * @param samplingMode defines the required sampling mode (Texture.NEAREST_SAMPLINGMODE by default)
     * @returns the dynamic texture inside an InternalTexture
     */
    fun createDynamicTexture(width: Number, height: Number, generateMipMaps: Boolean, samplingMode: Number): InternalTexture
    /**
     * Update the sampling mode of a given texture
     * @param samplingMode defines the required sampling mode
     * @param texture defines the texture to update
     */
    fun updateTextureSamplingMode(samplingMode: Number, texture: InternalTexture)
    /**
     * Update the content of a dynamic texture
     * @param texture defines the texture to update
     * @param canvas defines the canvas containing the source
     * @param invertY defines if data must be stored with Y axis inverted
     * @param premulAlpha defines if alpha is stored as premultiplied
     * @param format defines the format of the data
     * @param forceBindTexture if the texture should be forced to be bound eg. after a graphics context loss (Default: false)
     */
    fun updateDynamicTexture(texture: InternalTexture?, canvas: HTMLCanvasElement, invertY: Boolean, premulAlpha: Boolean?, format: Number?, forceBindTexture: Boolean?)
    /**
     * Update a video texture
     * @param texture defines the texture to update
     * @param video defines the video element to use
     * @param invertY defines if data must be stored with Y axis inverted
     */
    fun updateVideoTexture(texture: InternalTexture?, video: HTMLVideoElement, invertY: Boolean)
    /**
     * Updates a depth texture Comparison Mode and Function.
     * If the comparison Function is equal to 0, the mode will be set to none.
     * Otherwise, this only works in webgl 2 and requires a shadow sampler in the shader.
     * @param texture The texture to set the comparison function for
     * @param comparisonFunction The comparison function to set, 0 if no comparison required
     */
    fun updateTextureComparisonFunction(texture: InternalTexture, comparisonFunction: Number)
    /**
     * Creates a depth stencil texture.
     * This is only available in WebGL 2 or with the depth texture extension available.
     * @param size The size of face edge in the texture.
     * @param options The options defining the texture.
     * @returns The texture
     */
    fun createDepthStencilTexture(size: Number /*| { var width: Number var height: Number }*/, options: DepthTextureCreationOptions): InternalTexture
    /**
     * Sets the frame buffer Depth / Stencil attachement of the render target to the defined depth stencil texture.
     * @param renderTarget The render target to set the frame buffer for
     */
    fun setFrameBufferDepthStencilTexture(renderTarget: RenderTargetTexture)
    /**
     * Creates a new render target texture
     * @param size defines the size of the texture
     * @param options defines the options used to create the texture
     * @returns a new render target texture stored in an InternalTexture
     */
    fun createRenderTargetTexture(size: Number /*| { var width: Number var height: Number }*/, options: Boolean /*| RenderTargetCreationOptions*/): InternalTexture
    /**
     * Updates the sample count of a render target texture
     * @see [http://doc.babylonjs.com/features/webgl2#multisample-render-targets]
     * @param texture defines the texture to update
     * @param samples defines the sample count to set
     * @returns the effective sample count (could be 0 if multisample render targets are not supported)
     */
    fun updateRenderTargetTextureSampleCount(texture: InternalTexture?, samples: Number): Number
    /**
     * Binds an effect to the webGL context
     * @param effect defines the effect to bind
     */
    fun bindSamplers(effect: Effect)
    /**
     * Sets a texture to the webGL context from a postprocess
     * @param channel defines the channel to use
     * @param postProcess defines the source postprocess
     */
    fun setTextureFromPostProcess(channel: Number, postProcess: PostProcess?)
    /**
     * Binds the output of the passed in post process to the texture channel specified
     * @param channel The channel the texture should be bound to
     * @param postProcess The post process which's output should be bound
     */
    fun setTextureFromPostProcessOutput(channel: Number, postProcess: PostProcess?)
    /**
     * Unbind all textures from the webGL context
     */
    fun unbindAllTextures()
    /**
     * Sets a texture to the according uniform.
     * @param channel The texture channel
     * @param uniform The uniform to set
     * @param texture The texture to apply
     */
    fun setTexture(channel: Number, uniform: WebGLUniformLocation?, texture: BaseTexture?)
    /**
     * Sets a depth stencil texture from a render target to the according uniform.
     * @param channel The texture channel
     * @param uniform The uniform to set
     * @param texture The render target texture containing the depth stencil texture to apply
     */
    fun setDepthStencilTexture(channel: Number, uniform: WebGLUniformLocation?, texture: RenderTargetTexture?)
    /**
     * Sets an array of texture to the webGL context
     * @param channel defines the channel where the texture array must be set
     * @param uniform defines the associated uniform location
     * @param textures defines the array of textures to bind
     */
    fun setTextureArray(channel: Number, uniform: WebGLUniformLocation?, textures: Array<BaseTexture>)
    /**
     * Reads pixels from the current frame buffer. Please note that this function can be slow
     * @param x defines the x coordinate of the rectangle where pixels must be read
     * @param y defines the y coordinate of the rectangle where pixels must be read
     * @param width defines the width of the rectangle where pixels must be read
     * @param height defines the height of the rectangle where pixels must be read
     * @returns a Uint8Array containing RGBA colors
     */
    fun readPixels(x: Number, y: Number, width: Number, height: Number): Uint8Array
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
    fun <T>getExternalData(key: String): T
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
     * Unbind all vertex attributes from the webGL context
     */
    fun unbindAllAttributes()
    /**
     * Force the engine to release all cached effects. This means that next effect compilation will have to be done completely even if a similar effect was already compiled
     */
    fun releaseEffects()
    /**
     * Dispose and release all associated resources
     */
    fun dispose()
    /**
     * Display the loading screen
     * @see [http://doc.babylonjs.com/how_to/creating_a_custom_loading_screen]
     */
    fun displayLoadingUI()
    /**
     * Hide the loading screen
     * @see [http://doc.babylonjs.com/how_to/creating_a_custom_loading_screen]
     */
    fun hideLoadingUI()
    /**
     * Gets the current loading screen object
     * @see [http://doc.babylonjs.com/how_to/creating_a_custom_loading_screen]
     */
    /**
     * Sets the current loading screen object
     * @see [http://doc.babylonjs.com/how_to/creating_a_custom_loading_screen]
     */
    var loadingScreen: ILoadingScreen
    /**
     * Sets the current loading screen text
     * @see [http://doc.babylonjs.com/how_to/creating_a_custom_loading_screen]
     */
    var loadingUIText: String
    /**
     * Sets the current loading screen background color
     * @see [http://doc.babylonjs.com/how_to/creating_a_custom_loading_screen]
     */
    var loadingUIBackgroundColor: String
    /**
     * Attach a new callback raised when context lost event is fired
     * @param callback defines the callback to call
     */
    fun attachContextLostEvent(callback: ((event: WebGLContextEvent) -> Unit))
    /**
     * Attach a new callback raised when context restored event is fired
     * @param callback defines the callback to call
     */
    fun attachContextRestoredEvent(callback: ((event: WebGLContextEvent) -> Unit))
    /**
     * Gets the source code of the vertex shader associated with a specific webGL program
     * @param program defines the program to use
     * @returns a String containing the source code of the vertex shader associated with the program
     */
    fun getVertexShaderSource(program: WebGLProgram): String?
    /**
     * Gets the source code of the fragment shader associated with a specific webGL program
     * @param program defines the program to use
     * @returns a String containing the source code of the fragment shader associated with the program
     */
    fun getFragmentShaderSource(program: WebGLProgram): String?
    /**
     * Get the current error code of the webGL context
     * @returns the error code
     * @see [https://developer.mozilla.org/en-US/docs/Web/API/WebGLRenderingContext/getError]
     */
    fun getError(): Number
    /**
     * Gets the current framerate
     * @returns a Number representing the framerate
     */
    fun getFps(): Number
    /**
     * Gets the time spent between current and previous frame
     * @returns a Number representing the delta time in ms
     */
    fun getDeltaTime(): Number

    companion object {
        /** Use this array to turn off some WebGL2 features on known buggy browsers version */
        /*
        static ExceptionList: ({
            var key: String
            var capture: String
            var captureConstraint: Number
            var targets: Array<String>
        } | {
            var key: String
            var capture: null
            var captureConstraint: null
            var targets: Array<String>
        })[]
        */

        /** Gets the list of created engines */
        val Instances: Array<Engine>
        /**
         * Gets the latest created engine
         */
        val LastCreatedEngine: Engine?
        /**
         * Gets the latest created scene
         */
        val LastCreatedScene: Scene?
        /**
         * Will flag all materials in all scenes in all engines as dirty to trigger new shader compilation
         * @param flag defines which part of the materials must be marked as dirty
         * @param predicate defines a predicate used to filter which materials should be affected
         */
        fun MarkAllMaterialsAsDirty(flag: Number, predicate: ((mat: Material) -> Boolean)?)
        
        /** Defines that alpha blending is disabled */
        val ALPHA_DISABLE: Number
        /** Defines that alpha blending to SRC ALPHA * SRC + DEST */
        val ALPHA_ADD: Number
        /** Defines that alpha blending to SRC ALPHA * SRC + (1 - SRC ALPHA) * DEST */
        val ALPHA_COMBINE: Number
        /** Defines that alpha blending to DEST - SRC * DEST */
        val ALPHA_SUBTRACT: Number
        /** Defines that alpha blending to SRC * DEST */
        val ALPHA_MULTIPLY: Number
        /** Defines that alpha blending to SRC ALPHA * SRC + (1 - SRC) * DEST */
        val ALPHA_MAXIMIZED: Number
        /** Defines that alpha blending to SRC + DEST */
        val ALPHA_ONEONE: Number
        /** Defines that alpha blending to SRC + (1 - SRC ALPHA) * DEST */
        val ALPHA_PREMULTIPLIED: Number
        /**
         * Defines that alpha blending to SRC + (1 - SRC ALPHA) * DEST
         * Alpha will be set to (1 - SRC ALPHA) * DEST ALPHA
         */
        val ALPHA_PREMULTIPLIED_PORTERDUFF: Number
        /** Defines that alpha blending to CST * SRC + (1 - CST) * DEST */
        val ALPHA_INTERPOLATE: Number
        /**
         * Defines that alpha blending to SRC + (1 - SRC) * DEST
         * Alpha will be set to SRC ALPHA + (1 - SRC ALPHA) * DEST ALPHA
         */
        val ALPHA_SCREENMODE: Number
        /** Defines that the ressource is not delayed*/
        val DELAYLOADSTATE_NONE: Number
        /** Defines that the ressource was successfully delay loaded */
        val DELAYLOADSTATE_LOADED: Number
        /** Defines that the ressource is currently delay loading */
        val DELAYLOADSTATE_LOADING: Number
        /** Defines that the ressource is delayed and has not started loading */
        val DELAYLOADSTATE_NOTLOADED: Number
        /** Passed to depthFunction or stencilFunction to specify depth or stencil tests will never pass. i.e. Nothing will be drawn */
        val NEVER: Number
        /** Passed to depthFunction or stencilFunction to specify depth or stencil tests will always pass. i.e. Pixels will be drawn in the order they are drawn */
        val ALWAYS: Number
        /** Passed to depthFunction or stencilFunction to specify depth or stencil tests will pass if the new depth value is less than the stored value */
        val LESS: Number
        /** Passed to depthFunction or stencilFunction to specify depth or stencil tests will pass if the new depth value is equals to the stored value */
        val EQUAL: Number
        /** Passed to depthFunction or stencilFunction to specify depth or stencil tests will pass if the new depth value is less than or equal to the stored value */
        val LEQUAL: Number
        /** Passed to depthFunction or stencilFunction to specify depth or stencil tests will pass if the new depth value is greater than the stored value */
        val GREATER: Number
        /** Passed to depthFunction or stencilFunction to specify depth or stencil tests will pass if the new depth value is greater than or equal to the stored value */
        val GEQUAL: Number
        /** Passed to depthFunction or stencilFunction to specify depth or stencil tests will pass if the new depth value is not equal to the stored value */
        val NOTEQUAL: Number
        /** Passed to stencilOperation to specify that stencil value must be kept */
        val KEEP: Number
        /** Passed to stencilOperation to specify that stencil value must be replaced */
        val REPLACE: Number
        /** Passed to stencilOperation to specify that stencil value must be incremented */
        val INCR: Number
        /** Passed to stencilOperation to specify that stencil value must be decremented */
        val DECR: Number
        /** Passed to stencilOperation to specify that stencil value must be inverted */
        val INVERT: Number
        /** Passed to stencilOperation to specify that stencil value must be incremented with wrapping */
        val INCR_WRAP: Number
        /** Passed to stencilOperation to specify that stencil value must be decremented with wrapping */
        val DECR_WRAP: Number
        /** Texture is not repeating outside of 0..1 UVs */
        val TEXTURE_CLAMP_ADDRESSMODE: Number
        /** Texture is repeating outside of 0..1 UVs */
        val TEXTURE_WRAP_ADDRESSMODE: Number
        /** Texture is repeating and mirrored */
        val TEXTURE_MIRROR_ADDRESSMODE: Number
        /** ALPHA */
        val TEXTUREFORMAT_ALPHA: Number
        /** LUMINANCE */
        val TEXTUREFORMAT_LUMINANCE: Number
        /** LUMINANCE_ALPHA */
        val TEXTUREFORMAT_LUMINANCE_ALPHA: Number
        /** RGB */
        val TEXTUREFORMAT_RGB: Number
        /** RGBA */
        val TEXTUREFORMAT_RGBA: Number
        /** RED */
        val TEXTUREFORMAT_RED: Number
        /** RED (2nd reference) */
        val TEXTUREFORMAT_R: Number
        /** RG */
        val TEXTUREFORMAT_RG: Number
        /** RED_INTEGER */
        val TEXTUREFORMAT_RED_INTEGER: Number
        /** RED_INTEGER (2nd reference) */
        val TEXTUREFORMAT_R_INTEGER: Number
        /** RG_INTEGER */
        val TEXTUREFORMAT_RG_INTEGER: Number
        /** RGB_INTEGER */
        val TEXTUREFORMAT_RGB_INTEGER: Number
        /** RGBA_INTEGER */
        val TEXTUREFORMAT_RGBA_INTEGER: Number
        /** UNSIGNED_BYTE */
        val TEXTURETYPE_UNSIGNED_BYTE: Number
        /** UNSIGNED_BYTE (2nd reference) */
        val TEXTURETYPE_UNSIGNED_INT: Number
        /** FLOAT */
        val TEXTURETYPE_FLOAT: Number
        /** HALF_FLOAT */
        val TEXTURETYPE_HALF_FLOAT: Number
        /** BYTE */
        val TEXTURETYPE_BYTE: Number
        /** SHORT */
        val TEXTURETYPE_SHORT: Number
        /** UNSIGNED_SHORT */
        val TEXTURETYPE_UNSIGNED_SHORT: Number
        /** INT */
        val TEXTURETYPE_INT: Number
        /** UNSIGNED_INT */
        val TEXTURETYPE_UNSIGNED_INTEGER: Number
        /** UNSIGNED_SHORT_4_4_4_4 */
        val TEXTURETYPE_UNSIGNED_SHORT_4_4_4_4: Number
        /** UNSIGNED_SHORT_5_5_5_1 */
        val TEXTURETYPE_UNSIGNED_SHORT_5_5_5_1: Number
        /** UNSIGNED_SHORT_5_6_5 */
        val TEXTURETYPE_UNSIGNED_SHORT_5_6_5: Number
        /** UNSIGNED_INT_2_10_10_10_REV */
        val TEXTURETYPE_UNSIGNED_INT_2_10_10_10_REV: Number
        /** UNSIGNED_INT_24_8 */
        val TEXTURETYPE_UNSIGNED_INT_24_8: Number
        /** UNSIGNED_INT_10F_11F_11F_REV */
        val TEXTURETYPE_UNSIGNED_INT_10F_11F_11F_REV: Number
        /** UNSIGNED_INT_5_9_9_9_REV */
        val TEXTURETYPE_UNSIGNED_INT_5_9_9_9_REV: Number
        /** FLOAT_32_UNSIGNED_INT_24_8_REV */
        val TEXTURETYPE_FLOAT_32_UNSIGNED_INT_24_8_REV: Number
        /** nearest is mag = nearest and min = nearest and mip = linear */
        val TEXTURE_NEAREST_SAMPLINGMODE: Number
        /** Bilinear is mag = linear and min = linear and mip = nearest */
        val TEXTURE_BILINEAR_SAMPLINGMODE: Number
        /** Trilinear is mag = linear and min = linear and mip = linear */
        val TEXTURE_TRILINEAR_SAMPLINGMODE: Number
        /** nearest is mag = nearest and min = nearest and mip = linear */
        val TEXTURE_NEAREST_NEAREST_MIPLINEAR: Number
        /** Bilinear is mag = linear and min = linear and mip = nearest */
        val TEXTURE_LINEAR_LINEAR_MIPNEAREST: Number
        /** Trilinear is mag = linear and min = linear and mip = linear */
        val TEXTURE_LINEAR_LINEAR_MIPLINEAR: Number
        /** mag = nearest and min = nearest and mip = nearest */
        val TEXTURE_NEAREST_NEAREST_MIPNEAREST: Number
        /** mag = nearest and min = linear and mip = nearest */
        val TEXTURE_NEAREST_LINEAR_MIPNEAREST: Number
        /** mag = nearest and min = linear and mip = linear */
        val TEXTURE_NEAREST_LINEAR_MIPLINEAR: Number
        /** mag = nearest and min = linear and mip = none */
        val TEXTURE_NEAREST_LINEAR: Number
        /** mag = nearest and min = nearest and mip = none */
        val TEXTURE_NEAREST_NEAREST: Number
        /** mag = linear and min = nearest and mip = nearest */
        val TEXTURE_LINEAR_NEAREST_MIPNEAREST: Number
        /** mag = linear and min = nearest and mip = linear */
        val TEXTURE_LINEAR_NEAREST_MIPLINEAR: Number
        /** mag = linear and min = linear and mip = none */
        val TEXTURE_LINEAR_LINEAR: Number
        /** mag = linear and min = nearest and mip = none */
        val TEXTURE_LINEAR_NEAREST: Number
        /** Explicit coordinates mode */
        val TEXTURE_EXPLICIT_MODE: Number
        /** Spherical coordinates mode */
        val TEXTURE_SPHERICAL_MODE: Number
        /** Planar coordinates mode */
        val TEXTURE_PLANAR_MODE: Number
        /** Cubic coordinates mode */
        val TEXTURE_CUBIC_MODE: Number
        /** Projection coordinates mode */
        val TEXTURE_PROJECTION_MODE: Number
        /** Skybox coordinates mode */
        val TEXTURE_SKYBOX_MODE: Number
        /** Inverse Cubic coordinates mode */
        val TEXTURE_INVCUBIC_MODE: Number
        /** Equirectangular coordinates mode */
        val TEXTURE_EQUIRECTANGULAR_MODE: Number
        /** Equirectangular Fixed coordinates mode */
        val TEXTURE_FIXED_EQUIRECTANGULAR_MODE: Number
        /** Equirectangular Fixed Mirrored coordinates mode */
        val TEXTURE_FIXED_EQUIRECTANGULAR_MIRRORED_MODE: Number
        /** Defines that texture rescaling will use a floor to find the closer power of 2 size */
        val SCALEMODE_FLOOR: Number
        /** Defines that texture rescaling will look for the nearest power of 2 size */
        val SCALEMODE_NEAREST: Number
        /** Defines that texture rescaling will use a ceil to find the closer power of 2 size */
        val SCALEMODE_CEILING: Number
        /**
         * Returns the current npm package of the sdk
         */
        val NpmPackage: String
        /**
         * Returns the current version of the framework
         */
        val Version: String
        /**
         * Gets or sets the epsilon value used by collision engine
         */
        var CollisionsEpsilon: Number
        /**
         * Gets or sets the relative url used to load shaders if using the engine in non-minified mode
         */
        var ShadersRepository: String
        /**
         * Method called to create the default loading screen.
         * This can be overriden in your own app.
         * @param canvas The rendering canvas element
         * @returns The loading screen
         */
        fun DefaultLoadingScreenFactory(canvas: HTMLCanvasElement): ILoadingScreen
        /**
         * Method called to create the default rescale post process on each engine.
         */
        var _RescalePostProcessFactory: ((engine: Engine) -> PostProcess)?
        /**
         * Gets the audio engine
         * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music]
         * @ignorenaming
         */
        var audioEngine: IAudioEngine
        /**
         * Default AudioEngine factory responsible of creating the Audio Engine.
         * By default, this will create a BabylonJS Audio Engine if the workload has been embedded.
         */
        var AudioEngineFactory: (hostElement: HTMLElement?) -> IAudioEngine
        /**
         * Default offline support factory responsible of creating a tool used to store data locally.
         * By default, this will create a Database object if the workload has been embedded.
         */
        var OfflineProviderFactory: (urlToScene: String, callbackManifestChecked: (checked: Boolean) -> Any, disableManifestCheck: Boolean) -> IOfflineProvider

        /**
         * Gets a Boolean indicating if the engine can be instanciated (ie. if a webGL context can be found)
         * @returns true if the engine can be created
         * @ignorenaming
         */
        fun isSupported(): Boolean
    }
}
