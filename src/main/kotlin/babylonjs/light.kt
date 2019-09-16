@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals")
package babylonjs

/**
 * Base class of all the lights in Babylon. It groups all the generic information about lights.
 * Lights are used, as you would expect, to affect how meshes are seen, in terms of both illumination and colour.
 * All meshes allow light to pass through them unless shadow generation is activated. The default Number of lights allowed is four but this can be increased.
 */
abstract external class Light : Node {
    /**
     * Diffuse gives the basic color to an object.
     */
    var diffuse: Color3
    /**
     * Specular produces a highlight color on an object.
     * Note: This is note affecting PBR materials.
     */
    var specular: Color3
    /**
     * Defines the falloff type for this light. This lets overrriding how punctual light are
     * falling off base on range or angle.
     * This can be set to Any values in Light.FALLOFF_x.
     *
     * Note: This is only useful for PBR Materials at the moment. This could be extended if required to
     * other types of materials.
     */
    var falloffType: Number
    /**
     * Strength of the light.
     * Note: By default it is define in the framework own unit.
     * Note: In PBR materials the intensityMode can be use to chose what unit the intensity is defined in.
     */
    var intensity: Number
    /**
     * Defines how far from the source the light is impacting in scene units.
     * Note: Unused in PBR material as the distance light falloff is defined following the inverse squared falloff.
     */
    /**
     * Defines how far from the source the light is impacting in scene units.
     * Note: Unused in PBR material as the distance light falloff is defined following the inverse squared falloff.
     */
    var range: Number
    /**
     * Gets the photometric scale used to interpret the intensity.
     * This is only relevant with PBR Materials where the light intensity can be defined in a physical way.
     */
    /**
     * Sets the photometric scale used to interpret the intensity.
     * This is only relevant with PBR Materials where the light intensity can be defined in a physical way.
     */
    var intensityMode: Number
    /**
     * Gets the light radius used by PBR Materials to simulate soft area lights.
     */
    /**
     * sets the light radius used by PBR Materials to simulate soft area lights.
     */
    var radius: Number
    /**
     * Defines the rendering priority of the lights. It can help in case of fallback or Number of lights
     * exceeding the Number allowed of the materials.
     */
    var renderPriority: Number
    /**
     * Gets wether or not the shadows are enabled for this light. This can help turning off/on shadow without detaching
     * the current shadow generator.
     */
    /**
     * Sets wether or not the shadows are enabled for this light. This can help turning off/on shadow without detaching
     * the current shadow generator.
     */
    var shadowEnabled: Boolean
    /**
     * Gets the only meshes impacted by this light.
     */
    /**
     * Sets the only meshes impacted by this light.
     */
    var includedOnlyMeshes: Array<AbstractMesh>
    /**
     * Gets the meshes not impacted by this light.
     */
    /**
     * Sets the meshes not impacted by this light.
     */
    var excludedMeshes: Array<AbstractMesh>
    /**
     * Gets the layer id use to find what meshes are not impacted by the light.
     * Inactive if 0
     */
    /**
     * Sets the layer id use to find what meshes are not impacted by the light.
     * Inactive if 0
     */
    var excludeWithLayerMask: Number
    /**
     * Gets the layer id use to find what meshes are impacted by the light.
     * Inactive if 0
     */
    /**
     * Sets the layer id use to find what meshes are impacted by the light.
     * Inactive if 0
     */
    var includeOnlyWithLayerMask: Number
    /**
     * Gets the lightmap mode of this light (should be one of the constants defined by Light.LIGHTMAP_x)
     */
    /**
     * Sets the lightmap mode of this light (should be one of the constants defined by Light.LIGHTMAP_x)
     */
    var lightmapMode: Number
    /**
     * Creates a Light object in the scene.
     * Documentation : https://doc.babylonjs.com/babylon101/lights
     * @param name The firendly name of the light
     * @param scene The scene the light belongs too
     */
    constructor(name: String, scene: Scene)
    /**
     * Sets the passed Effect "effect" with the Light information.
     * @param effect The effect to update
     * @param lightIndex The index of the light in the effect to update
     * @returns The light
     */
    abstract fun transferToEffect(effect: Effect, lightIndex: String): Light
    /**
     * Converts the light information to a readable String for debug purpose.
     * @param fullDetails Supports for multiple levels of logging within scene loading
     * @returns the human readable light info
     */
    fun toString(fullDetails: Boolean?): String
    /**
     * Returns the Light associated shadow generator if Any.
     * @return the associated shadow generator.
     */
    fun getShadowGenerator(): IShadowGenerator?
    /**
     * Returns a Vector3, the absolute light position in the World.
     * @returns the world space position of the light
     */
    fun getAbsolutePosition(): Vector3
    /**
     * Specifies if the light will affect the passed mesh.
     * @param mesh The mesh to test against the light
     * @return true the mesh is affected otherwise, false.
     */
    fun canAffectMesh(mesh: AbstractMesh): Boolean
    /**
     * Returns the light type ID (integer).
     * @returns The light Type id as a constant defines in Light.LIGHTTYPEID_x
     */
    fun getTypeID(): Number
    /**
     * Returns the intensity scaled by the Photometric Scale according to the light type and intensity mode.
     * @returns the scaled intensity in intensity mode unit
     */
    fun getScaledIntensity(): Number
    /**
     * Returns a new Light object, named "name", from the current one.
     * @param name The name of the cloned light
     * @returns the new created light
     */
    fun clone(name: String): Light?
    /**
     * Serializes the current light into a Serialization object.
     * @returns the serialized object.
     */
    fun serialize(): Any
    /**
     * Prepares the list of defines specific to the light type.
     * @param defines the list of defines
     * @param lightIndex defines the index of the light for the effect
     */
    abstract fun prepareLightSpecificDefines(defines: Any, lightIndex: Number)

    companion object {
        /**
         * Falloff Default: light is falling off following the material specification:
         * standard material is using standard falloff whereas pbr material can request special falloff per materials.
         */
        val FALLOFF_DEFAULT: Number
        /**
         * Falloff Physical: light is falling off following the inverse squared distance law.
         */
        val FALLOFF_PHYSICAL: Number
        /**
         * Falloff gltf: light is falling off as described in the gltf moving to PBR document
         * to enhance interoperability with other engines.
         */
        val FALLOFF_GLTF: Number
        /**
         * Falloff Standard: light is falling off like in the standard material
         * to enhance interoperability with other materials.
         */
        val FALLOFF_STANDARD: Number
        /**
         * If every light affecting the material is in this lightmapMode,
         * material.lightmapTexture adds or multiplies
         * (depends on material.useLightmapAsShadowmap)
         * after every other light calculations.
         */
        val LIGHTMAP_DEFAULT: Number
        /**
         * material.lightmapTexture as only diffuse lighting from this light
         * adds only specular lighting from this light
         * adds dynamic shadows
         */
        val LIGHTMAP_SPECULAR: Number
        /**
         * material.lightmapTexture as only lighting
         * no light calculation from this light
         * only adds dynamic shadows from this light
         */
        val LIGHTMAP_SHADOWSONLY: Number
        /**
         * Each light type uses the default quantity according to its type:
         *      point/spot lights use luminous intensity
         *      directional lights use illuminance
         */
        val INTENSITYMODE_AUTOMATIC: Number
        /**
         * lumen (lm)
         */
        val INTENSITYMODE_LUMINOUSPOWER: Number
        /**
         * candela (lm/sr)
         */
        val INTENSITYMODE_LUMINOUSINTENSITY: Number
        /**
         * lux (lm/m^2)
         */
        val INTENSITYMODE_ILLUMINANCE: Number
        /**
         * nit (cd/m^2)
         */
        val INTENSITYMODE_LUMINANCE: Number
        /**
         * Light type const id of the point light.
         */
        val LIGHTTYPEID_POINTLIGHT: Number
        /**
         * Light type const id of the directional light.
         */
        val LIGHTTYPEID_DIRECTIONALLIGHT: Number
        /**
         * Light type const id of the spot light.
         */
        val LIGHTTYPEID_SPOTLIGHT: Number
        /**
         * Light type const id of the hemispheric light.
         */
        val LIGHTTYPEID_HEMISPHERICLIGHT: Number

        /**
         * Sort function to order lights for rendering.
         * @param a First Light object to compare to second.
         * @param b Second Light object to compare first.
         * @return -1 to reduce's a's index relative to be, 0 for no change, 1 to increase a's index relative to b.
         */
        fun CompareLightsPriority(a: Light, b: Light): Number
        /**
         * Creates a new typed light from the passed type (integer) : point light = 0, directional light = 1, spot light = 2, hemispheric light = 3.
         * This new light is named "name" and added to the passed scene.
         * @param type Type according to the types available in Light.LIGHTTYPEID_x
         * @param name The friendly name of the light
         * @param scene The scene the new light will belong to
         * @returns the constructor function
         */
        fun GetConstructorFromName(type: Number, name: String, scene: Scene): (() -> Light)?
        /**
         * Parses the passed "parsedLight" and returns a new instanced Light from this parsing.
         * @param parsedLight The JSON representation of the light
         * @param scene The scene to create the parsed light in
         * @returns the created light after parsing
         */
        fun Parse(parsedLight: Any, scene: Scene): Light?
    }
}

/**
 * The HemisphericLight simulates the ambient environment light,
 * so the passed direction is the light reflection direction, not the incoming direction.
 */
external class HemisphericLight : Light {

    override fun transferToEffect(effect: Effect, lightIndex: String): Light

    override fun prepareLightSpecificDefines(defines: Any, lightIndex: Number)

    /**
     * The groundColor is the light in the opposite direction to the one specified during creation.
     * You can think of the diffuse and specular light as coming from the centre of the object in the given direction and the groundColor light in the opposite direction.
     */
    var groundColor: Color3
    /**
     * The light reflection direction, not the incoming direction.
     */
    var direction: Vector3
    /**
     * Creates a HemisphericLight object in the scene according to the passed direction (Vector3).
     * The HemisphericLight simulates the ambient environment light, so the passed direction is the light reflection direction, not the incoming direction.
     * The HemisphericLight can't cast shadows.
     * Documentation : https://doc.babylonjs.com/babylon101/lights
     * @param name The friendly name of the light
     * @param direction The direction of the light reflection
     * @param scene The scene the light belongs to
     */
    constructor(name: String, direction: Vector3, scene: Scene)
    /**
     * Sets the HemisphericLight direction towards the passed target (Vector3).
     * Returns the updated direction.
     * @param target The target the direction should point to
     * @return The computed direction
     */
    fun setDirectionToTarget(target: Vector3): Vector3
    /**
     * Computes the world matrix of the node
     * @param force defines if the cache version should be invalidated forcing the world matrix to be created from scratch
     * @param useWasUpdatedFlag defines a reserved property
     * @returns the world matrix
     */
    fun computeWorldMatrix(): Matrix
}

/**
 * Interface describing all the common properties and methods a shadow light needs to implement.
 * This helps both the shadow generator and materials to genrate the corresponding shadow maps
 * as well as binding the different shadow properties to the effects.
 */
external interface IShadowLight {
    /**
     * The light id in the scene (used in scene.findLighById for instance)
     */
    var id: String
    /**
     * The position the shdow will be casted from.
     */
    var position: Vector3
    /**
     * In 2d mode (needCube being false), the direction used to cast the shadow.
     */
    var direction: Vector3
    /**
     * The transformed position. Position of the light in world space taking parenting in account.
     */
    var transformedPosition: Vector3
    /**
     * The transformed direction. Direction of the light in world space taking parenting in account.
     */
    var transformedDirection: Vector3
    /**
     * The friendly name of the light in the scene.
     */
    var name: String
    /**
     * Defines the shadow projection clipping minimum z value.
     */
    var shadowMinZ: Number
    /**
     * Defines the shadow projection clipping maximum z value.
     */
    var shadowMaxZ: Number
    /**
     * Computes the transformed information (transformedPosition and transformedDirection in World space) of the current light
     * @returns true if the information has been computed, false if it does not need to (no parenting)
     */
    fun computeTransformedInformation(): Boolean
    /**
     * Gets the scene the light belongs to.
     * @returns The scene
     */
    fun getScene(): Scene
    /**
     * Callback defining a custom Projection Matrix Builder.
     * This can be used to override the default projection matrix computation.
     */
    var customProjectionMatrixBuilder: (viewMatrix: Matrix, renderList: Array<AbstractMesh>, result: Matrix) -> Unit
    /**
     * Sets the shadow projection matrix in parameter to the generated projection matrix.
     * @param matrix The materix to updated with the projection information
     * @param viewMatrix The transform matrix of the light
     * @param renderList The list of mesh to render in the map
     * @returns The current light
     */
    fun setShadowProjectionMatrix(matrix: Matrix, viewMatrix: Matrix, renderList: Array<AbstractMesh>): IShadowLight
    /**
     * Gets the current depth scale used in ESM.
     * @returns The scale
     */
    fun getDepthScale(): Number
    /**
     * Returns whether or not the shadow generation require a cube texture or a 2d texture.
     * @returns true if a cube texture needs to be use
     */
    fun needCube(): Boolean
    /**
     * Detects if the projection matrix requires to be recomputed this frame.
     * @returns true if it requires to be recomputed otherwise, false.
     */
    fun needProjectionMatrixCompute(): Boolean
    /**
     * Forces the shadow generator to recompute the projection matrix even if position and direction did not changed.
     */
    fun forceProjectionMatrixCompute()
    /**
     * Get the direction to use to render the shadow map. In case of cube texture, the face index can be passed.
     * @param faceIndex The index of the face we are computed the direction to generate shadow
     * @returns The set direction in 2d mode otherwise the direction to the cubemap face if needCube() is true
     */
    fun getShadowDirection(faceIndex: Number?): Vector3
    /**
     * Gets the minZ used for shadow according to both the scene and the light.
     * @param activeCamera The camera we are returning the min for
     * @returns the depth min z
     */
    fun getDepthMinZ(activeCamera: Camera): Number
    /**
     * Gets the maxZ used for shadow according to both the scene and the light.
     * @param activeCamera The camera we are returning the max for
     * @returns the depth max z
     */
    fun getDepthMaxZ(activeCamera: Camera): Number
}
/**
 * Base implementation IShadowLight
 * It groups all the common behaviour in order to reduce dupplication and better follow the DRY pattern.
 */
abstract external class ShadowLight: Light, IShadowLight {
    /**
     * Sets the ShadowLight direction toward the passed target.
     * @param target The point to target in local space
     * @returns the updated ShadowLight direction
     */
    fun setDirectionToTarget(target: Vector3): Vector3
    /**
     * Returns the light rotation in euler definition.
     * @returns the x y z rotation in local space.
     */
    fun getRotation(): Vector3
}

/**
 * A point light is a light defined by an unique point in world space.
 * The light is emitted in every direction from this point.
 * A good example of a point light is a standard light bulb.
 * Documentation: https://doc.babylonjs.com/babylon101/lights
 */
external class PointLight : ShadowLight {
    /**
     * Getter: In case of direction provided, the shadow will not use a cube texture but simulate a spot shadow as a fallback
     * This specifies what angle the shadow will use to be created.
     *
     * It default to 90 degrees to work nicely with the cube texture generation for point lights shadow maps.
     */
    /**
     * Setter: In case of direction provided, the shadow will not use a cube texture but simulate a spot shadow as a fallback
     * This specifies what angle the shadow will use to be created.
     *
     * It default to 90 degrees to work nicely with the cube texture generation for point lights shadow maps.
     */
    var shadowAngle: Number
    /**
     * Creates a PointLight object from the passed name and position (Vector3) and adds it in the scene.
     * A PointLight emits the light in every direction.
     * It can cast shadows.
     * If the scene camera is already defined and you want to set your PointLight at the camera position, just set it :
     * ```javascript
     * var pointLight = new PointLight("pl", camera.position, scene)
     * ```
     * Documentation : https://doc.babylonjs.com/babylon101/lights
     * @param name The light friendly name
     * @param position The position of the point light in the scene
     * @param scene The scene the lights belongs to
     */
    constructor(name: String, position: Vector3, scene: Scene)

    override var position: Vector3
    override var direction: Vector3
    override var transformedPosition: Vector3
    override var transformedDirection: Vector3
    override var shadowMinZ: Number
    override var shadowMaxZ: Number

    override var customProjectionMatrixBuilder: (viewMatrix: Matrix, renderList: Array<AbstractMesh>, result: Matrix) -> Unit

    override fun transferToEffect(effect: Effect, lightIndex: String): Light
    override fun prepareLightSpecificDefines(defines: Any, lightIndex: Number)
    override fun computeTransformedInformation(): Boolean
    override fun setShadowProjectionMatrix(matrix: Matrix, viewMatrix: Matrix, renderList: Array<AbstractMesh>): IShadowLight
    override fun getDepthScale(): Number
    override fun needCube(): Boolean
    override fun needProjectionMatrixCompute(): Boolean
    override fun forceProjectionMatrixCompute()
    override fun getShadowDirection(faceIndex: Number?): Vector3
    override fun getDepthMinZ(activeCamera: Camera): Number
    override fun getDepthMaxZ(activeCamera: Camera): Number
}
