@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals", "FunctionName")
package babylonjs

import kotlin.js.Promise

/**
 * Manages the defines for the Material
 */
open external class MaterialDefines {
    /**
     * Specifies if the material needs to be re-calculated
     */
    val isDirty: Boolean
    /**
     * Marks the material to indicate that it has been re-calculated
     */
    fun markAsProcessed()
    /**
     * Marks the material to indicate that it needs to be re-calculated
     */
    fun markAsUnprocessed()
    /**
     * Marks the material to indicate all of its defines need to be re-calculated
     */
    fun markAllAsDirty()
    /**
     * Marks the material to indicate that image processing needs to be re-calculated
     */
    fun markAsImageProcessingDirty()
    /**
     * Marks the material to indicate the lights need to be re-calculated
     */
    fun markAsLightDirty()
    /**
     * Marks the attribute state as changed
     */
    fun markAsAttributesDirty()
    /**
     * Marks the texture state as changed
     */
    fun markAsTexturesDirty()
    /**
     * Marks the fresnel state as changed
     */
    fun markAsFresnelDirty()
    /**
     * Marks the misc state as changed
     */
    fun markAsMiscDirty()
    /**
     * Rebuilds the material defines
     */
    fun rebuild()
    /**
     * Specifies if two material defines are equal
     * @param other - A material define instance to compare to
     * @returns - Boolean indicating if the material defines are equal (true) or not (false)
     */
    fun isEqual(other: MaterialDefines): Boolean
    /**
     * Clones this instance's defines to another instance
     * @param other - material defines to clone values to
     */
    fun cloneTo(other: MaterialDefines)
    /**
     * Resets the material define values
     */
    fun reset()
}

/**
 * Base class for the main features of a material in Babylon.js
 */
open external class Material: IAnimatable {
    /**
     * The ID of the material
     */
    var id: String
    /**
     * Gets or sets the unique id of the material
     */
    var uniqueId: Number
    /**
     * The name of the material
     */
    var name: String
    /**
     * Gets or sets user defined metadata
     */
    var metadata: Any
    /**
     * For internal use only. Please do not use.
     */
    var reservedDataStore: Any
    /**
     * Specifies if the ready state should be checked on each call
     */
    var checkReadyOnEveryCall: Boolean
    /**
     * Specifies if the ready state should be checked once
     */
    var checkReadyOnlyOnce: Boolean
    /**
     * The state of the material
     */
    var state: String
    /**
     * List of inspectable custom properties (used by the Inspector)
     * @see [https://doc.babylonjs.com/how_to/debug_layer#extensibility]
     */
    var inspectableCustomProperties: Array<IInspectable>
    /**
     * Sets the alpha value of the material
     */
    /**
     * Gets the alpha value of the material
     */
    var alpha: Number
    /**
     * Sets the back-face culling state
     */
    /**
     * Gets the back-face culling state
     */
    var backFaceCulling: Boolean
    /**
     * Stores the value for side orientation
     */
    var sideOrientation: Number
    /**
     * Callback triggered when the material is compiled
     */
    var onCompiled: ((effect: Effect) -> Unit)?
    /**
     * Callback triggered when an error occurs
     */
    var onError: ((effect: Effect, errors: String) -> Unit)?
    /**
     * Callback triggered to get the render target textures
     */
    var getRenderTargetTextures: (() -> SmartArray<RenderTargetTexture>)?
    /**
     * Gets a Boolean indicating that current material needs to register RTT
     */
    val hasRenderTargetTextures: Boolean
    /**
     * Specifies if the material should be serialized
     */
    var doNotSerialize: Boolean
    /**
     * Stores the animations for the material
     */
    override var animations:Array<Animation>?
    /**
     * An event triggered when the material is disposed
     */
    var onDisposeObservable: Observable<Material>
    /**
     * Called during a dispose event
     */
    var onDispose: () -> Unit
    /**
     * An event triggered when the material is bound
     */
    val onBindObservable: Observable<AbstractMesh>
    /**
     * Called during a bind event
     */
    var onBind: (Mesh: AbstractMesh) -> Unit
    /**
     * An event triggered when the material is unbound
     */
    val onUnBindObservable: Observable<Material>
    /**
     * Sets the value of the alpha mode.
     *
     * | Value | Type | Description |
     * | --- | --- | --- |
     * | 0 | ALPHA_DISABLE |   |
     * | 1 | ALPHA_ADD |   |
     * | 2 | ALPHA_COMBINE |   |
     * | 3 | ALPHA_SUBTRACT |   |
     * | 4 | ALPHA_MULTIPLY |   |
     * | 5 | ALPHA_MAXIMIZED |   |
     * | 6 | ALPHA_ONEONE |   |
     * | 7 | ALPHA_PREMULTIPLIED |   |
     * | 8 | ALPHA_PREMULTIPLIED_PORTERDUFF |   |
     * | 9 | ALPHA_INTERPOLATE |   |
     * | 10 | ALPHA_SCREENMODE |   |
     *
     */
    /**
     * Gets the value of the alpha mode
     */
    var alphaMode: Number
    /**
     * Sets the need depth pre-pass value
     */
    /**
     * Gets the depth pre-pass value
     */
    var needDepthPrePass: Boolean
    /**
     * Specifies if depth writing should be disabled
     */
    var disableDepthWrite: Boolean
    /**
     * Specifies if depth writing should be forced
     */
    var forceDepthWrite: Boolean
    /**
     * Specifies if there should be a separate pass for culling
     */
    var separateCullingPass: Boolean
    /**
     * Sets the state for enabling fog
     */
    /**
     * Gets the value of the fog enabled state
     */
    var fogEnabled: Boolean
    /**
     * Stores the size of points
     */
    var pointSize: Number
    /**
     * Stores the z offset value
     */
    var zOffset: Number
    /**
     * Gets a value specifying if wireframe mode is enabled
     */
    /**
     * Sets the state of wireframe mode
     */
    var wireframe: Boolean
    /**
     * Gets the value specifying if point clouds are enabled
     */
    /**
     * Sets the state of point cloud mode
     */
    var pointsCloud: Boolean
    /**
     * Gets the material fill mode
     */
    /**
     * Sets the material fill mode
     */
    var fillMode: Number
    /**
     * Creates a material instance
     * @param name defines the name of the material
     * @param scene defines the scene to reference
     * @param doNotAdd specifies if the material should be added to the scene
     */
    constructor(name: String, scene: Scene, doNotAdd: Boolean?)
    /**
     * Returns a String representation of the current material
     * @param fullDetails defines a Boolean indicating which levels of logging is desired
     * @returns a String with material information
     */
    fun toString(fullDetails: Boolean?): String
    /**
     * Gets the class name of the material
     * @returns a String with the class name of the material
     */
    fun getClassName(): String
    /**
     * Specifies if updates for the material been locked
     */
    val isFrozen: Boolean
    /**
     * Locks updates for the material
     */
    fun freeze()
    /**
     * Unlocks updates for the material
     */
    fun unfreeze()
    /**
     * Specifies if the material is ready to be used
     * @param mesh defines the mesh to check
     * @param useInstances specifies if instances should be used
     * @returns a Boolean indicating if the material is ready to be used
     */
    fun isReady(mesh: AbstractMesh?, useInstances: Boolean?): Boolean
    /**
     * Specifies that the submesh is ready to be used
     * @param mesh defines the mesh to check
     * @param subMesh defines which submesh to check
     * @param useInstances specifies that instances should be used
     * @returns a Boolean indicating that the submesh is ready or not
     */
    fun isReadyForSubMesh(mesh: AbstractMesh, subMesh: BaseSubMesh, useInstances: Boolean?): Boolean
    /**
     * Returns the material effect
     * @returns the effect associated with the material
     */
    fun getEffect(): Effect?
    /**
     * Returns the current scene
     * @returns a Scene
     */
    fun getScene(): Scene
    /**
     * Specifies if the material will require alpha blending
     * @returns a Boolean specifying if alpha blending is needed
     */
    fun needAlphaBlending(): Boolean
    /**
     * Specifies if the mesh will require alpha blending
     * @param mesh defines the mesh to check
     * @returns a Boolean specifying if alpha blending is needed for the mesh
     */
    fun needAlphaBlendingForMesh(mesh: AbstractMesh): Boolean
    /**
     * Specifies if this material should be rendered in alpha test mode
     * @returns a Boolean specifying if an alpha test is needed.
     */
    fun needAlphaTesting(): Boolean
    /**
     * Gets the texture used for the alpha test
     * @returns the texture to use for alpha testing
     */
    fun getAlphaTestTexture(): BaseTexture?
    /**
     * Marks the material to indicate that it needs to be re-calculated
     */
    fun markDirty()
    /**
     * Binds the material to the mesh
     * @param world defines the world transformation matrix
     * @param mesh defines the mesh to bind the material to
     */
    fun bind(world: Matrix, mesh: Mesh?)
    /**
     * Binds the submesh to the material
     * @param world defines the world transformation matrix
     * @param mesh defines the mesh containing the submesh
     * @param subMesh defines the submesh to bind the material to
     */
    fun bindForSubMesh(world: Matrix, mesh: Mesh, subMesh: SubMesh)
    /**
     * Binds the world matrix to the material
     * @param world defines the world transformation matrix
     */
    fun bindOnlyWorldMatrix(world: Matrix)
    /**
     * Binds the scene's uniform buffer to the effect.
     * @param effect defines the effect to bind to the scene uniform buffer
     * @param sceneUbo defines the uniform buffer storing scene data
     */
    fun bindSceneUniformBuffer(effect: Effect, sceneUbo: UniformBuffer)
    /**
     * Binds the view matrix to the effect
     * @param effect defines the effect to bind the view matrix to
     */
    fun bindView(effect: Effect)
    /**
     * Binds the view projection matrix to the effect
     * @param effect defines the effect to bind the view projection matrix to
     */
    fun bindViewProjection(effect: Effect)
    /**
     * Unbinds the material from the mesh
     */
    fun unbind()
    /**
     * Gets the active textures from the material
     * @returns an array of textures
     */
    fun getActiveTextures(): Array<BaseTexture>
    /**
     * Specifies if the material uses a texture
     * @param texture defines the texture to check against the material
     * @returns a Boolean specifying if the material uses the texture
     */
    fun hasTexture(texture: BaseTexture): Boolean
    /**
     * Makes a duplicate of the material, and gives it a new name
     * @param name defines the new name for the duplicated material
     * @returns the cloned material
     */
    fun clone(name: String): Material?
    /**
     * Gets the meshes bound to the material
     * @returns an array of meshes bound to the material
     */
    fun getBindedMeshes(): Array<AbstractMesh>
    /**
     * Force shader compilation
     * @param mesh defines the mesh associated with this material
     * @param onCompiled defines a function to execute once the material is compiled
     * @param options defines the options to configure the compilation
     */
    fun forceCompilation(mesh: AbstractMesh, onCompiled: ((material: Material) -> Unit)? = definedExternally, options: Any? = definedExternally /*Partial?<{ var clipPlane: Boolean }>*/)
    /**
     * Force shader compilation
     * @param mesh defines the mesh that will use this material
     * @param options defines additional options for compiling the shaders
     * @returns a promise that resolves when the compilation completes
     */
    fun forceCompilation(mesh: AbstractMesh, options: Any? = definedExternally /*Partial?<{ var clipPlane: Boolean }>*/): Promise<Unit>
    /**
     * Marks a define in the material to indicate that it needs to be re-computed
     * @param flag defines a flag used to determine which parts of the material have to be marked as dirty
     */
    fun markAsDirty(flag: Number)
    /**
     * Disposes the material
     * @param forceDisposeEffect specifies if effects should be forcefully disposed
     * @param forceDisposeTextures specifies if textures should be forcefully disposed
     * @param notBoundToMesh specifies if the material that is being disposed is known to be not bound to Any mesh
     */
    fun dispose(forceDisposeEffect: Boolean? = definedExternally, forceDisposeTextures: Boolean? = definedExternally, notBoundToMesh: Boolean? = definedExternally)
    /**
     * Serializes this material
     * @returns the serialized material object
     */
    fun serialize(): Any

    companion object {
        /**
         * Returns the triangle fill mode
         */
        val TriangleFillMode: Number
        /**
         * Returns the wireframe mode
         */
        val WireFrameFillMode: Number
        /**
         * Returns the point fill mode
         */
        val PointFillMode: Number
        /**
         * Returns the point list draw mode
         */
        val PointListDrawMode: Number
        /**
         * Returns the line list draw mode
         */
        val LineListDrawMode: Number
        /**
         * Returns the line loop draw mode
         */
        val LineLoopDrawMode: Number
        /**
         * Returns the line strip draw mode
         */
        val LineStripDrawMode: Number
        /**
         * Returns the triangle strip draw mode
         */
        val TriangleStripDrawMode: Number
        /**
         * Returns the triangle fan draw mode
         */
        val TriangleFanDrawMode: Number
        /**
         * Stores the clock-wise side orientation
         */
        val ClockWiseSideOrientation: Number
        /**
         * Stores the counter clock-wise side orientation
         */
        val CounterClockWiseSideOrientation: Number
        /**
         * The dirty texture flag value
         */
        val TextureDirtyFlag: Number
        /**
         * The dirty light flag value
         */
        val LightDirtyFlag: Number
        /**
         * The dirty fresnel flag value
         */
        val FresnelDirtyFlag: Number
        /**
         * The dirty attribute flag value
         */
        val AttributesDirtyFlag: Number
        /**
         * The dirty misc flag value
         */
        val MiscDirtyFlag: Number
        /**
         * The all dirty flag value
         */
        val AllDirtyFlag: Number
        /**
         * Creates a material from parsed material data
         * @param parsedMaterial defines parsed material data
         * @param scene defines the hosting scene
         * @param rootUrl defines the root URL to use to load textures
         * @returns a new material
         */
        fun Parse(parsedMaterial: Any, scene: Scene, rootUrl: String): Material?
    }
}

/**
 * Base class of materials working in push mode in babylon JS
 * @hidden
 */
open external class PushMaterial: Material {
    /**
     * Gets or sets a Boolean indicating that the material is allowed to do shader hot swapping.
     * This means that the material can keep using a previous shader while a new one is being compiled.
     * This is mostly used when shader parallel compilation is supported (true by default)
     */
    var allowShaderHotSwapping: Boolean
    
    constructor(name: String, scene: Scene)
    
    /**
     * Binds the given normal matrix to the active effect
     *
     * @param normalMatrix the matrix to bind
     */
    fun bindOnlyNormalMatrix(normalMatrix: Matrix)
}

external class StandardMaterialDefines: MaterialDefines, IImageProcessingConfigurationDefines {
    var MAINUV1: Boolean
    var MAINUV2: Boolean
    var DIFFUSE: Boolean
    var DIFFUSEDIRECTUV: Number
    var AMBIENT: Boolean
    var AMBIENTDIRECTUV: Number
    var OPACITY: Boolean
    var OPACITYDIRECTUV: Number
    var OPACITYRGB: Boolean
    var REFLECTION: Boolean
    var EMISSIVE: Boolean
    var EMISSIVEDIRECTUV: Number
    var SPECULAR: Boolean
    var SPECULARDIRECTUV: Number
    var BUMP: Boolean
    var BUMPDIRECTUV: Number
    var PARALLAX: Boolean
    var PARALLAXOCCLUSION: Boolean
    var SPECULAROVERALPHA: Boolean
    var CLIPPLANE: Boolean
    var CLIPPLANE2: Boolean
    var CLIPPLANE3: Boolean
    var CLIPPLANE4: Boolean
    var ALPHATEST: Boolean
    var DEPTHPREPASS: Boolean
    var ALPHAFROMDIFFUSE: Boolean
    var POINTSIZE: Boolean
    var FOG: Boolean
    var SPECULARTERM: Boolean
    var DIFFUSEFRESNEL: Boolean
    var OPACITYFRESNEL: Boolean
    var REFLECTIONFRESNEL: Boolean
    var REFRACTIONFRESNEL: Boolean
    var EMISSIVEFRESNEL: Boolean
    var FRESNEL: Boolean
    var NORMAL: Boolean
    var UV1: Boolean
    var UV2: Boolean
    var VERTEXCOLOR: Boolean
    var VERTEXALPHA: Boolean
    var NUM_BONE_INFLUENCERS: Number
    var BonesPerMesh: Number
    var BONETEXTURE: Boolean
    var INSTANCES: Boolean
    var GLOSSINESS: Boolean
    var ROUGHNESS: Boolean
    var EMISSIVEASILLUMINATION: Boolean
    var LINKEMISSIVEWITHDIFFUSE: Boolean
    var REFLECTIONFRESNELFROMSPECULAR: Boolean
    var LIGHTMAP: Boolean
    var LIGHTMAPDIRECTUV: Number
    var OBJECTSPACE_NORMALMAP: Boolean
    var USELIGHTMAPASSHADOWMAP: Boolean
    var REFLECTIONMAP_3D: Boolean
    var REFLECTIONMAP_SPHERICAL: Boolean
    var REFLECTIONMAP_PLANAR: Boolean
    var REFLECTIONMAP_CUBIC: Boolean
    var USE_LOCAL_REFLECTIONMAP_CUBIC: Boolean
    var REFLECTIONMAP_PROJECTION: Boolean
    var REFLECTIONMAP_SKYBOX: Boolean
    var REFLECTIONMAP_SKYBOX_TRANSFORMED: Boolean
    var REFLECTIONMAP_EXPLICIT: Boolean
    var REFLECTIONMAP_EQUIRECTANGULAR: Boolean
    var REFLECTIONMAP_EQUIRECTANGULAR_FIXED: Boolean
    var REFLECTIONMAP_MIRROREDEQUIRECTANGULAR_FIXED: Boolean
    var INVERTCUBICMAP: Boolean
    var LOGARITHMICDEPTH: Boolean
    var REFRACTION: Boolean
    var REFRACTIONMAP_3D: Boolean
    var REFLECTIONOVERALPHA: Boolean
    var TWOSIDEDLIGHTING: Boolean
    var SHADOWFLOAT: Boolean
    var MORPHTARGETS: Boolean
    var MORPHTARGETS_NORMAL: Boolean
    var MORPHTARGETS_TANGENT: Boolean
    var NUM_MORPH_INFLUENCERS: Number
    var NONUNIFORMSCALING: Boolean
    var PREMULTIPLYALPHA: Boolean
    var IMAGEPROCESSING: Boolean
    var VIGNETTE: Boolean
    var VIGNETTEBLENDMODEMULTIPLY: Boolean
    var VIGNETTEBLENDMODEOPAQUE: Boolean
    var TONEMAPPING: Boolean
    var TONEMAPPING_ACES: Boolean
    var CONTRAST: Boolean
    var COLORCURVES: Boolean
    var COLORGRADING: Boolean
    var COLORGRADING3D: Boolean
    var SAMPLER3DGREENDEPTH: Boolean
    var SAMPLER3DBGRMAP: Boolean
    var IMAGEPROCESSINGPOSTPROCESS: Boolean
    var MULTIVIEW: Boolean
    /**
     * If the reflection texture on this material is in linear color space
     * @hidden
     */
    var IS_REFLECTION_LINEAR: Boolean
    /**
     * If the refraction texture on this material is in linear color space
     * @hidden
     */
    var IS_REFRACTION_LINEAR: Boolean
    var EXPOSURE: Boolean
    constructor()
    fun setReflectionMode(modeToEnable: String)
}

/**
 * This represents all the required information to add a fresnel effect on a material:
 * @see [http://doc.babylonjs.com/how_to/how_to_use_fresnelparameters]
 */
external class FresnelParameters {
    /**
     * Define if the fresnel effect is enable or not.
     */
    var isEnabled: Boolean
    /**
     * Define the color used on edges (grazing angle)
     */
    var leftColor: Color3
    /**
     * Define the color used on center
     */
    var rightColor: Color3
    /**
     * Define bias applied to computed fresnel term
     */
    var bias: Number
    /**
     * Defined the power exponent applied to fresnel term
     */
    var power: Number
    /**
     * Clones the current fresnel and its valuues
     * @returns a clone fresnel configuration
     */
    fun clone(): FresnelParameters
    /**
     * Serializes the current fresnel parameters to a JSON representation.
     * @return the JSON serialization
     */
    fun serialize(): Any

    companion object {
        /**
         * Parse a JSON object and deserialize it to a new Fresnel parameter object.
         * @param parsedFresnelParameters Define the JSON representation
         * @returns the parsed parameters
         */
        fun Parse(parsedFresnelParameters: Any): FresnelParameters
    }
}

/**
 * This is the default material used in Babylon. It is the best trade off between quality
 * and performances.
 * @see [http://doc.babylonjs.com/babylon101/materials]
 */
external class StandardMaterial: PushMaterial {
    /**
     * The basic texture of the material as viewed under a light.
     */
    var diffuseTexture: BaseTexture?
    /**
     * AKA Occlusion Texture in other nomenclature, it helps adding baked shadows into your material.
     */
    var ambientTexture: BaseTexture?
    /**
     * Define the transparency of the material from a texture.
     * The final alpha value can be read either from the red channel (if texture.getAlphaFromRGB is false)
     * or from the luminance or the current texel (if texture.getAlphaFromRGB is true)
     */
    var opacityTexture: BaseTexture?
    /**
     * Define the texture used to display the reflection.
     * @see [http://doc.babylonjs.com/how_to/reflect#how-to-obtain-reflections-and-refractions]
     */
    var reflectionTexture: BaseTexture?
    /**
     * Define texture of the material as if self lit.
     * This will be mixed in the final result even in the absence of light.
     */
    var emissiveTexture: BaseTexture?
    /**
     * Define how the color and intensity of the highlight given by the light in the material.
     */
    var specularTexture: BaseTexture?
    /**
     * Bump mapping is a technique to simulate bump and dents on a rendered surface.
     * These are made by creating a normal map from an image. The means to do this can be found on the web, a search for 'normal map generator' will bring up free and paid for methods of doing this.
     * @see [http://doc.babylonjs.com/how_to/more_materials#bump-map]
     */
    var bumpTexture: BaseTexture?
    /**
     * Complex lighting can be computationally expensive to compute at runtime.
     * To save on computation, lightmaps may be used to store calculated lighting in a texture which will be applied to a given mesh.
     * @see [http://doc.babylonjs.com/babylon101/lights#lightmaps]
     */
    var lightmapTexture: BaseTexture?
    /**
     * Define the texture used to display the refraction.
     * @see [http://doc.babylonjs.com/how_to/reflect#how-to-obtain-reflections-and-refractions]
     */
    var refractionTexture: BaseTexture?
    /**
     * The color of the material lit by the environmental background lighting.
     * @see [http://doc.babylonjs.com/babylon101/materials#ambient-color-example]
     */
    var ambientColor: Color3
    /**
     * The basic color of the material as viewed under a light.
     */
    var diffuseColor: Color3
    /**
     * Define how the color and intensity of the highlight given by the light in the material.
     */
    var specularColor: Color3
    /**
     * Define the color of the material as if self lit.
     * This will be mixed in the final result even in the absence of light.
     */
    var emissiveColor: Color3
    /**
     * Defines how sharp are the highlights in the material.
     * The bigger the value the sharper giving a more glossy feeling to the result.
     * Reversely, the smaller the value the blurrier giving a more rough feeling to the result.
     */
    var specularPower: Number
    /**
     * Does the transparency come from the diffuse texture alpha channel.
     */
    var useAlphaFromDiffuseTexture: Boolean
    /**
     * If true, the emissive value is added into the end result, otherwise it is multiplied in.
     */
    var useEmissiveAsIllumination: Boolean
    /**
     * If true, some kind of energy conservation will prevent the end result to be more than 1 by reducing
     * the emissive level when the final color is close to one.
     */
    var linkEmissiveWithDiffuse: Boolean
    /**
     * Specifies that the material will keep the specular highlights over a transparent surface (only the most limunous ones).
     * A car glass is a good exemple of that. When sun reflects on it you can not see what is behind.
     */
    var useSpecularOverAlpha: Boolean
    /**
     * Specifies that the material will keeps the reflection highlights over a transparent surface (only the most limunous ones).
     * A car glass is a good exemple of that. When the street lights reflects on it you can not see what is behind.
     */
    var useReflectionOverAlpha: Boolean
    /**
     * Does lights from the scene impacts this material.
     * It can be a nice trick for performance to disable lighting on a fully emissive material.
     */
    var disableLighting: Boolean
    /**
     * Allows using an object space normal map (instead of tangent space).
     */
    var useObjectSpaceNormalMap: Boolean
    /**
     * Is parallax enabled or not.
     * @see [http://doc.babylonjs.com/how_to/using_parallax_mapping]
     */
    var useParallax: Boolean
    /**
     * Is parallax occlusion enabled or not.
     * If true, the outcome is way more realistic than traditional Parallax but you can expect a performance hit that worthes consideration.
     * @see [http://doc.babylonjs.com/how_to/using_parallax_mapping]
     */
    var useParallaxOcclusion: Boolean
    /**
     * Apply a scaling factor that determine which "depth" the height map should reprensent. A value between 0.05 and 0.1 is reasonnable in Parallax, you can reach 0.2 using Parallax Occlusion.
     */
    var parallaxScaleBias: Number
    /**
     * Helps to define how blurry the reflections should appears in the material.
     */
    var roughness: Number
    /**
     * In case of refraction, define the value of the indice of refraction.
     * @see [http://doc.babylonjs.com/how_to/reflect#how-to-obtain-reflections-and-refractions]
     */
    var indexOfRefraction: Number
    /**
     * Invert the refraction texture alongside the y axis.
     * It can be useful with procedural textures or probe for instance.
     * @see [http://doc.babylonjs.com/how_to/reflect#how-to-obtain-reflections-and-refractions]
     */
    var invertRefractionY: Boolean
    /**
     * Defines the alpha limits in alpha test mode.
     */
    var alphaCutOff: Number
    /**
     * In case of light mapping, define whether the map contains light or shadow informations.
     */
    var useLightmapAsShadowmap: Boolean
    /**
     * Define the diffuse fresnel parameters of the material.
     * @see [http://doc.babylonjs.com/how_to/how_to_use_fresnelparameters]
     */
    var diffuseFresnelParameters: FresnelParameters
    /**
     * Define the opacity fresnel parameters of the material.
     * @see [http://doc.babylonjs.com/how_to/how_to_use_fresnelparameters]
     */
    var opacityFresnelParameters: FresnelParameters
    /**
     * Define the reflection fresnel parameters of the material.
     * @see [http://doc.babylonjs.com/how_to/how_to_use_fresnelparameters]
     */
    var reflectionFresnelParameters: FresnelParameters
    /**
     * Define the refraction fresnel parameters of the material.
     * @see [http://doc.babylonjs.com/how_to/how_to_use_fresnelparameters]
     */
    var refractionFresnelParameters: FresnelParameters
    /**
     * Define the emissive fresnel parameters of the material.
     * @see [http://doc.babylonjs.com/how_to/how_to_use_fresnelparameters]
     */
    var emissiveFresnelParameters: FresnelParameters
    /**
     * If true automatically deducts the fresnels values from the material specularity.
     * @see [http://doc.babylonjs.com/how_to/how_to_use_fresnelparameters]
     */
    var useReflectionFresnelFromSpecular: Boolean
    /**
     * Defines if the glossiness/roughness of the material should be read from the specular map alpha channel
     */
    var useGlossinessFromSpecularMapAlpha: Boolean
    /**
     * Defines the maximum Number of lights that can be used in the material
     */
    var maxSimultaneousLights: Number
    /**
     * If sets to true, x component of normal map value will invert (x = 1.0 - x).
     */
    var invertNormalMapX: Boolean
    /**
     * If sets to true, y component of normal map value will invert (y = 1.0 - y).
     */
    var invertNormalMapY: Boolean
    /**
     * If sets to true and backfaceCulling is false, normals will be flipped on the backside.
     */
    var twoSidedLighting: Boolean
    /**
     * Gets the image processing configuration used either in this material.
     */
    /**
     * Sets the Default image processing configuration used either in the this material.
     *
     * If sets to null, the scene one is in use.
     */
    var imageProcessingConfiguration: ImageProcessingConfiguration
    /**
     * Gets wether the color curves effect is enabled.
     */
    /**
     * Sets wether the color curves effect is enabled.
     */
    var cameraColorCurvesEnabled: Boolean
    /**
     * Gets wether the color grading effect is enabled.
     */
    /**
     * Gets wether the color grading effect is enabled.
     */
    var cameraColorGradingEnabled: Boolean
    /**
     * Gets wether tonemapping is enabled or not.
     */
    /**
     * Sets wether tonemapping is enabled or not
     */
    var cameraToneMappingEnabled: Boolean
    /**
     * The camera exposure used on this material.
     * This property is here and not in the camera to allow controlling exposure without full screen post process.
     * This corresponds to a photographic exposure.
     */
    /**
     * The camera exposure used on this material.
     * This property is here and not in the camera to allow controlling exposure without full screen post process.
     * This corresponds to a photographic exposure.
     */
    var cameraExposure: Number
    /**
     * Gets The camera contrast used on this material.
     */
    /**
     * Sets The camera contrast used on this material.
     */
    var cameraContrast: Number
    /**
     * Gets the Color Grading 2D Lookup Texture.
     */
    /**
     * Sets the Color Grading 2D Lookup Texture.
     */
    var cameraColorGradingTexture: BaseTexture?
    /**
     * The color grading curves provide additional color adjustmnent that is applied after Any color grading transform (3D LUT).
     * They allow basic adjustment of saturation and small exposure adjustments, along with color filter tinting to provide white balance adjustment or more stylistic effects.
     * These are similar to controls found in many professional imaging or colorist software. The global controls are applied to the entire image. For advanced tuning, extra controls are provided to adjust the shadow, midtone and highlight areas of the image
     * corresponding to low luminance, medium luminance, and high luminance areas respectively.
     */
    /**
     * The color grading curves provide additional color adjustmnent that is applied after Any color grading transform (3D LUT).
     * They allow basic adjustment of saturation and small exposure adjustments, along with color filter tinting to provide white balance adjustment or more stylistic effects.
     * These are similar to controls found in many professional imaging or colorist software. The global controls are applied to the entire image. For advanced tuning, extra controls are provided to adjust the shadow, midtone and highlight areas of the image
     * corresponding to low luminance, medium luminance, and high luminance areas respectively.
     */
    var cameraColorCurves: ColorCurves?
    /**
     * Custom callback helping to override the default shader used in the material.
     */
    var customShaderNameResolve: (shaderName: String, uniforms: Array<String>, uniformBuffers: Array<String>, samplers: Array<String>, defines: StandardMaterialDefines) -> String
    /**
     * Instantiates a new standard material.
     * This is the default material used in Babylon. It is the best trade off between quality
     * and performances.
     * @see [http://doc.babylonjs.com/babylon101/materials]
     * @param name Define the name of the material in the scene
     * @param scene Define the scene the material belong to
     */
    constructor(name: String, scene: Scene)
    /**
     * In case the depth buffer does not allow enough depth precision for your scene (might be the case in large scenes)
     * You can try switching to logarithmic depth.
     * @see [http://doc.babylonjs.com/how_to/using_logarithmic_depth_buffer]
     */
    var useLogarithmicDepth: Boolean
    /**
     * Get if the submesh is ready to be used and all its information available.
     * Child classes can use it to update shaders
     * @param mesh defines the mesh to check
     * @param subMesh defines which submesh to check
     * @param useInstances specifies that instances should be used
     * @returns a Boolean indicating that the submesh is ready or not
     */
    fun isReadyForSubMesh(mesh: AbstractMesh, subMesh: SubMesh, useInstances: Boolean?): Boolean
    /**
     * Builds the material UBO layouts.
     * Used internally during the effect preparation.
     */
    fun buildUniformLayout()

    companion object {
        /**
         * Creates a standard material from parsed material data
         * @param source defines the JSON representation of the material
         * @param scene defines the hosting scene
         * @param rootUrl defines the root URL to use to load textures and relative dependencies
         * @returns a new standard material
         */
        fun Parse(source: Any, scene: Scene, rootUrl: String): StandardMaterial
        /**
         * Are diffuse textures enabled in the application.
         */
        var DiffuseTextureEnabled: Boolean
        /**
         * Are ambient textures enabled in the application.
         */
        var AmbientTextureEnabled: Boolean
        /**
         * Are opacity textures enabled in the application.
         */
        var OpacityTextureEnabled: Boolean
        /**
         * Are reflection textures enabled in the application.
         */
        var ReflectionTextureEnabled: Boolean
        /**
         * Are emissive textures enabled in the application.
         */
        var EmissiveTextureEnabled: Boolean
        /**
         * Are specular textures enabled in the application.
         */
        var SpecularTextureEnabled: Boolean
        /**
         * Are bump textures enabled in the application.
         */
        var BumpTextureEnabled: Boolean
        /**
         * Are lightmap textures enabled in the application.
         */
        var LightmapTextureEnabled: Boolean
        /**
         * Are refraction textures enabled in the application.
         */
        var RefractionTextureEnabled: Boolean
        /**
         * Are color grading textures enabled in the application.
         */
        var ColorGradingTextureEnabled: Boolean
        /**
         * Are fresnels enabled in the application.
         */
        var FresnelEnabled: Boolean
    }
}
