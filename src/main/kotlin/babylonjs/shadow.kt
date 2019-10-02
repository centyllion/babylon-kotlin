@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals", "FunctionName")
@file:JsModule("babylonjs")
package babylonjs

import kotlin.js.Promise

/**
 * Defines the options associated with the creation of a custom shader for a shadow generator.
 */
external interface ICustomShaderOptions {
    /**
     * Gets or sets the custom shader name to use
     */
    var shaderName: String
    /**
     * The list of attribute names used in the shader
     */
    var attributes: Array<String>?
    /**
     * The list of unifrom names used in the shader
     */
    var uniforms: Array<String>?
    /**
     * The list of sampler names used in the shader
     */
    var samplers: Array<String>?
    /**
     * The list of defines used in the shader
     */
    var defines: Array<String>?
}

/**
 * Interface to implement to create a shadow generator compatible with BJS.
 */
external interface IShadowGenerator {
    /**
     * Gets the main RTT containing the shadow map (usually storing depth from the light point of view).
     * @returns The render target texture if present otherwise, null
     */
    fun getShadowMap(): RenderTargetTexture?
    /**
     * Gets the RTT used during rendering (can be a blurred version of the shadow map or the shadow map itself).
     * @returns The render target texture if the shadow map is present otherwise, null
     */
    fun getShadowMapForRendering(): RenderTargetTexture?
    /**
     * Determine wheter the shadow generator is ready or not (mainly all effects and related post processes needs to be ready).
     * @param subMesh The submesh we want to render in the shadow map
     * @param useInstances Defines wether will draw in the map using instances
     * @returns true if ready otherwise, false
     */
    fun isReady(subMesh: SubMesh, useInstances: Boolean): Boolean
    /**
     * Prepare all the defines in a material relying on a shadow map at the specified light index.
     * @param defines Defines of the material we want to update
     * @param lightIndex Index of the light in the enabled light list of the material
     */
    fun prepareDefines(defines: MaterialDefines, lightIndex: Number)
    /**
     * Binds the shadow related information inside of an effect (information like near, far, darkness...
     * defined in the generator but impacting the effect).
     * It implies the unifroms available on the materials are the standard BJS ones.
     * @param lightIndex Index of the light in the enabled light list of the material owning the effect
     * @param effect The effect we are binfing the information for
     */
    fun bindShadowLight(lightIndex: String, effect: Effect)
    /**
     * Gets the transformation matrix used to project the meshes into the map from the light point of view.
     * (eq to shadow prjection matrix * light transform matrix)
     * @returns The transform matrix used to create the shadow map
     */
    fun getTransformMatrix(): Matrix
    /**
     * Recreates the shadow map dependencies like RTT and post processes. This can be used during the switch between
     * Cube and 2D textures for instance.
     */
    fun recreateShadowMap()
    /**
     * Forces all the attached effect to compile to enable rendering only once ready vs. lazyly compiling effects.
     * @param onCompiled Callback triggered at the and of the effects compilation
     * @param options Sets of optional options forcing the compilation with different modes
     */
    fun forceCompilation(onCompiled: ((generator: ShadowGenerator) -> Unit)?, options: Any?)
    /**
     * Forces all the attached effect to compile to enable rendering only once ready vs. lazyly compiling effects.
     * @param options Sets of optional options forcing the compilation with different modes
     * @returns A promise that resolves when the compilation completes
     */
    fun forceCompilationAsync(options: Any? = definedExternally): Promise<Unit>
    /**
     * Serializes the shadow generator setup to a json object.
     * @returns The serialized JSON object
     */
    fun serialize(): Any
    /**
     * Disposes the Shadow map and related Textures and effects.
     */
    fun dispose()
}
/**
 * Default implementation IShadowGenerator.
 * This is the main object responsible of generating shadows in the framework.
 * Documentation: https://doc.babylonjs.com/babylon101/shadows
 */
external class ShadowGenerator: IShadowGenerator {
    /** Gets or sets the custom shader name to use */
    var customShaderOptions: ICustomShaderOptions
    /**
     * Observable triggered before the shadow is rendered. Can be used to update internal effect state
     */
    var onBeforeShadowMapRenderObservable: Observable<Effect>
    /**
     * Observable triggered before a mesh is rendered in the shadow map.
     * Can be used to update internal effect state (that you can get from the onBeforeShadowMapRenderObservable)
     */
    var onBeforeShadowMapRenderMeshObservable: Observable<Mesh>
    /**
     * Gets the bias: offset applied on the depth preventing acnea (in light direction).
     */
    /**
     * Sets the bias: offset applied on the depth preventing acnea (in light direction).
     */
    var bias: Number
    /**
     * Gets the normalBias: offset applied on the depth preventing acnea (along side the normal direction and proportinal to the light/normal angle).
     */
    /**
     * Sets the normalBias: offset applied on the depth preventing acnea (along side the normal direction and proportinal to the light/normal angle).
     */
    var normalBias: Number
    /**
     * Gets the blur box offset: offset applied during the blur pass.
     * Only useful if useKernelBlur = false
     */
    /**
     * Sets the blur box offset: offset applied during the blur pass.
     * Only useful if useKernelBlur = false
     */
    var blurBoxOffset: Number
    /**
     * Gets the blur scale: scale of the blurred texture compared to the main shadow map.
     * 2 means half of the size.
     */
    /**
     * Sets the blur scale: scale of the blurred texture compared to the main shadow map.
     * 2 means half of the size.
     */
    var blurScale: Number
    /**
     * Gets the blur kernel: kernel size of the blur pass.
     * Only useful if useKernelBlur = true
     */
    /**
     * Sets the blur kernel: kernel size of the blur pass.
     * Only useful if useKernelBlur = true
     */
    var blurKernel: Number
    /**
     * Gets whether the blur pass is a kernel blur (if true) or box blur.
     * Only useful in filtered mode (useBlurExponentialShadowMap...)
     */
    /**
     * Sets whether the blur pass is a kernel blur (if true) or box blur.
     * Only useful in filtered mode (useBlurExponentialShadowMap...)
     */
    var useKernelBlur: Boolean
    /**
     * Gets the depth scale used in ESM mode.
     */
    /**
     * Sets the depth scale used in ESM mode.
     * This can override the scale stored on the light.
     */
    var depthScale: Number
    /**
     * Gets the current mode of the shadow generator (normal, PCF, ESM...).
     * The returned value is a Number equal to one of the available mode defined in ShadowMap.FILTER_x like _FILTER_NONE
     */
    /**
     * Sets the current mode of the shadow generator (normal, PCF, ESM...).
     * The returned value is a Number equal to one of the available mode defined in ShadowMap.FILTER_x like _FILTER_NONE
     */
    var filter: Number
    /**
     * Gets if the current filter is set to Poisson Sampling.
     */
    /**
     * Sets the current filter to Poisson Sampling.
     */
    var usePoissonSampling: Boolean
    /**
     * Gets if the current filter is set to ESM.
     */
    /**
     * Sets the current filter is to ESM.
     */
    var useExponentialShadowMap: Boolean
    /**
     * Gets if the current filter is set to filtered ESM.
     */
    /**
     * Gets if the current filter is set to filtered  ESM.
     */
    var useBlurExponentialShadowMap: Boolean
    /**
     * Gets if the current filter is set to "close ESM" (using the inverse of the
     * exponential to prevent steep falloff artifacts).
     */
    /**
     * Sets the current filter to "close ESM" (using the inverse of the
     * exponential to prevent steep falloff artifacts).
     */
    var useCloseExponentialShadowMap: Boolean
    /**
     * Gets if the current filter is set to filtered "close ESM" (using the inverse of the
     * exponential to prevent steep falloff artifacts).
     */
    /**
     * Sets the current filter to filtered "close ESM" (using the inverse of the
     * exponential to prevent steep falloff artifacts).
     */
    var useBlurCloseExponentialShadowMap: Boolean
    /**
     * Gets if the current filter is set to "PCF" (percentage closer filtering).
     */
    /**
     * Sets the current filter to "PCF" (percentage closer filtering).
     */
    var usePercentageCloserFiltering: Boolean
    /**
     * Gets the PCF or PCSS Quality.
     * Only valid if usePercentageCloserFiltering or usePercentageCloserFiltering is true.
     */
    /**
     * Sets the PCF or PCSS Quality.
     * Only valid if usePercentageCloserFiltering or usePercentageCloserFiltering is true.
     */
    var filteringQuality: Number
    /**
     * Gets if the current filter is set to "PCSS" (contact hardening).
     */
    /**
     * Sets the current filter to "PCSS" (contact hardening).
     */
    var useContactHardeningShadow: Boolean
    /**
     * Gets the Light Size (in shadow map uv unit) used in PCSS to determine the blocker search area and the penumbra size.
     * Using a ratio helps keeping shape stability independently of the map size.
     *
     * It does not account for the light projection as it was having too much
     * instability during the light setup or during light position changes.
     *
     * Only valid if useContactHardeningShadow is true.
     */
    /**
     * Sets the Light Size (in shadow map uv unit) used in PCSS to determine the blocker search area and the penumbra size.
     * Using a ratio helps keeping shape stability independently of the map size.
     *
     * It does not account for the light projection as it was having too much
     * instability during the light setup or during light position changes.
     *
     * Only valid if useContactHardeningShadow is true.
     */
    var contactHardeningLightSizeUVRatio: Number
    /**
     * Returns the darkness value (float). This can only decrease the actual darkness of a shadow.
     * 0 means strongest and 1 would means no shadow.
     * @returns the darkness.
     */
    fun getDarkness(): Number
    /**
     * Sets the darkness value (float). This can only decrease the actual darkness of a shadow.
     * @param darkness The darkness value 0 means strongest and 1 would means no shadow.
     * @returns the shadow generator allowing fluent coding.
     */
    fun setDarkness(darkness: Number): ShadowGenerator
    /**
     * Sets the ability to have transparent shadow (Boolean).
     * @param transparent True if transparent else False
     * @returns the shadow generator allowing fluent coding
     */
    fun setTransparencyShadow(transparent: Boolean): ShadowGenerator
    /**
     * Gets the main RTT containing the shadow map (usually storing depth from the light point of view).
     * @returns The render target texture if present otherwise, null
     */
    override fun getShadowMap(): RenderTargetTexture?
    /**
     * Gets the RTT used during rendering (can be a blurred version of the shadow map or the shadow map itself).
     * @returns The render target texture if the shadow map is present otherwise, null
     */
    override fun getShadowMapForRendering(): RenderTargetTexture?
    /**
     * Helper function to add a mesh and its descendants to the list of shadow casters.
     * @param mesh Mesh to add
     * @param includeDescendants Boolean indicating if the descendants should be added. Default to true
     * @returns the Shadow Generator itself
     */
    fun addShadowCaster(mesh: AbstractMesh, includeDescendants: Boolean? = definedExternally): ShadowGenerator
    /**
     * Helper function to remove a mesh and its descendants from the list of shadow casters
     * @param mesh Mesh to remove
     * @param includeDescendants Boolean indicating if the descendants should be removed. Default to true
     * @returns the Shadow Generator itself
     */
    fun removeShadowCaster(mesh: AbstractMesh, includeDescendants: Boolean? = definedExternally): ShadowGenerator
    /**
     * Controls the extent to which the shadows fade out at the edge of the frustum
     * Used only by directionals and spots
     */
    var frustumEdgeFalloff: Number
    /**
     * Returns the associated light object.
     * @returns the light generating the shadow
     */
    fun getLight(): IShadowLight
    /**
     * If true the shadow map is generated by rendering the back face of the mesh instead of the front face.
     * This can help with self-shadowing as the geometry making up the back of objects is slightly offset.
     * It might on the other hand introduce peter panning.
     */
    var forceBackFacesOnly: Boolean
    /**
     * Creates a ShadowGenerator object.
     * A ShadowGenerator is the required tool to use the shadows.
     * Each light casting shadows needs to use its own ShadowGenerator.
     * Documentation : https://doc.babylonjs.com/babylon101/shadows
     * @param mapSize The size of the texture what stores the shadows. Example : 1024.
     * @param light The light object generating the shadows.
     * @param usefulFloatFirst By default the generator will try to use half float textures but if you need precision (for self shadowing for instance), you can use this option to enforce full float texture.
     */
    constructor(mapSize: Number, light: IShadowLight, usefulFloatFirst: Boolean? = definedExternally)
    /**
     * Forces all the attached effect to compile to enable rendering only once ready vs. lazyly compiling effects.
     * @param onCompiled Callback triggered at the and of the effects compilation
     * @param options Sets of optional options forcing the compilation with different modes
     */
    override fun forceCompilation(onCompiled: ((generator: ShadowGenerator) -> Unit)?, options: Any?)
    /**
     * Forces all the attached effect to compile to enable rendering only once ready vs. lazyly compiling effects.
     * @param options Sets of optional options forcing the compilation with different modes
     * @returns A promise that resolves when the compilation completes
     */
    override fun forceCompilationAsync(options: Any?): Promise<Unit>
    /**
     * Determine wheter the shadow generator is ready or not (mainly all effects and related post processes needs to be ready).
     * @param subMesh The submesh we want to render in the shadow map
     * @param useInstances Defines wether will draw in the map using instances
     * @returns true if ready otherwise, false
     */
    override fun isReady(subMesh: SubMesh, useInstances: Boolean): Boolean
    /**
     * Prepare all the defines in a material relying on a shadow map at the specified light index.
     * @param defines Defines of the material we want to update
     * @param lightIndex Index of the light in the enabled light list of the material
     */
    override fun prepareDefines(defines: MaterialDefines, lightIndex: Number)
    /**
     * Binds the shadow related information inside of an effect (information like near, far, darkness...
     * defined in the generator but impacting the effect).
     * @param lightIndex Index of the light in the enabled light list of the material owning the effect
     * @param effect The effect we are binfing the information for
     */
    override fun bindShadowLight(lightIndex: String, effect: Effect)
    /**
     * Gets the transformation matrix used to project the meshes into the map from the light point of view.
     * (eq to shadow prjection matrix * light transform matrix)
     * @returns The transform matrix used to create the shadow map
     */
    override fun getTransformMatrix(): Matrix
    /**
     * Recreates the shadow map dependencies like RTT and post processes. This can be used during the switch between
     * Cube and 2D textures for instance.
     */
    override fun recreateShadowMap()
    /**
     * Disposes the ShadowGenerator.
     * Returns nothing.
     */
    override fun dispose()
    /**
     * Serializes the shadow generator setup to a json object.
     * @returns The serialized JSON object
     */
    override fun serialize(): Any
    
    companion object {
        /**
         * Shadow generator mode None: no filtering applied.
         */
        val FILTER_NONE: Number
        /**
         * Shadow generator mode ESM: Exponential Shadow Mapping.
         * (http://developer.download.nvidia.com/presentations/2008/GDC/GDC08_SoftShadowMapping.pdf)
         */
        val FILTER_EXPONENTIALSHADOWMAP: Number
        /**
         * Shadow generator mode Poisson Sampling: Percentage Closer Filtering.
         * (Multiple Tap around evenly distributed around the pixel are used to evaluate the shadow strength)
         */
        val FILTER_POISSONSAMPLING: Number
        /**
         * Shadow generator mode ESM: Blurred Exponential Shadow Mapping.
         * (http://developer.download.nvidia.com/presentations/2008/GDC/GDC08_SoftShadowMapping.pdf)
         */
        val FILTER_BLUREXPONENTIALSHADOWMAP: Number
        /**
         * Shadow generator mode ESM: Exponential Shadow Mapping using the inverse of the exponential preventing
         * edge artifacts on steep falloff.
         * (http://developer.download.nvidia.com/presentations/2008/GDC/GDC08_SoftShadowMapping.pdf)
         */
        val FILTER_CLOSEEXPONENTIALSHADOWMAP: Number
        /**
         * Shadow generator mode ESM: Blurred Exponential Shadow Mapping using the inverse of the exponential preventing
         * edge artifacts on steep falloff.
         * (http://developer.download.nvidia.com/presentations/2008/GDC/GDC08_SoftShadowMapping.pdf)
         */
        val FILTER_BLURCLOSEEXPONENTIALSHADOWMAP: Number
        /**
         * Shadow generator mode PCF: Percentage Closer Filtering
         * benefits from Webgl 2 shadow samplers. Fallback to Poisson Sampling in Webgl 1
         * (https://developer.nvidia.com/gpugems/GPUGems/gpugems_ch11.html)
         */
        val FILTER_PCF: Number
        /**
         * Shadow generator mode PCSS: Percentage Closering Soft Shadow.
         * benefits from Webgl 2 shadow samplers. Fallback to Poisson Sampling in Webgl 1
         * Contact Hardening
         */
        val FILTER_PCSS: Number
        /**
         * Reserved for PCF and PCSS
         * Highest Quality.
         *
         * Execute PCF on a 5*5 kernel improving a lot the shadow aliasing artifacts.
         *
         * Execute PCSS with 32 taps blocker search and 64 taps PCF.
         */
        val QUALITY_HIGH: Number
        /**
         * Reserved for PCF and PCSS
         * Good tradeoff for quality/perf cross devices
         *
         * Execute PCF on a 3*3 kernel.
         *
         * Execute PCSS with 16 taps blocker search and 32 taps PCF.
         */
        val QUALITY_MEDIUM: Number
        /**
         * Reserved for PCF and PCSS
         * The lowest quality but the fastest.
         *
         * Execute PCF on a 1*1 kernel.
         *
         * Execute PCSS with 16 taps blocker search and 16 taps PCF.
         */
        val QUALITY_LOW: Number
        /**
         * Parses a serialized ShadowGenerator and returns a new ShadowGenerator.
         * @param parsedShadowGenerator The JSON object to parse
         * @param scene The scene to create the shadow map for
         * @returns The parsed shadow generator
         */
        fun Parse(parsedShadowGenerator: Any, scene: Scene): ShadowGenerator
    }
}
