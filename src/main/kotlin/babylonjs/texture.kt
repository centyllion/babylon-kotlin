@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals", "FunctionName")
package babylonjs

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.ArrayBufferView
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLImageElement
import org.w3c.files.Blob

/**
 * Base class of all the textures in babylon.
 * It groups all the common properties the materials, post process, lights... might need
 * in order to make a correct use of the texture.
 */
open external class BaseTexture: IAnimatable {

    override var animations: Array<Animation>?

    /**
     * Gets or sets the unique id of the texture
     */
    var uniqueId: Number
    /**
     * Define the name of the texture.
     */
    var name: String
    /**
     * Gets or sets an object used to store user defined information.
     */
    var metadata: Any
    /**
     * For internal use only. Please do not use.
     */
    var reservedDataStore: Any
    /**
     * Define if the texture is having a usable alpha value (can be use for transparency or glossiness for instance).
     */
    var hasAlpha: Boolean
    /**
     * Defines if the alpha value should be determined via the rgb values.
     * If true the luminance of the pixel might be used to find the corresponding alpha value.
     */
    var getAlphaFromRGB: Boolean
    /**
     * Intensity or strength of the texture.
     * It is commonly used by materials to fine tune the intensity of the texture
     */
    var level: Number
    /**
     * Define the UV chanel to use starting from 0 and defaulting to 0.
     * This is part of the texture as textures usually maps to one uv set.
     */
    var coordinatesIndex: Number
    /**
     * How a texture is mapped.
     *
     * | Value | Type                                | Description |
     * | ----- | ----------------------------------- | ----------- |
     * | 0     | EXPLICIT_MODE                       |             |
     * | 1     | SPHERICAL_MODE                      |             |
     * | 2     | PLANAR_MODE                         |             |
     * | 3     | CUBIC_MODE                          |             |
     * | 4     | PROJECTION_MODE                     |             |
     * | 5     | SKYBOX_MODE                         |             |
     * | 6     | INVCUBIC_MODE                       |             |
     * | 7     | EQUIRECTANGULAR_MODE                |             |
     * | 8     | FIXED_EQUIRECTANGULAR_MODE          |             |
     * | 9     | FIXED_EQUIRECTANGULAR_MIRRORED_MODE |             |
     */
    var coordinatesMode: Number
    /**
     * | Value | Type               | Description |
     * | ----- | ------------------ | ----------- |
     * | 0     | CLAMP_ADDRESSMODE  |             |
     * | 1     | WRAP_ADDRESSMODE   |             |
     * | 2     | MIRROR_ADDRESSMODE |             |
     */
    var wrapU: Number
    /**
     * | Value | Type               | Description |
     * | ----- | ------------------ | ----------- |
     * | 0     | CLAMP_ADDRESSMODE  |             |
     * | 1     | WRAP_ADDRESSMODE   |             |
     * | 2     | MIRROR_ADDRESSMODE |             |
     */
    var wrapV: Number
    /**
     * | Value | Type               | Description |
     * | ----- | ------------------ | ----------- |
     * | 0     | CLAMP_ADDRESSMODE  |             |
     * | 1     | WRAP_ADDRESSMODE   |             |
     * | 2     | MIRROR_ADDRESSMODE |             |
     */
    var wrapR: Number
    /**
     * With compliant hardware and browser (supporting anisotropic filtering)
     * this defines the level of anisotropic filtering in the texture.
     * The higher the better but the slower. This defaults to 4 as it seems to be the best tradeoff.
     */
    var anisotropicFilteringLevel: Number
    /**
     * Define if the texture is a cube texture or if false a 2d texture.
     */
    var isCube: Boolean
    /**
     * Define if the texture is a 3d texture (webgl 2) or if false a 2d texture.
     */
    var is3D: Boolean
    /**
     * Define if the texture contains data in gamma space (most of the png/jpg aside bump).
     * HDR texture are usually stored in linear space.
     * This only impacts the PBR and Background materials
     */
    var gammaSpace: Boolean
    /**
     * Gets whether or not the texture contains RGBD data.
     */
    val isRGBD: Boolean
    /**
     * Is Z inverted in the texture (useful in a cube texture).
     */
    var invertZ: Boolean
    /**
     * Are mip maps generated for this texture or not.
     */
    val noMipmap: Boolean
    /**
     * With prefiltered texture, defined the offset used during the prefiltering steps.
     */
    var lodGenerationOffset: Number
    /**
     * With prefiltered texture, defined the scale used during the prefiltering steps.
     */
    var lodGenerationScale: Number
    /**
     * Define if the texture is a render target.
     */
    var isRenderTarget: Boolean
    /**
     * Define the unique id of the texture in the scene.
     */
    val uid: String
   
    /**
     * Get the class name of the texture.
     * @returns "BaseTexture"
     */
    fun getClassName(): String
    /**
     * An event triggered when the texture is disposed.
     */
    var onDisposeObservable: Observable<BaseTexture>
    /**
     * Callback triggered when the texture has been disposed.
     * Kept for back compatibility, you can use the onDisposeObservable instead.
     */
    var onDispose: () -> Unit
    /**
     * Define the current state of the loading sequence when in delayed load mode.
     */
    var delayLoadState: Number
    /**
     * Define if the texture is preventing a material to render or not.
     * If not and the texture is not ready, the engine will use a default black texture instead.
     */
    val isBlocking: Boolean
    /**
     * Instantiates a new BaseTexture.
     * Base class of all the textures in babylon.
     * It groups all the common properties the materials, post process, lights... might need
     * in order to make a correct use of the texture.
     * @param scene Define the scene the texture blongs to
     */
    constructor(scene: Scene?)
    /**
     * Get the scene the texture belongs to.
     * @returns the scene or null if undefined
     */
    fun getScene(): Scene?
    /**
     * Get the texture transform matrix used to offset tile the texture for istance.
     * @returns the transformation matrix
     */
    fun getTextureMatrix(): Matrix
    /**
     * Get the texture reflection matrix used to rotate/transform the reflection.
     * @returns the reflection matrix
     */
    fun getReflectionTextureMatrix(): Matrix
    /**
     * Get the underlying lower level texture from Babylon.
     * @returns the insternal texture
     */
    fun getInternalTexture(): InternalTexture?
    /**
     * Get if the texture is ready to be consumed (either it is ready or it is not blocking)
     * @returns true if ready or not blocking
     */
    fun isReadyOrNotBlocking(): Boolean
    /**
     * Get if the texture is ready to be used (downloaded, converted, mip mapped...).
     * @returns true if fully ready
     */
    fun isReady(): Boolean
    /**
     * Get the size of the texture.
     * @returns the texture size.
     */
    fun getSize(): ISize
    /**
     * Get the base size of the texture.
     * It can be different from the size if the texture has been resized for POT for instance
     * @returns the base size
     */
    fun getBaseSize(): ISize
    /**
     * Update the sampling mode of the texture.
     * Default is Trilinear mode.
     *
     * | Value | Type               | Description |
     * | ----- | ------------------ | ----------- |
     * | 1     | NEAREST_SAMPLINGMODE or NEAREST_NEAREST_MIPLINEAR  | Nearest is: mag = nearest, min = nearest, mip = linear |
     * | 2     | BILINEAR_SAMPLINGMODE or LINEAR_LINEAR_MIPNEAREST | Bilinear is: mag = linear, min = linear, mip = nearest |
     * | 3     | TRILINEAR_SAMPLINGMODE or LINEAR_LINEAR_MIPLINEAR | Trilinear is: mag = linear, min = linear, mip = linear |
     * | 4     | NEAREST_NEAREST_MIPNEAREST |             |
     * | 5    | NEAREST_LINEAR_MIPNEAREST |             |
     * | 6    | NEAREST_LINEAR_MIPLINEAR |             |
     * | 7    | NEAREST_LINEAR |             |
     * | 8    | NEAREST_NEAREST |             |
     * | 9   | LINEAR_NEAREST_MIPNEAREST |             |
     * | 10   | LINEAR_NEAREST_MIPLINEAR |             |
     * | 11   | LINEAR_LINEAR |             |
     * | 12   | LINEAR_NEAREST |             |
     *
     *    > _mag_: magnification filter (close to the viewer)
     *    > _min_: minification filter (far from the viewer)
     *    > _mip_: filter used between mip map levels
     *@param samplingMode Define the new sampling mode of the texture
     */
    fun updateSamplingMode(samplingMode: Number)
    /**
     * Scales the texture if is `canRescale()`
     * @param ratio the resize factor we want to use to rescale
     */
    fun scale(ratio: Number)
    /**
     * Get if the texture can rescale.
     */
    val canRescale: Boolean
    /**
     * Triggers the load sequence in delayed load mode.
     */
    fun delayLoad()
    /**
     * Clones the texture.
     * @returns the cloned texture
     */
    fun clone(): BaseTexture?
    /**
     * Get the texture underlying type (INT, FLOAT...)
     */
    val textureType: Number
    /**
     * Get the texture underlying format (RGB, RGBA...)
     */
    val textureFormat: Number
    /**
     * Reads the pixels stored in the webgl texture and returns them as an ArrayBuffer.
     * This will returns an RGBA array buffer containing either in values (0-255) or
     * float values (0-1) depending of the underlying buffer type.
     * @param faceIndex defines the face of the texture to read (in case of cube texture)
     * @param level defines the LOD level of the texture to read (in case of Mip Maps)
     * @param buffer defines a user defined buffer to fill with data (can be null)
     * @returns The Array buffer containing the pixels data.
     */
    fun readPixels(faceIndex: Number?, level: Number?, buffer: ArrayBufferView?): ArrayBufferView?
    /**
     * Release and destroy the underlying lower level texture aka internalTexture.
     */
    fun releaseInternalTexture()
    /**
     * Get the polynomial representation of the texture data.
     * This is mainly use as a fast way to recover IBL Diffuse irradiance data.
     * @see [https://learnopengl.com/PBR/IBL/Diffuse-irradiance]
     */
    var sphericalPolynomial: SphericalPolynomial?
    /**
     * Dispose the texture and release its associated resources.
     */
    fun dispose()
    /**
     * Serialize the texture into a JSON representation that can be parsed later on.
     * @returns the JSON representation of the texture
     */
    fun serialize(): Any
    
    companion object {
        /**
         * Default anisotropic filtering level for the application.
         * It is set to 4 as a good tradeoff between perf and quality.
         */
        var DEFAULT_ANISOTROPIC_FILTERING_LEVEL: Number
        /**
         * Helper function to be called back once a list of texture contains only ready textures.
         * @param textures Define the list of textures to wait for
         * @param callback Define the callback triggered once the entire list will be ready
         */
        fun WhenAllReady(textures: Array<BaseTexture>, callback: () -> Unit)
    }
}

/**
 * This represents a texture in babylon. It can be easily loaded from a network, base64 or html input.
 * @see [http://doc.babylonjs.com/babylon101/materials#texture]
 */
open external class Texture: BaseTexture {
    /**
     * Define the url of the texture.
     */
    var url: String?
    /**
     * Define an offset on the texture to offset the u coordinates of the UVs
     * @see [http://doc.babylonjs.com/how_to/more_materials#offsetting]
     */
    var uOffset: Number
    /**
     * Define an offset on the texture to offset the v coordinates of the UVs
     * @see [http://doc.babylonjs.com/how_to/more_materials#offsetting]
     */
    var vOffset: Number
    /**
     * Define an offset on the texture to scale the u coordinates of the UVs
     * @see [http://doc.babylonjs.com/how_to/more_materials#tiling]
     */
    var uScale: Number
    /**
     * Define an offset on the texture to scale the v coordinates of the UVs
     * @see [http://doc.babylonjs.com/how_to/more_materials#tiling]
     */
    var vScale: Number
    /**
     * Define an offset on the texture to rotate around the u coordinates of the UVs
     * @see [http://doc.babylonjs.com/how_to/more_materials]
     */
    var uAng: Number
    /**
     * Define an offset on the texture to rotate around the v coordinates of the UVs
     * @see [http://doc.babylonjs.com/how_to/more_materials]
     */
    var vAng: Number
    /**
     * Define an offset on the texture to rotate around the w coordinates of the UVs (in case of 3d texture)
     * @see [http://doc.babylonjs.com/how_to/more_materials]
     */
    var wAng: Number
    /**
     * Defines the center of rotation (U)
     */
    var uRotationCenter: Number
    /**
     * Defines the center of rotation (V)
     */
    var vRotationCenter: Number
    /**
     * Defines the center of rotation (W)
     */
    var wRotationCenter: Number
    /**
     * List of inspectable custom properties (used by the Inspector)
     * @see [https://doc.babylonjs.com/how_to/debug_layer#extensibility]
     */
    var inspectableCustomProperties: Array<IInspectable>?
    /**
     * Observable triggered once the texture has been loaded.
     */
    var onLoadObservable: Observable<Texture>
    /**
     * Get the current sampling mode associated with the texture.
     */
    val samplingMode: Number
    /**
     * Gets a Boolean indicating if the texture needs to be inverted on the y axis during loading
     */
    val invertY: Boolean
    /**
     * Instantiates a new texture.
     * This represents a texture in babylon. It can be easily loaded from a network, base64 or html input.
     * @see [http://doc.babylonjs.com/babylon101/materials#texture]
     * @param url define the url of the picture to load as a texture
     * @param scene define the scene the texture will belong to
     * @param noMipmap define if the texture will require mip maps or not
     * @param invertY define if the texture needs to be inverted on the y axis during loading
     * @param samplingMode define the sampling mode we want for the texture while fectching from it (Texture.NEAREST_SAMPLINGMODE...)
     * @param onLoad define a callback triggered when the texture has been loaded
     * @param onError define a callback triggered when an error occurred during the loading session
     * @param buffer define the buffer to load the texture from in case the texture is loaded from a buffer representation
     * @param deleteBuffer define if the buffer we are loading the texture from should be deleted after load
     * @param format define the format of the texture we are trying to load (Engine.TEXTUREFORMAT_RGBA...)
     */
    constructor(
        url: String? = definedExternally,
        scene: Scene? = definedExternally,
        noMipmap: Boolean? = definedExternally,
        invertY: Boolean? = definedExternally,
        samplingMode: Number? = definedExternally,
        onLoad: (() -> Unit)? = definedExternally,
        onError: ((message: String?, exception: Any?) -> Unit)? = definedExternally,
        buffer: String? = definedExternally,
        deleteBuffer: Boolean? = definedExternally,
        format: Number? = definedExternally
    )
    /**
     * Instantiates a new texture.
     * This represents a texture in babylon. It can be easily loaded from a network, base64 or html input.
     * @see [http://doc.babylonjs.com/babylon101/materials#texture]
     * @param url define the url of the picture to load as a texture
     * @param scene define the scene the texture will belong to
     * @param noMipmap define if the texture will require mip maps or not
     * @param invertY define if the texture needs to be inverted on the y axis during loading
     * @param samplingMode define the sampling mode we want for the texture while fectching from it (Texture.NEAREST_SAMPLINGMODE...)
     * @param onLoad define a callback triggered when the texture has been loaded
     * @param onError define a callback triggered when an error occurred during the loading session
     * @param buffer define the buffer to load the texture from in case the texture is loaded from a buffer representation
     * @param deleteBuffer define if the buffer we are loading the texture from should be deleted after load
     * @param format define the format of the texture we are trying to load (Engine.TEXTUREFORMAT_RGBA...)
     */
    constructor(
        url: String? = definedExternally,
        scene: Scene? = definedExternally,
        noMipmap: Boolean? = definedExternally,
        invertY: Boolean? = definedExternally,
        samplingMode: Number? = definedExternally,
        onLoad: (() -> Unit)? = definedExternally,
        onError: ((message: String?, exception: Any?) -> Unit)? = definedExternally,
        buffer: ArrayBuffer? = definedExternally,
        deleteBuffer: Boolean? = definedExternally,
        format: Number? = definedExternally
    )
    /**
     * Instantiates a new texture.
     * This represents a texture in babylon. It can be easily loaded from a network, base64 or html input.
     * @see [http://doc.babylonjs.com/babylon101/materials#texture]
     * @param url define the url of the picture to load as a texture
     * @param scene define the scene the texture will belong to
     * @param noMipmap define if the texture will require mip maps or not
     * @param invertY define if the texture needs to be inverted on the y axis during loading
     * @param samplingMode define the sampling mode we want for the texture while fectching from it (Texture.NEAREST_SAMPLINGMODE...)
     * @param onLoad define a callback triggered when the texture has been loaded
     * @param onError define a callback triggered when an error occurred during the loading session
     * @param buffer define the buffer to load the texture from in case the texture is loaded from a buffer representation
     * @param deleteBuffer define if the buffer we are loading the texture from should be deleted after load
     * @param format define the format of the texture we are trying to load (Engine.TEXTUREFORMAT_RGBA...)
     */
    constructor(
        url: String? = definedExternally,
        scene: Scene? = definedExternally,
        noMipmap: Boolean? = definedExternally,
        invertY: Boolean? = definedExternally,
        samplingMode: Number? = definedExternally,
        onLoad: (() -> Unit)? = definedExternally,
        onError: ((message: String?, exception: Any?) -> Unit)? = definedExternally,
        buffer: HTMLImageElement? = definedExternally,
        deleteBuffer: Boolean? = definedExternally,
        format: Number? = definedExternally
    )
    /**
     * Instantiates a new texture.
     * This represents a texture in babylon. It can be easily loaded from a network, base64 or html input.
     * @see [http://doc.babylonjs.com/babylon101/materials#texture]
     * @param url define the url of the picture to load as a texture
     * @param scene define the scene the texture will belong to
     * @param noMipmap define if the texture will require mip maps or not
     * @param invertY define if the texture needs to be inverted on the y axis during loading
     * @param samplingMode define the sampling mode we want for the texture while fectching from it (Texture.NEAREST_SAMPLINGMODE...)
     * @param onLoad define a callback triggered when the texture has been loaded
     * @param onError define a callback triggered when an error occurred during the loading session
     * @param buffer define the buffer to load the texture from in case the texture is loaded from a buffer representation
     * @param deleteBuffer define if the buffer we are loading the texture from should be deleted after load
     * @param format define the format of the texture we are trying to load (Engine.TEXTUREFORMAT_RGBA...)
     */
    constructor(url: String?, scene: Scene?, noMipmap: Boolean?, invertY: Boolean?, samplingMode: Number?, onLoad: (() -> Unit)?, onError: ((message: String?, exception: Any?) -> Unit)?, buffer: Blob?, deleteBuffer: Boolean?, format: Number?)
    /**
     * Update the url (and optional buffer) of this texture if url was null during construction.
     * @param url the url of the texture
     * @param buffer the buffer of the texture (defaults to null)
     * @param onLoad callback called when the texture is loaded  (defaults to null)
     */
    fun updateURL(url: String, buffer: String?, onLoad: (() -> Unit)?)
    /**
     * Update the url (and optional buffer) of this texture if url was null during construction.
     * @param url the url of the texture
     * @param buffer the buffer of the texture (defaults to null)
     * @param onLoad callback called when the texture is loaded  (defaults to null)
     */
    fun updateURL(url: String, buffer: ArrayBuffer?, onLoad: (() -> Unit)?)
    /**
     * Update the url (and optional buffer) of this texture if url was null during construction.
     * @param url the url of the texture
     * @param buffer the buffer of the texture (defaults to null)
     * @param onLoad callback called when the texture is loaded  (defaults to null)
     */
    fun updateURL(url: String, buffer: HTMLImageElement?, onLoad: (() -> Unit)?)
    /**
     * Update the url (and optional buffer) of this texture if url was null during construction.
     * @param url the url of the texture
     * @param buffer the buffer of the texture (defaults to null)
     * @param onLoad callback called when the texture is loaded  (defaults to null)
     */
    fun updateURL(url: String, buffer: Blob?, onLoad: (() -> Unit)?)

    companion object {
        /** nearest is mag = nearest and min = nearest and mip = linear */
        val NEAREST_SAMPLINGMODE: Number
        /** nearest is mag = nearest and min = nearest and mip = linear */
        val NEAREST_NEAREST_MIPLINEAR: Number
        /** Bilinear is mag = linear and min = linear and mip = nearest */
        val BILINEAR_SAMPLINGMODE: Number
        /** Bilinear is mag = linear and min = linear and mip = nearest */
        val LINEAR_LINEAR_MIPNEAREST: Number
        /** Trilinear is mag = linear and min = linear and mip = linear */
        val TRILINEAR_SAMPLINGMODE: Number
        /** Trilinear is mag = linear and min = linear and mip = linear */
        val LINEAR_LINEAR_MIPLINEAR: Number
        /** mag = nearest and min = nearest and mip = nearest */
        val NEAREST_NEAREST_MIPNEAREST: Number
        /** mag = nearest and min = linear and mip = nearest */
        val NEAREST_LINEAR_MIPNEAREST: Number
        /** mag = nearest and min = linear and mip = linear */
        val NEAREST_LINEAR_MIPLINEAR: Number
        /** mag = nearest and min = linear and mip = none */
        val NEAREST_LINEAR: Number
        /** mag = nearest and min = nearest and mip = none */
        val NEAREST_NEAREST: Number
        /** mag = linear and min = nearest and mip = nearest */
        val LINEAR_NEAREST_MIPNEAREST: Number
        /** mag = linear and min = nearest and mip = linear */
        val LINEAR_NEAREST_MIPLINEAR: Number
        /** mag = linear and min = linear and mip = none */
        val LINEAR_LINEAR: Number
        /** mag = linear and min = nearest and mip = none */
        val LINEAR_NEAREST: Number
        /** Explicit coordinates mode */
        val EXPLICIT_MODE: Number
        /** Spherical coordinates mode */
        val SPHERICAL_MODE: Number
        /** Planar coordinates mode */
        val PLANAR_MODE: Number
        /** Cubic coordinates mode */
        val CUBIC_MODE: Number
        /** Projection coordinates mode */
        val PROJECTION_MODE: Number
        /** Inverse Cubic coordinates mode */
        val SKYBOX_MODE: Number
        /** Inverse Cubic coordinates mode */
        val INVCUBIC_MODE: Number
        /** Equirectangular coordinates mode */
        val EQUIRECTANGULAR_MODE: Number
        /** Equirectangular Fixed coordinates mode */
        val FIXED_EQUIRECTANGULAR_MODE: Number
        /** Equirectangular Fixed Mirrored coordinates mode */
        val FIXED_EQUIRECTANGULAR_MIRRORED_MODE: Number
        /** Texture is not repeating outside of 0..1 UVs */
        val CLAMP_ADDRESSMODE: Number
        /** Texture is repeating outside of 0..1 UVs */
        val WRAP_ADDRESSMODE: Number
        /** Texture is repeating and mirrored */
        val MIRROR_ADDRESSMODE: Number
        /**
         * Gets or sets a Boolean which defines if the texture url must be build from the serialized URL instead of just using the name and loading them side by side with the scene file
         */
        var UseSerializedUrlIfAny: Boolean
        /**
         * Parse the JSON representation of a texture in order to recreate the texture in the given scene.
         * @param parsedTexture Define the JSON representation of the texture
         * @param scene Define the scene the parsed texture should be instantiated in
         * @param rootUrl Define the root url of the parsing sequence in the case of relative dependencies
         * @returns The parsed texture if successful
         */
        fun Parse(parsedTexture: Any, scene: Scene, rootUrl: String): BaseTexture?
        /**
         * Creates a texture from its base 64 representation.
         * @param data Define the base64 payload without the data: prefix
         * @param name Define the name of the texture in the scene useful fo caching purpose for instance
         * @param scene Define the scene the texture should belong to
         * @param noMipmap Forces the texture to not create mip map information if true
         * @param invertY define if the texture needs to be inverted on the y axis during loading
         * @param samplingMode define the sampling mode we want for the texture while fectching from it (Texture.NEAREST_SAMPLINGMODE...)
         * @param onLoad define a callback triggered when the texture has been loaded
         * @param onError define a callback triggered when an error occurred during the loading session
         * @param format define the format of the texture we are trying to load (Engine.TEXTUREFORMAT_RGBA...)
         * @returns the created texture
         */
        fun CreateFromBase64String(data : String, name: String, scene: Scene, noMipmap: Boolean?, invertY: Boolean?, samplingMode: Number?, onLoad: (() -> Unit)?, onError: (() -> Unit)?, format: Number?): Texture
        /**
         * Creates a texture from its data: representation. (data: will be added in case only the payload has been passed in)
         * @param data Define the base64 payload without the data: prefix
         * @param name Define the name of the texture in the scene useful fo caching purpose for instance
         * @param buffer define the buffer to load the texture from in case the texture is loaded from a buffer representation
         * @param scene Define the scene the texture should belong to
         * @param deleteBuffer define if the buffer we are loading the texture from should be deleted after load
         * @param noMipmap Forces the texture to not create mip map information if true
         * @param invertY define if the texture needs to be inverted on the y axis during loading
         * @param samplingMode define the sampling mode we want for the texture while fectching from it (Texture.NEAREST_SAMPLINGMODE...)
         * @param onLoad define a callback triggered when the texture has been loaded
         * @param onError define a callback triggered when an error occurred during the loading session
         * @param format define the format of the texture we are trying to load (Engine.TEXTUREFORMAT_RGBA...)
         * @returns the created texture
         */
        fun LoadFromDataString(name: String, buffer: Any, scene: Scene, deleteBuffer: Boolean?, noMipmap: Boolean?, invertY: Boolean?, samplingMode: Number?, onLoad: (() -> Unit)?, onError: ((message: String?, exception: Any?) -> Unit)?, format: Number?): Texture
    }
}

/**
 * A class extending Texture allowing drawing on a texture
 * @see [http://doc.babylonjs.com/how_to/dynamictexture]
 */
external class DynamicTexture: Texture {
    /**
     * Creates a DynamicTexture
     * @param name defines the name of the texture
     * @param options provides 3 alternatives for width and height of texture, a canvas, object with width and height properties, Number for both width and height
     * @param scene defines the scene where you want the texture
     * @param generateMipMaps defines the use of MinMaps or not (default is false)
     * @param samplingMode defines the sampling mode to use (default is Texture.TRILINEAR_SAMPLINGMODE)
     * @param format defines the texture format to use (default is Engine.TEXTUREFORMAT_RGBA)
     */
    constructor(name: String, options: TextureOptions?, scene: Scene?, generateMipMaps: Boolean = definedExternally, samplingMode: Number? = definedExternally, format: Number? = definedExternally)

    /**
     * Resizes the texture
     * @param width the new width
     * @param height the new height
     */
    fun scaleTo(width: Number, height: Number)
    /**
     * Gets the context of the canvas used by the texture
     * @returns the canvas context of the dynamic texture
     */
    fun getContext(): CanvasRenderingContext2D
    /**
     * Clears the texture
     */
    fun clear()
    /**
     * Updates the texture
     * @param invertY defines the direction for the Y axis (default is true - y increases downwards)
     * @param premulAlpha defines if alpha is stored as premultiplied (default is false)
     */
    fun update(invertY: Boolean?, premulAlpha: Boolean?)
    /**
     * Draws text onto the texture
     * @param text defines the text to be drawn
     * @param x defines the placement of the text from the left
     * @param y defines the placement of the text from the top when invertY is true and from the bottom when false
     * @param font defines the font to be used with font-style, font-size, font-name
     * @param color defines the color used for the text
     * @param clearColor defines the color for the canvas, use null to not overwrite canvas
     * @param invertY defines the direction for the Y axis (default is true - y increases downwards)
     * @param update defines whether texture is immediately update (default is true)
     */
    fun drawText(text: String, x: Number, y: Number, font: String, color: String, clearColor: String, invertY: Boolean?, update: Boolean?)
}

/**
 * Raw texture can help creating a texture directly from an array of data.
 * This can be super useful if you either get the data from an uncompressed source or
 * if you wish to create your texture pixel by pixel.
 */
external class RawTexture: Texture {
    /**
     * Define the format of the data (RGB, RGBA... Engine.TEXTUREFORMAT_xxx)
     */
    var format: Number
    /**
     * Instantiates a new RawTexture.
     * Raw texture can help creating a texture directly from an array of data.
     * This can be super useful if you either get the data from an uncompressed source or
     * if you wish to create your texture pixel by pixel.
     * @param data define the array of data to use to create the texture
     * @param width define the width of the texture
     * @param height define the height of the texture
     * @param format define the format of the data (RGB, RGBA... Engine.TEXTUREFORMAT_xxx)
     * @param scene  define the scene the texture belongs to
     * @param generateMipMaps define whether mip maps should be generated or not
     * @param invertY define if the data should be flipped on Y when uploaded to the GPU
     * @param samplingMode define the texture sampling mode (Texture.xxx_SAMPLINGMODE)
     * @param type define the format of the data (int, float... Engine.TEXTURETYPE_xxx)
     */
    constructor(data: ArrayBufferView, width: Number, height: Number,
        /**
         * Define the format of the data (RGB, RGBA... Engine.TEXTUREFORMAT_xxx)
         */
        format: Number, scene: Scene, generateMipMaps: Boolean?, invertY: Boolean?, samplingMode: Number?, type: Number?)
    /**
     * Updates the texture underlying data.
     * @param data Define the new data of the texture
     */
    fun update(data: ArrayBufferView)
    
    companion object {
        /**
         * Creates a luminance texture from some data.
         * @param data Define the texture data
         * @param width Define the width of the texture
         * @param height Define the height of the texture
         * @param scene Define the scene the texture belongs to
         * @param generateMipMaps Define whether or not to create mip maps for the texture
         * @param invertY define if the data should be flipped on Y when uploaded to the GPU
         * @param samplingMode define the texture sampling mode (Texture.xxx_SAMPLINGMODE)
         * @returns the luminance texture
         */
        fun CreateLuminanceTexture(data : ArrayBufferView, width: Number, height: Number, scene: Scene, generateMipMaps: Boolean? = definedExternally, invertY: Boolean?= definedExternally, samplingMode: Number? = definedExternally): RawTexture
        /**
         * Creates a luminance alpha texture from some data.
         * @param data Define the texture data
         * @param width Define the width of the texture
         * @param height Define the height of the texture
         * @param scene Define the scene the texture belongs to
         * @param generateMipMaps Define whether or not to create mip maps for the texture
         * @param invertY define if the data should be flipped on Y when uploaded to the GPU
         * @param samplingMode define the texture sampling mode (Texture.xxx_SAMPLINGMODE)
         * @returns the luminance alpha texture
         */
        fun CreateLuminanceAlphaTexture(data : ArrayBufferView, width: Number, height: Number, scene: Scene, generateMipMaps: Boolean? = definedExternally, invertY: Boolean? = definedExternally, samplingMode: Number? = definedExternally): RawTexture
        /**
         * Creates an alpha texture from some data.
         * @param data Define the texture data
         * @param width Define the width of the texture
         * @param height Define the height of the texture
         * @param scene Define the scene the texture belongs to
         * @param generateMipMaps Define whether or not to create mip maps for the texture
         * @param invertY define if the data should be flipped on Y when uploaded to the GPU
         * @param samplingMode define the texture sampling mode (Texture.xxx_SAMPLINGMODE)
         * @returns the alpha texture
         */
        fun CreateAlphaTexture(data : ArrayBufferView, width: Number, height: Number, scene: Scene, generateMipMaps: Boolean? = definedExternally, invertY: Boolean? = definedExternally, samplingMode: Number? = definedExternally): RawTexture
        /**
         * Creates a RGB texture from some data.
         * @param data Define the texture data
         * @param width Define the width of the texture
         * @param height Define the height of the texture
         * @param scene Define the scene the texture belongs to
         * @param generateMipMaps Define whether or not to create mip maps for the texture
         * @param invertY define if the data should be flipped on Y when uploaded to the GPU
         * @param samplingMode define the texture sampling mode (Texture.xxx_SAMPLINGMODE)
         * @param type define the format of the data (int, float... Engine.TEXTURETYPE_xxx)
         * @returns the RGB alpha texture
         */
        fun CreateRGBTexture(data : ArrayBufferView, width: Number, height: Number, scene: Scene, generateMipMaps: Boolean? = definedExternally, invertY: Boolean? = definedExternally, samplingMode: Number? = definedExternally, type: Number? = definedExternally): RawTexture
        /**
         * Creates a RGBA texture from some data.
         * @param data Define the texture data
         * @param width Define the width of the texture
         * @param height Define the height of the texture
         * @param scene Define the scene the texture belongs to
         * @param generateMipMaps Define whether or not to create mip maps for the texture
         * @param invertY define if the data should be flipped on Y when uploaded to the GPU
         * @param samplingMode define the texture sampling mode (Texture.xxx_SAMPLINGMODE)
         * @param type define the format of the data (int, float... Engine.TEXTURETYPE_xxx)
         * @returns the RGBA texture
         */
        fun CreateRGBATexture(data : ArrayBufferView, width: Number, height: Number, scene: Scene, generateMipMaps: Boolean? = definedExternally, invertY: Boolean? = definedExternally, samplingMode: Number? = definedExternally, type: Number? = definedExternally): RawTexture
        /**
         * Creates a R texture from some data.
         * @param data Define the texture data
         * @param width Define the width of the texture
         * @param height Define the height of the texture
         * @param scene Define the scene the texture belongs to
         * @param generateMipMaps Define whether or not to create mip maps for the texture
         * @param invertY define if the data should be flipped on Y when uploaded to the GPU
         * @param samplingMode define the texture sampling mode (Texture.xxx_SAMPLINGMODE)
         * @param type define the format of the data (int, float... Engine.TEXTURETYPE_xxx)
         * @returns the R texture
         */
        fun CreateRTexture(data : ArrayBufferView, width: Number, height: Number, scene: Scene, generateMipMaps: Boolean? = definedExternally, invertY: Boolean? = definedExternally, samplingMode: Number? = definedExternally, type: Number? = definedExternally): RawTexture
    }
}
