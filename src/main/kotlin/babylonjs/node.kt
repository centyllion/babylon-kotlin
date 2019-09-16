@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals")
package babylonjs

/**
 * Node is the basic class for all scene objects (Mesh, Light, Camera.)
 */
open external class Node: IBehaviorAware<Node> {
    /**
     * Gets or sets the name of the node
     */
    var name: String
    /**
     * Gets or sets the id of the node
     */
    var id: String
    /**
     * Gets or sets the unique id of the node
     */
    var uniqueId: Number
    /**
     * Gets or sets a String used to store user defined state for the node
     */
    var state: String
    /**
     * Gets or sets an object used to store user defined information for the node
     */
    var metadata: Any
    /**
     * For internal use only. Please do not use.
     */
    var reservedDataStore: Any
    /**
     * List of inspectable custom properties (used by the Inspector)
     * @see [https://doc.babylonjs.com/how_to/debug_layer#extensibility]
     */
    var inspectableCustomProperties: Array<IInspectable>
    /**
     * Gets or sets a Boolean used to define if the node must be serialized
     */
    var doNotSerialize: Boolean
    /**
     * Gets a list of Animations associated with the node
     */
    var animations: Array<Animation>
    /**
     * Callback raised when the node is ready to be used
     */
    var onReady: ((node: Node) -> Unit)?
    /**
     * Gets a Boolean indicating if the node has been disposed
     * @returns true if the node was disposed
     */
    fun isDisposed(): Boolean
    /**
     * Gets or sets the parent of the node (without keeping the current position in the scene)
     * @see [https://doc.babylonjs.com/how_to/parenting]
     */
    var parent: Node?
    /**
     * Gets or sets the animation properties override
     */
    var animationPropertiesOverride: AnimationPropertiesOverride?
    /**
     * Gets a String idenfifying the name of the class
     * @returns "Node" String
     */
    fun getClassName(): String
    /**
     * An event triggered when the mesh is disposed
     */
    var onDisposeObservable: Observable<Node>
    /**
     * Sets a callback that will be raised when the node will be disposed
     */
    var onDispose: () -> Unit
    /**
     * Creates a new Node
     * @param name the name and id to be given to this node
     * @param scene the scene this node will be added to
     * @param addToRootNodes the node will be added to scene.rootNodes
     */
    constructor(name: String, scene: Scene?, addToRootNodes: Boolean?)
    /**
     * Gets the scene of the node
     * @returns a scene
     */
    fun getScene(): Scene
    /**
     * Gets the engine of the node
     * @returns a Engine
     */
    fun getEngine(): Engine
    /**
     * Attach a behavior to the node
     * @see [http://doc.babylonjs.com/features/behaviour]
     * @param behavior defines the behavior to attach
     * @returns the current Node
     */
    //override fun addBehavior(behavior: Behavior<Node>/*, attachImmediately: Boolean? = definedExternally*/): Node
    /**
     * Attach a behavior to the node
     * @see [http://doc.babylonjs.com/features/behaviour]
     * @param behavior defines the behavior to attach
     * @param attachImmediately defines that the behavior must be attached even if the scene is still loading
     * @returns the current Node
     */
    override fun addBehavior(behavior: Behavior<Node>, attachImmediately: Boolean?): Node
    /**
     * Remove an attached behavior
     * @see [http://doc.babylonjs.com/features/behaviour]
     * @param behavior defines the behavior to attach
     * @returns the current Node
     */
    override fun removeBehavior(behavior: Behavior<Node>): Node
    /**
     * Gets the list of attached behaviors
     * @see [http://doc.babylonjs.com/features/behaviour]
     */
    val behaviors: Array<Behavior<Node>>
    /**
     * Gets an attached behavior by name
     * @param name defines the name of the behavior to look for
     * @see [http://doc.babylonjs.com/features/behaviour]
     * @returns null if behavior was not found else the requested behavior
     */
    override fun getBehaviorByName(name: String): Behavior<Node>?
    /**
     * Returns the latest update of the World matrix
     * @returns a Matrix
     */
    fun getWorldMatrix(): Matrix
    /**
     * Returns directly the latest state of the mesh World matrix.
     * A Matrix is returned.
     */
    val worldMatrixFromCache: Matrix
    /**
     * Is this node ready to be used/rendered
     * @param completeCheck defines if a complete check (including materials and lights) has to be done (false by default)
     * @return true if the node is ready
     */
    fun isReady(completeCheck: Boolean?): Boolean
    /**
     * Is this node enabled?
     * If the node has a parent, all ancestors will be checked and false will be returned if Any are false (not enabled), otherwise will return true
     * @param checkAncestors indicates if this method should check the ancestors. The default is to check the ancestors. If set to false, the method will return the value of this node without checking ancestors
     * @return whether this node (and its parent) is enabled
     */
    fun isEnabled(checkAncestors: Boolean?): Boolean
    /**
     * Set the enabled state of this node
     * @param value defines the new enabled state
     */
    fun setEnabled(value: Boolean)
    /**
     * Is this node a descendant of the given node?
     * The function will iterate up the hierarchy until the ancestor was found or no more parents defined
     * @param ancestor defines the parent node to inspect
     * @returns a Boolean indicating if this node is a descendant of the given node
     */
    fun isDescendantOf(ancestor: Node): Boolean
    /**
     * Will return all nodes that have this node as ascendant
     * @param directDescendantsOnly defines if true only direct descendants of 'this' will be considered, if false direct and also indirect (children of children, an so on in a recursive manner) descendants of 'this' will be considered
     * @param predicate defines an optional predicate that will be called on every evaluated child, the predicate must return true for a given child to be part of the result, otherwise it will be ignored
     * @return all children nodes of all types
     */
    fun getDescendants(directDescendantsOnly: Boolean?, predicate: ((node: Node) -> Boolean)?): Array<Node>
    /**
     * Get all child-meshes of this node
     * @param directDescendantsOnly defines if true only direct descendants of 'this' will be considered, if false direct and also indirect (children of children, an so on in a recursive manner) descendants of 'this' will be considered (Default: false)
     * @param predicate defines an optional predicate that will be called on every evaluated child, the predicate must return true for a given child to be part of the result, otherwise it will be ignored
     * @returns an array of AbstractMesh
     */
    fun getChildMeshes(directDescendantsOnly: Boolean? = definedExternally, predicate: ((node: Node) -> Boolean)? = definedExternally): Array<AbstractMesh>
    /**
     * Get all direct children of this node
     * @param predicate defines an optional predicate that will be called on every evaluated child, the predicate must return true for a given child to be part of the result, otherwise it will be ignored
     * @param directDescendantsOnly defines if true only direct descendants of 'this' will be considered, if false direct and also indirect (children of children, an so on in a recursive manner) descendants of 'this' will be considered (Default: true)
     * @returns an array of Node
     */
    fun getChildren(predicate: ((node: Node) -> Boolean)?, directDescendantsOnly: Boolean?): Array<Node>
    /**
     * Get an animation by name
     * @param name defines the name of the animation to look for
     * @returns null if not found else the requested animation
     */
    fun getAnimationByName(name: String): Animation?
    /**
     * Creates an animation range for this node
     * @param name defines the name of the range
     * @param from defines the starting key
     * @param to defines the end key
     */
    fun createAnimationRange(name: String, from: Number, to: Number)
    /**
     * Delete a specific animation range
     * @param name defines the name of the range to delete
     * @param deleteFrames defines if animation frames from the range must be deleted as well
     */
    fun deleteAnimationRange(name: String, deleteFrames: Boolean?)
    /**
     * Get an animation range by name
     * @param name defines the name of the animation range to look for
     * @returns null if not found else the requested animation range
     */
    fun getAnimationRange(name: String): AnimationRange?
    /**
     * Gets the list of all animation ranges defined on this node
     * @returns an array
     */
    fun getAnimationRanges(): Array<AnimationRange>?
    /**
     * Will start the animation sequence
     * @param name defines the range frames for animation sequence
     * @param loop defines if the animation should loop (false by default)
     * @param speedRatio defines the speed factor in which to run the animation (1 by default)
     * @param onAnimationEnd defines a function to be executed when the animation ended (undefined by default)
     * @returns the object created for this animation. If range does not exist, it will return null
     */
    fun beginAnimation(name: String, loop: Boolean?, speedRatio: Number?, onAnimationEnd: (() -> Unit)?): Animatable?
    /**
     * Serialize animation ranges into a JSON compatible object
     * @returns serialization object
     */
    fun serializeAnimationRanges(): Any
    /**
     * Computes the world matrix of the node
     * @param force defines if the cache version should be invalidated forcing the world matrix to be created from scratch
     * @returns the world matrix
     */
    fun computeWorldMatrix(force: Boolean?): Matrix
    /**
     * Releases resources associated with this node.
     * @param doNotRecurse Set to true to not recurse into each children (recurse into each children by default)
     * @param disposeMaterialAndTextures Set to true to also dispose referenced materials and textures (false by default)
     */
    fun dispose(doNotRecurse: Boolean?, disposeMaterialAndTextures: Boolean?)
    /**
     * Return the minimum and maximum world vectors of the entire hierarchy under current node
     * @param includeDescendants Include bounding info from descendants as well (true by default)
     * @param predicate defines a callback function that can be customize to filter what meshes should be included in the list used to compute the bounding vectors
     * @returns the new bounding vectors
     */
    fun getHierarchyBoundingVectors(includeDescendants: Boolean?, predicate: ((abstractMesh: AbstractMesh) -> Boolean)?): Any
    /*{
        min: Vector3
        max: Vector3
    }*/

    companion object {
        /**
         * Add a new node constructor
         * @param type defines the type name of the node to construct
         * @param constructorFunc defines the constructor function
         */
        fun AddNodeConstructor(type: String, constructorFunc: NodeConstructor)
        /**
         * Returns a node constructor based on type name
         * @param type defines the type name
         * @param name defines the new node name
         * @param scene defines the hosting scene
         * @param options defines optional options to transmit to constructors
         * @returns the new constructor or null
         */
        fun Construct(type: String, name: String, scene: Scene, options: Any?): (() -> Node)?

        /**
         * Parse animation range data from a serialization object and store them into a given node
         * @param node defines where to store the animation ranges
         * @param parsedNode defines the serialization object to read data from
         * @param scene defines the hosting scene
         */
        fun ParseAnimationRanges(node: Node, parsedNode: Any, scene: Scene)

    }
}

/**
 * A TransformNode is an object that is not rendered but can be used as a center of transformation. This can decrease memory usage and increase rendering speed compared to using an empty mesh as a parent and is less complicated than using a pivot matrix.
 * @see [https://doc.babylonjs.com/how_to/transformnode]
 */
open external class TransformNode: Node {
    /**
     * Gets or sets the billboard mode. Default is 0.
     *
     * | Value | Type | Description |
     * | --- | --- | --- |
     * | 0 | BILLBOARDMODE_NONE |  |
     * | 1 | BILLBOARDMODE_X |  |
     * | 2 | BILLBOARDMODE_Y |  |
     * | 4 | BILLBOARDMODE_Z |  |
     * | 7 | BILLBOARDMODE_ALL |  |
     *
     */
    val billboardMode: Number 
    /**
     * Gets or sets a Boolean indicating that parent rotation should be preserved when using billboards.
     * This could be useful for glTF objects where parent rotation helps converting from right handed to left handed
     */
    val preserveParentRotationForBillboard: Boolean 
    /**
     * Multiplication factor on scale x/y/z when computing the world matrix. Eg. for a 1x1x1 cube setting this to 2 will make it a 2x2x2 cube
     */
    val scalingDeterminant: Number 
    /**
     * Gets or sets the distance of the object to max, often used by skybox
     */
    val infiniteDistance: Boolean 
    /**
     * Gets or sets a Boolean indicating that non uniform scaling (when at least one component is different from others) should be ignored.
     * By default the system will update normals to compensate
     */
    val ignoreNonUniformScaling: Boolean 
    /**
     * Gets or sets a Boolean indicating that even if rotationQuaternion is defined, you can keep updating rotation property and Babylon.js will just mix both
     */
    val reIntegrateRotationIntoRotationQuaternion: Boolean 
    /**
     * An event triggered after the world matrix is updated
     */
    var onAfterWorldMatrixUpdateObservable: Observable<TransformNode>
    
    constructor(name: String, scene: Scene?, isPure: Boolean?)

    /**
     * Gets or set the node position (default is (0.0, 0.0, 0.0))
     */
    val position: Vector3 
    /**
     * Gets or sets the rotation property : a Vector3 defining the rotation value in radians around each local axis X, Y, Z  (default is (0.0, 0.0, 0.0)).
     * If rotation quaternion is set, this Vector3 will be ignored and copy from the quaternion
     */
    val rotation: Vector3 
    /**
     * Gets or sets the scaling property : a Vector3 defining the node scaling along each local axis X, Y, Z (default is (0.0, 0.0, 0.0)).
     */
    val scaling: Vector3 
    /**
     * Gets or sets the rotation Quaternion property : this a Quaternion object defining the node rotation by using a unit quaternion (undefined by default, but can be null).
     * If set, only the rotationQuaternion is then used to compute the node rotation (ie. node.rotation will be ignored)
     */
    var rotationQuaternion: Quaternion?
    /**
     * The forward direction of that transform in world space.
     */
    val forward: Vector3
    /**
     * The up direction of that transform in world space.
     */
    val up: Vector3
    /**
     * The right direction of that transform in world space.
     */
    val right: Vector3
    /**
     * Copies the parameter passed Matrix into the mesh Pose matrix.
     * @param matrix the matrix to copy the pose from
     * @returns this TransformNode.
     */
    fun updatePoseMatrix(matrix: Matrix): TransformNode
    /**
     * Returns the mesh Pose matrix.
     * @returns the pose matrix
     */
    fun getPoseMatrix(): Matrix
    /**
     * Flag the transform node as dirty (Forcing it to update everything)
     * @param property if set to "rotation" the objects rotationQuaternion will be set to null
     * @returns this transform node
     */
    fun markAsDirty(property: String): TransformNode
    /**
     * Returns the current mesh absolute position.
     * Returns a Vector3.
     */
    val absolutePosition: Vector3
    /**
     * Sets a new matrix to apply before all other transformation
     * @param matrix defines the transform matrix
     * @returns the current TransformNode
     */
    fun setPreTransformMatrix(matrix: Matrix): TransformNode
    /**
     * Sets a new pivot matrix to the current node
     * @param matrix defines the new pivot matrix to use
     * @param postMultiplyPivotMatrix defines if the pivot matrix must be cancelled in the world matrix. When this parameter is set to true (default), the inverse of the pivot matrix is also applied at the end to cancel the transformation effect
     * @returns the current TransformNode
     */
    fun setPivotMatrix(matrix: Matrix, postMultiplyPivotMatrix: Boolean?): TransformNode
    /**
     * Returns the mesh pivot matrix.
     * Default : Identity.
     * @returns the matrix
     */
    fun getPivotMatrix(): Matrix
    /**
     * Prevents the World matrix to be computed Any longer.
     * @returns the TransformNode.
     */
    fun freezeWorldMatrix(): TransformNode
    /**
     * Allows back the World matrix computation.
     * @returns the TransformNode.
     */
    fun unfreezeWorldMatrix(): TransformNode
    /**
     * True if the World matrix has been frozen.
     */
    val isWorldMatrixFrozen: Boolean
    /**
     * Retuns the mesh absolute position in the World.
     * @returns a Vector3.
     */
    fun getAbsolutePosition(): Vector3
    /**
     * Sets the mesh absolute position in the World from a Vector3 or an Array(3).
     * @param absolutePosition the absolute position to set
     * @returns the TransformNode.
     */
    fun setAbsolutePosition(absolutePosition: Vector3): TransformNode
    /**
     * Sets the mesh position in its local space.
     * @param vector3 the position to set in localspace
     * @returns the TransformNode.
     */
    fun setPositionWithLocalVector(vector3: Vector3): TransformNode
    /**
     * Returns the mesh position in the local space from the current World matrix values.
     * @returns a new Vector3.
     */
    fun getPositionExpressedInLocalSpace(): Vector3
    /**
     * Translates the mesh along the passed Vector3 in its local space.
     * @param vector3 the distance to translate in localspace
     * @returns the TransformNode.
     */
    fun locallyTranslate(vector3: Vector3): TransformNode
    /**
     * Orients a mesh towards a target point. Mesh must be drawn facing user.
     * @param targetPoint the position (must be in same space as current mesh) to look at
     * @param yawCor optional yaw (y-axis) correction in radians
     * @param pitchCor optional pitch (x-axis) correction in radians
     * @param rollCor optional roll (z-axis) correction in radians
     * @param space the choosen space of the target
     * @returns the TransformNode.
     */
    fun lookAt(targetPoint: Vector3, yawCor: Number?, pitchCor: Number?, rollCor: Number?, space: Space?): TransformNode
    /**
     * Returns a new Vector3 that is the localAxis, expressed in the mesh local space, rotated like the mesh.
     * This Vector3 is expressed in the World space.
     * @param localAxis axis to rotate
     * @returns a new Vector3 that is the localAxis, expressed in the mesh local space, rotated like the mesh.
     */
    fun getDirection(localAxis: Vector3): Vector3
    /**
     * Sets the Vector3 "result" as the rotated Vector3 "localAxis" in the same rotation than the mesh.
     * localAxis is expressed in the mesh local space.
     * result is computed in the Wordl space from the mesh World matrix.
     * @param localAxis axis to rotate
     * @param result the resulting transformnode
     * @returns this TransformNode.
     */
    fun getDirectionToRef(localAxis: Vector3, result: Vector3): TransformNode
    /**
     * Sets this transform node rotation to the given local axis.
     * @param localAxis the axis in local space
     * @param yawCor optional yaw (y-axis) correction in radians
     * @param pitchCor optional pitch (x-axis) correction in radians
     * @param rollCor optional roll (z-axis) correction in radians
     * @returns this TransformNode
     */
    fun setDirection(localAxis: Vector3, yawCor: Number?, pitchCor: Number?, rollCor: Number?): TransformNode
    /**
     * Sets a new pivot point to the current node
     * @param point defines the new pivot point to use
     * @param space defines if the point is in world or local space (local by default)
     * @returns the current TransformNode
     */
    fun setPivotPoint(point: Vector3, space: Space?): TransformNode
    /**
     * Returns a new Vector3 set with the mesh pivot point coordinates in the local space.
     * @returns the pivot point
     */
    fun getPivotPoint(): Vector3
    /**
     * Sets the passed Vector3 "result" with the coordinates of the mesh pivot point in the local space.
     * @param result the vector3 to store the result
     * @returns this TransformNode.
     */
    fun getPivotPointToRef(result: Vector3): TransformNode
    /**
     * Returns a new Vector3 set with the mesh pivot point World coordinates.
     * @returns a new Vector3 set with the mesh pivot point World coordinates.
     */
    fun getAbsolutePivotPoint(): Vector3
    /**
     * Sets the Vector3 "result" coordinates with the mesh pivot point World coordinates.
     * @param result vector3 to store the result
     * @returns this TransformNode.
     */
    fun getAbsolutePivotPointToRef(result: Vector3): TransformNode
    /**
     * Defines the passed node as the parent of the current node.
     * The node will remain exactly where it is and its position / rotation will be updated accordingly
     * @see [https://doc.babylonjs.com/how_to/parenting]
     * @param node the node ot set as the parent
     * @returns this TransformNode.
     */
    fun setParent(node: Node?): TransformNode
    /**
     * True if the scaling property of this object is non uniform eg. (1,2,1)
     */
    val nonUniformScaling: Boolean
    /**
     * Attach the current TransformNode to another TransformNode associated with a bone
     * @param bone Bone affecting the TransformNode
     * @param affectedTransformNode TransformNode associated with the bone
     * @returns this object
     */
    fun attachToBone(bone: Bone, affectedTransformNode: TransformNode): TransformNode
    /**
     * Detach the transform node if its associated with a bone
     * @returns this object
     */
    fun detachFromBone(): TransformNode
    /**
     * Rotates the mesh around the axis vector for the passed angle (amount) expressed in radians, in the given space.
     * space (default LOCAL) can be either Space.LOCAL, either Space.WORLD.
     * Note that the property `rotationQuaternion` is then automatically updated and the property `rotation` is set to (0,0,0) and no longer used.
     * The passed axis is also normalized.
     * @param axis the axis to rotate around
     * @param amount the amount to rotate in radians
     * @param space Space to rotate in (Default: local)
     * @returns the TransformNode.
     */
    fun rotate(axis: Vector3, amount: Number, space: Space? = definedExternally): TransformNode
    /**
     * Rotates the mesh around the axis vector for the passed angle (amount) expressed in radians, in world space.
     * Note that the property `rotationQuaternion` is then automatically updated and the property `rotation` is set to (0,0,0) and no longer used.
     * The passed axis is also normalized. .
     * Method is based on [http://www.euclideanspace.com/maths/geometry/affine/aroundPoint/index.htm]
     * @param point the point to rotate around
     * @param axis the axis to rotate around
     * @param amount the amount to rotate in radians
     * @returns the TransformNode
     */
    fun rotateAround(point: Vector3, axis: Vector3, amount: Number): TransformNode
    /**
     * Translates the mesh along the axis vector for the passed distance in the given space.
     * space (default LOCAL) can be either Space.LOCAL, either Space.WORLD.
     * @param axis the axis to translate in
     * @param distance the distance to translate
     * @param space Space to rotate in (Default: local)
     * @returns the TransformNode.
     */
    fun translate(axis: Vector3, distance: Number, space: Space? = definedExternally): TransformNode
    /**
     * Adds a rotation step to the mesh current rotation.
     * x, y, z are Euler angles expressed in radians.
     * This methods updates the current mesh rotation, either mesh.rotation, either mesh.rotationQuaternion if it's set.
     * This means this rotation is made in the mesh local space only.
     * It's useful to set a custom rotation order different from the BJS standard one YXZ.
     * Example : this rotates the mesh first around its local X axis, then around its local Z axis, finally around its local Y axis.
     * ```javascript
     * mesh.addRotation(x1, 0, 0).addRotation(0, 0, z2).addRotation(0, 0, y3)
     * ```
     * Note that `addRotation()` accumulates the passed rotation values to the current ones and computes the .rotation or .rotationQuaternion updated values.
     * Under the hood, only quaternions are used. So it's a little faster is you use .rotationQuaternion because it doesn't need to translate them back to Euler angles.
     * @param x Rotation to add
     * @param y Rotation to add
     * @param z Rotation to add
     * @returns the TransformNode.
     */
    fun addRotation(x: Number, y: Number, z: Number): TransformNode
    /**
     * If you'd like to be called back after the mesh position, rotation or scaling has been updated.
     * @param func callback function to add
     *
     * @returns the TransformNode.
     */
    fun registerAfterWorldMatrixUpdate(func: (mesh: TransformNode) -> Unit): TransformNode
    /**
     * Removes a registered callback function.
     * @param func callback function to remove
     * @returns the TransformNode.
     */
    fun unregisterAfterWorldMatrixUpdate(func: (mesh: TransformNode) -> Unit): TransformNode
    /**
     * Gets the position of the current mesh in camera space
     * @param camera defines the camera to use
     * @returns a position
     */
    fun getPositionInCameraSpace(camera: Camera?): Vector3
    /**
     * Returns the distance from the mesh to the active camera
     * @param camera defines the camera to use
     * @returns the distance
     */
    fun getDistanceToCamera(camera: Camera?): Number
    /**
     * Clone the current transform node
     * @param name Name of the new clone
     * @param newParent New parent for the clone
     * @param doNotCloneChildren Do not clone children hierarchy
     * @returns the new transform node
     */
    fun clone(name: String, newParent: Node, doNotCloneChildren: Boolean? = definedExternally): TransformNode?
    /**
     * Serializes the objects information.
     * @param currentSerializationObject defines the object to serialize in
     * @returns the serialized object
     */
    fun serialize(currentSerializationObject: Any?): Any
    /**
     * Get all child-transformNodes of this node
     * @param directDescendantsOnly defines if true only direct descendants of 'this' will be considered, if false direct and also indirect (children of children, an so on in a recursive manner) descendants of 'this' will be considered
     * @param predicate defines an optional predicate that will be called on every evaluated child, the predicate must return true for a given child to be part of the result, otherwise it will be ignored
     * @returns an array of TransformNode
     */
    fun getChildTransformNodes(directDescendantsOnly: Boolean?, predicate: ((node: Node) -> Boolean)?): Array<TransformNode>

    companion object {
        /**
         * Object will not rotate to face the camera
         */
        val BILLBOARDMODE_NONE: Number
        /**
         * Object will rotate to face the camera but only on the x axis
         */
        val BILLBOARDMODE_X: Number
        /**
         * Object will rotate to face the camera but only on the y axis
         */
        val BILLBOARDMODE_Y: Number
        /**
         * Object will rotate to face the camera but only on the z axis
         */
        val BILLBOARDMODE_Z: Number
        /**
         * Object will rotate to face the camera
         */
        val BILLBOARDMODE_ALL: Number
        
        /**
         * Returns a new TransformNode object parsed from the source provided.
         * @param parsedTransformNode is the source.
         * @param scene the scne the object belongs to
         * @param rootUrl is a String, it's the root URL to prefix the `delayLoadingFile` property with
         * @returns a new TransformNode object parsed from the source provided.
         */
        fun Parse(parsedTransformNode: Any, scene: Scene, rootUrl: String): TransformNode
    }
}
