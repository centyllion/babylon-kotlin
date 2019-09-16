@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals", "FunctionName")
package babylonjs

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint8Array

/**
 * Class used to store all common mesh properties
 */
abstract external class AbstractMesh: TransformNode, IDisposable, ICullable, IGetSetVerticesData {
    /**
     * The culling strategy to use to check whether the mesh must be rendered or not.
     * This value can be changed at Any time and will be used on the next render mesh selection.
     * The possible values are :
     * - AbstractMesh.CULLINGSTRATEGY_STANDARD
     * - AbstractMesh.CULLINGSTRATEGY_BOUNDINGSPHERE_ONLY
     * - AbstractMesh.CULLINGSTRATEGY_OPTIMISTIC_INCLUSION
     * - AbstractMesh.CULLINGSTRATEGY_OPTIMISTIC_INCLUSION_THEN_BSPHERE_ONLY
     * Please read each static variable documentation to get details about the culling process.
     * */
    var cullingStrategy: Number
    /**
     * Gets the Number of facets in the mesh
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata#what-is-a-mesh-facet]
     */
    val facetNb: Number
    /**
     * Gets or set the Number (integer) of subdivisions per axis in the partioning space
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata#tweaking-the-partitioning]
     */
    var partitioningSubdivisions: Number
    /**
     * The ratio (float) to apply to the bouding box size to set to the partioning space.
     * Ex : 1.01 (default) the partioning space is 1% bigger than the bounding box
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata#tweaking-the-partitioning]
     */
    var partitioningBBoxRatio: Number
    /**
     * Gets or sets a Boolean indicating that the facets must be depth sorted on next call to `updateFacetData()`.
     * Works only for updatable meshes.
     * Doesn't work with multi-materials
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata#facet-depth-sort]
     */
    var mustDepthSortFacets: Boolean
    /**
     * The location (Vector3) where the facet depth sort must be computed from.
     * By default, the active camera position.
     * Used only when facet depth sort is enabled
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata#facet-depth-sort]
     */
    var facetDepthSortFrom: Vector3
    /**
     * gets a Boolean indicating if facetData is enabled
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata#what-is-a-mesh-facet]
     */
    val isFacetDataEnabled: Boolean
    /**
     * An event triggered when this mesh collides with another one
     */
    var onCollideObservable: Observable<AbstractMesh>
    /** Set a function to call when this mesh collides with another one */
    var onCollide: () -> Unit
    /**
     * An event triggered when the collision's position changes
     */
    var onCollisionPositionChangeObservable: Observable<Vector3>
    /** Set a function to call when the collision's position changes */
    var onCollisionPositionChange: () -> Unit
    /**
     * An event triggered when material is changed
     */
    var onMaterialChangedObservable: Observable<AbstractMesh>
    /**
     * Gets or sets the orientation for POV movement & rotation
     */
    var definedFacingForward: Boolean
    /**
     * Gets or sets mesh visibility between 0 and 1 (default is 1)
     */
    /**
     * Gets or sets mesh visibility between 0 and 1 (default is 1)
     */
    var visibility: Number
    /** Gets or sets the alpha index used to sort transparent meshes
     * @see [http://doc.babylonjs.com/resources/transparency_and_how_meshes_are_rendered#alpha-index]
     */
    var alphaIndex: Number
    /**
     * Gets or sets a Boolean indicating if the mesh is visible (renderable). Default is true
     */
    var isVisible: Boolean
    /**
     * Gets or sets a Boolean indicating if the mesh can be picked (by scene.pick for instance or through actions). Default is true
     */
    var isPickable: Boolean
    /** Gets or sets a Boolean indicating that bounding boxes of subMeshes must be rendered as well (false by default) */
    var showSubMeshesBoundingBox: Boolean
    /** Gets or sets a Boolean indicating if the mesh must be considered as a ray blocker for lens flares (false by default)
     * @see [http://doc.babylonjs.com/how_to/how_to_use_lens_flares]
     */
    var isBlocker: Boolean
    /**
     * Gets or sets a Boolean indicating that pointer move events must be supported on this mesh (false by default)
     */
    var enablePointerMoveEvents: Boolean
    /**
     * Specifies the rendering group id for this mesh (0 by default)
     * @see [http://doc.babylonjs.com/resources/transparency_and_how_meshes_are_rendered#rendering-groups]
     */
    var renderingGroupId: Number
    /** Gets or sets current material */
    var material: Material?
    /**
     * Gets or sets a Boolean indicating that this mesh can receive realtime shadows
     * @see [http://doc.babylonjs.com/babylon101/shadows]
     */
    var receiveShadows: Boolean
    /** Defines color to use when rendering outline */
    var outlineColor: Color3
    /** Define width to use when rendering outline */
    var outlineWidth: Number
    /** Defines color to use when rendering overlay */
    var overlayColor: Color3
    /** Defines alpha to use when rendering overlay */
    var overlayAlpha: Number
    /** Gets or sets a Boolean indicating that this mesh contains vertex color data with alpha values */
    var hasVertexAlpha: Boolean
    /** Gets or sets a Boolean indicating that this mesh needs to use vertex color data to render (if this kind of vertex data is available in the geometry) */
    var useVertexColors: Boolean
    /**
     * Gets or sets a Boolean indicating that bone animations must be computed by the CPU (false by default)
     */
    var computeBonesUsingShaders: Boolean
    /** Gets or sets the Number of allowed bone influences per vertex (4 by default) */
    var numBoneInfluencers: Number
    /** Gets or sets a Boolean indicating that this mesh will allow fog to be rendered on it (true by default) */
    var applyFog: Boolean
    /** Gets or sets a Boolean indicating that internal octree (if available) can be used to boost submeshes selection (true by default) */
    var useOctreeForRenderingSelection: Boolean
    /** Gets or sets a Boolean indicating that internal octree (if available) can be used to boost submeshes picking (true by default) */
    var useOctreeForPicking: Boolean
    /** Gets or sets a Boolean indicating that internal octree (if available) can be used to boost submeshes collision (true by default) */
    var useOctreeForCollisions: Boolean
    /**
     * Gets or sets the current layer mask (default is 0x0FFFFFFF)
     * @see [http://doc.babylonjs.com/how_to/layermasks_and_multi-cam_textures]
     */
    var layerMask: Number
    /**
     * True if the mesh must be rendered in Any case (this will shortcut the frustum clipping phase)
     */
    var alwaysSelectAsActiveMesh: Boolean
    /**
     * Gets or sets a Boolean indicating that the bounding info does not need to be kept in sync (for performance reason)
     */
    var doNotSyncBoundingInfo: Boolean
    /**
     * Gets or sets the current action manager
     * @see [http://doc.babylonjs.com/how_to/how_to_use_actions]
     */
    var actionManager: AbstractActionManager?
    /**
     * Gets or sets the ellipsoid used to impersonate this mesh when using collision engine (default is (0.5, 1, 0.5))
     * @see [http://doc.babylonjs.com/babylon101/cameras,_mesh_collisions_and_gravity]
     */
    var ellipsoid: Vector3
    /**
     * Gets or sets the ellipsoid offset used to impersonate this mesh when using collision engine (default is (0, 0, 0))
     * @see [http://doc.babylonjs.com/babylon101/cameras,_mesh_collisions_and_gravity]
     */
    var ellipsoidOffset: Vector3
    /**
     * Gets or sets a collision mask used to mask collisions (default is -1).
     * A collision between A and B will happen if A.collisionGroup & b.collisionMask !== 0
     */
    var collisionMask: Number
    /**
     * Gets or sets the current collision group mask (-1 by default).
     * A collision between A and B will happen if A.collisionGroup & b.collisionMask !== 0
     */
    var collisionGroup: Number
    /**
     * Defines edge width used when edgesRenderer is enabled
     * @see [https://www.babylonjs-playground.com/#10OJSG#13]
     */
    var edgesWidth: Number
    /**
     * Defines edge color used when edgesRenderer is enabled
     * @see [https://www.babylonjs-playground.com/#10OJSG#13]
     */
    var edgesColor: Color4
    /**
     * Gets or sets the list of subMeshes
     * @see [http://doc.babylonjs.com/how_to/multi_materials]
     */
    var subMeshes: Array<SubMesh>
    /** Gets the list of lights affecting that mesh */
    val lightSources: Array<Light>
    /**
     * Gets or sets a skeleton to apply skining transformations
     * @see [http://doc.babylonjs.com/how_to/how_to_use_bones_and_skeletons]
     */
    var skeleton: Skeleton?
    /**
     * An event triggered when the mesh is rebuilt.
     */
    var onRebuildObservable: Observable<AbstractMesh>
    /**
     * Creates a new AbstractMesh
     * @param name defines the name of the mesh
     * @param scene defines the hosting scene
     */
    constructor(name: String, scene: Scene?)
    /**
     * Gets a String representation of the current mesh
     * @param fullDetails defines a Boolean indicating if full details must be included
     * @returns a String representation of the current mesh
     */
    fun toString(fullDetails: Boolean?): String
    /**
     * Returns true if the mesh is blocked. Implemented by child classes
     */
    val isBlocked: Boolean
    /**
     * Returns the mesh itself by default. Implemented by child classes
     * @param camera defines the camera to use to pick the right LOD level
     * @returns the currentAbstractMesh
     */
    fun getLOD(camera: Camera): AbstractMesh?
    /**
     * Returns 0 by default. Implemented by child classes
     * @returns an integer
     */
    fun getTotalVertices(): Number
    /**
     * Returns a positive integer : the total Number of indices in this mesh geometry.
     * @returns the numner of indices or zero if the mesh has no geometry.
     */
    fun getTotalIndices(): Number
    /**
     * Returns null by default. Implemented by child classes
     * @returns null
     */
    fun getIndices(): IndicesArray?
    /**
     * Returns the array of the requested vertex data kind. Implemented by child classes
     * @param kind defines the vertex data kind to use
     * @returns null
     */
    fun getVerticesData(kind: String): FloatArray?
    /**
     * Sets the vertex data of the mesh geometry for the requested `kind`.
     * If the mesh has no geometry, a new Geometry object is set to the mesh and then passed this vertex data.
     * Note that a new underlying VertexBuffer object is created each call.
     * If the `kind` is the `PositionKind`, the mesh BoundingInfo is renewed, so the bounding box and sphere, and the mesh World Matrix is recomputed.
     * @param kind defines vertex data kind:
     * * VertexBuffer.PositionKind
     * * VertexBuffer.UVKind
     * * VertexBuffer.UV2Kind
     * * VertexBuffer.UV3Kind
     * * VertexBuffer.UV4Kind
     * * VertexBuffer.UV5Kind
     * * VertexBuffer.UV6Kind
     * * VertexBuffer.ColorKind
     * * VertexBuffer.MatricesIndicesKind
     * * VertexBuffer.MatricesIndicesExtraKind
     * * VertexBuffer.MatricesWeightsKind
     * * VertexBuffer.MatricesWeightsExtraKind
     * @param data defines the data source
     * @param updatable defines if the data must be flagged as updatable (or static)
     * @param stride defines the vertex stride (size of an entire vertex). Can be null and in this case will be deduced from vertex data kind
     * @returns the current mesh
     */
    fun setVerticesData(kind: String, data: FloatArray, updatable: Boolean?, stride: Number?): AbstractMesh
    /**
     * Sets the mesh indices,
     * If the mesh has no geometry, a new Geometry object is created and set to the mesh.
     * @param indices Expects an array populated with integers or a typed array (Int32Array, Uint32Array, Uint16Array)
     * @param totalVertices Defines the total Number of vertices
     * @returns the current mesh
     */
    fun setIndices(indices: IndicesArray, totalVertices: Number?): AbstractMesh
    /**
     * Returns the mesh BoundingInfo object or creates a new one and returns if it was undefined
     * @returns a BoundingInfo
     */
    fun getBoundingInfo(): BoundingInfo
    /**
     * Uniformly scales the mesh to fit inside of a unit cube (1 X 1 X 1 units)
     * @param includeDescendants Use the hierarchy's bounding box instead of the mesh's bounding box. Default is false
     * @param ignoreRotation ignore rotation when computing the scale (ie. object will be axis aligned). Default is false
     * @returns the current mesh
     */
    fun normalizeToUnitCube(includeDescendants: Boolean?, ignoreRotation: Boolean?): AbstractMesh
    /**
     * Overwrite the current bounding info
     * @param boundingInfo defines the new bounding info
     * @returns the current mesh
     */
    fun setBoundingInfo(boundingInfo: BoundingInfo): AbstractMesh
    /** Gets a Boolean indicating if this mesh has skinning data and an attached skeleton */
    val useBones: Boolean
    /**
     * Gets a Boolean indicating if this mesh is an instance or a regular mesh
     */
    val isAnInstance: Boolean
    /**
     * Perform relative position change from the point of view of behind the front of the mesh.
     * This is performed taking into account the meshes current rotation, so you do not have to care.
     * Supports definition of mesh facing forward or backward
     * @param amountRight defines the distance on the right axis
     * @param amountUp defines the distance on the up axis
     * @param amountForward defines the distance on the forward axis
     * @returns the current mesh
     */
    fun movePOV(amountRight: Number, amountUp: Number, amountForward: Number): AbstractMesh
    /**
     * Calculate relative position change from the point of view of behind the front of the mesh.
     * This is performed taking into account the meshes current rotation, so you do not have to care.
     * Supports definition of mesh facing forward or backward
     * @param amountRight defines the distance on the right axis
     * @param amountUp defines the distance on the up axis
     * @param amountForward defines the distance on the forward axis
     * @returns the new displacement vector
     */
    fun calcMovePOV(amountRight: Number, amountUp: Number, amountForward: Number): Vector3
    /**
     * Perform relative rotation change from the point of view of behind the front of the mesh.
     * Supports definition of mesh facing forward or backward
     * @param flipBack defines the flip
     * @param twirlClockwise defines the twirl
     * @param tiltRight defines the tilt
     * @returns the current mesh
     */
    fun rotatePOV(flipBack: Number, twirlClockwise: Number, tiltRight: Number): AbstractMesh
    /**
     * Calculate relative rotation change from the point of view of behind the front of the mesh.
     * Supports definition of mesh facing forward or backward.
     * @param flipBack defines the flip
     * @param twirlClockwise defines the twirl
     * @param tiltRight defines the tilt
     * @returns the new rotation vector
     */
    fun calcRotatePOV(flipBack: Number, twirlClockwise: Number, tiltRight: Number): Vector3
    /**
     * This method recomputes and sets a new BoundingInfo to the mesh unless it is locked.
     * This means the mesh underlying bounding box and sphere are recomputed.
     * @param applySkeleton defines whether to apply the skeleton before computing the bounding info
     * @returns the current mesh
     */
    fun refreshBoundingInfo(applySkeleton: Boolean?): AbstractMesh
    /**
     * True if the mesh intersects another mesh or a SolidParticle object
     * @param mesh defines a target mesh or SolidParticle to test
     * @param precise Unless the parameter `precise` is set to `true` the intersection is computed according to Axis Aligned Bounding Boxes (AABB), else according to OBB (Oriented BBoxes)
     * @param includeDescendants Can be set to true to test if the mesh defined in parameters intersects with the current mesh or Any child meshes
     * @returns true if there is an intersection
     */
    fun intersectsMesh(mesh: AbstractMesh, precise: Boolean?, includeDescendants: Boolean?): Boolean
    /**
     * True if the mesh intersects another mesh or a SolidParticle object
     * @param mesh defines a target mesh or SolidParticle to test
     * @param precise Unless the parameter `precise` is set to `true` the intersection is computed according to Axis Aligned Bounding Boxes (AABB), else according to OBB (Oriented BBoxes)
     * @param includeDescendants Can be set to true to test if the mesh defined in parameters intersects with the current mesh or Any child meshes
     * @returns true if there is an intersection
     */
    fun intersectsMesh(mesh: SolidParticle, precise: Boolean?, includeDescendants: Boolean?): Boolean
    /**
     * Returns true if the passed point (Vector3) is inside the mesh bounding box
     * @param point defines the point to test
     * @returns true if there is an intersection
     */
    fun intersectsPoint(point: Vector3): Boolean
    /**
     * Gets or sets a Boolean indicating that this mesh can be used in the collision engine
     * @see [http://doc.babylonjs.com/babylon101/cameras,_mesh_collisions_and_gravity]
     */
    var checkCollisions: Boolean
    /**
     * Gets Collider object used to compute collisions (not physics)
     * @see [http://doc.babylonjs.com/babylon101/cameras,_mesh_collisions_and_gravity]
     */
    val collider: Collider?
    /**
     * Move the mesh using collision engine
     * @see [http://doc.babylonjs.com/babylon101/cameras,_mesh_collisions_and_gravity]
     * @param displacement defines the requested displacement vector
     * @returns the current mesh
     */
    fun moveWithCollisions(displacement: Vector3): AbstractMesh
    /**
     * Checks if the passed Ray intersects with the mesh
     * @param ray defines the ray to use
     * @param fastCheck defines if fast mode (but less precise) must be used (false by default)
     * @param trianglePredicate defines an optional predicate used to select faces when a mesh intersection is detected
     * @returns the picking info
     * @see [http://doc.babylonjs.com/babylon101/intersect_collisions_-_mesh]
     */
    fun intersects(ray: Ray, fastCheck: Boolean?, trianglePredicate: TrianglePickingPredicate?): PickingInfo
    /**
     * Disposes all the submeshes of the current meshnp
     * @returns the current mesh
     */
    fun releaseSubMeshes(): AbstractMesh
    /**
     * Adds the passed mesh as a child to the current mesh
     * @param mesh defines the child mesh
     * @returns the current mesh
     */
    fun addChild(mesh: AbstractMesh): AbstractMesh
    /**
     * Removes the passed mesh from the current mesh children list
     * @param mesh defines the child mesh
     * @returns the current mesh
     */
    fun removeChild(mesh: AbstractMesh): AbstractMesh
    /**
     * Updates the mesh facetData arrays and the internal partitioning when the mesh is morphed or updated.
     * This method can be called within the render loop.
     * You don't need to call this method by yourself in the render loop when you update/morph a mesh with the methods CreateXXX() as they automatically manage this computation
     * @returns the current mesh
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun updateFacetData(): AbstractMesh
    /**
     * Returns the facetLocalNormals array.
     * The normals are expressed in the mesh local spac
     * @returns an array of Vector3
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun getFacetLocalNormals(): Array<Vector3>
    /**
     * Returns the facetLocalPositions array.
     * The facet positions are expressed in the mesh local space
     * @returns an array of Vector3
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun getFacetLocalPositions(): Array<Vector3>
    /**
     * Returns the facetLocalPartioning array
     * @returns an array of array of numbers
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun getFacetLocalPartitioning(): Array<Array<Number>>
    /**
     * Returns the i-th facet position in the world system.
     * This method allocates a new Vector3 per call
     * @param i defines the facet index
     * @returns a new Vector3
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun getFacetPosition(i: Number): Vector3
    /**
     * Sets the reference Vector3 with the i-th facet position in the world system
     * @param i defines the facet index
     * @param ref defines the target vector
     * @returns the current mesh
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun getFacetPositionToRef(i: Number, ref: Vector3): AbstractMesh
    /**
     * Returns the i-th facet normal in the world system.
     * This method allocates a new Vector3 per call
     * @param i defines the facet index
     * @returns a new Vector3
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun getFacetNormal(i: Number): Vector3
    /**
     * Sets the reference Vector3 with the i-th facet normal in the world system
     * @param i defines the facet index
     * @param ref defines the target vector
     * @returns the current mesh
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun getFacetNormalToRef(i: Number, ref: Vector3): AbstractMesh
    /**
     * Returns the facets (in an array) in the same partitioning block than the one the passed coordinates are located (expressed in the mesh local system)
     * @param x defines x coordinate
     * @param y defines y coordinate
     * @param z defines z coordinate
     * @returns the array of facet indexes
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun getFacetsAtLocalCoordinates(x: Number, y: Number, z: Number): Array<Number>?
    /**
     * Returns the closest mesh facet index at (x,y,z) World coordinates, null if not found
     * @param projected sets as the (x,y,z) world projection on the facet
     * @param checkFace if true (default false), only the facet "facing" to (x,y,z) or only the ones "turning their backs", according to the parameter "facing" are returned
     * @param facing if facing and checkFace are true, only the facet "facing" to (x, y, z) are returned : positive dot (x, y, z) * facet position. If facing si false and checkFace is true, only the facet "turning their backs" to (x, y, z) are returned : negative dot (x, y, z) * facet position
     * @param x defines x coordinate
     * @param y defines y coordinate
     * @param z defines z coordinate
     * @returns the face index if found (or null instead)
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun getClosestFacetAtCoordinates(x: Number, y: Number, z: Number, projected: Vector3?, checkFace: Boolean?, facing: Boolean?): Number?
    /**
     * Returns the closest mesh facet index at (x,y,z) local coordinates, null if not found
     * @param projected sets as the (x,y,z) local projection on the facet
     * @param checkFace if true (default false), only the facet "facing" to (x,y,z) or only the ones "turning their backs", according to the parameter "facing" are returned
     * @param facing if facing and checkFace are true, only the facet "facing" to (x, y, z) are returned : positive dot (x, y, z) * facet position. If facing si false and checkFace is true, only the facet "turning their backs" to (x, y, z) are returned : negative dot (x, y, z) * facet position
     * @param x defines x coordinate
     * @param y defines y coordinate
     * @param z defines z coordinate
     * @returns the face index if found (or null instead)
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun getClosestFacetAtLocalCoordinates(x: Number, y: Number, z: Number, projected: Vector3?, checkFace: Boolean?, facing: Boolean?): Number?
    /**
     * Returns the object "parameter" set with all the expected parameters for facetData computation by ComputeNormals()
     * @returns the parameters
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun getFacetDataParameters(): Any
    /**
     * Disables the feature FacetData and frees the related memory
     * @returns the current mesh
     * @see [http://doc.babylonjs.com/how_to/how_to_use_facetdata]
     */
    fun disableFacetData(): AbstractMesh
    /**
     * Updates the AbstractMesh indices array
     * @param indices defines the data source
     * @param offset defines the offset in the index buffer where to store the new data (can be null)
     * @param gpuMemoryOnly defines a Boolean indicating that only the GPU memory must be updated leaving the CPU version of the indices unchanged (false by default)
     * @returns the current mesh
     */
    fun updateIndices(indices: IndicesArray, offset: Number?, gpuMemoryOnly: Boolean?): AbstractMesh
    /**
     * Creates new normals data for the mesh
     * @param updatable defines if the normal vertex buffer must be flagged as updatable
     * @returns the current mesh
     */
    fun createNormals(updatable: Boolean): AbstractMesh
    /**
     * Align the mesh with a normal
     * @param normal defines the normal to use
     * @param upDirection can be used to redefined the up vector to use (will use the (0, 1, 0) by default)
     * @returns the current mesh
     */
    fun alignWithNormal(normal: Vector3, upDirection: Vector3?): AbstractMesh
    /**
     * Disables the mesh edge rendering mode
     * @returns the currentAbstractMesh
     */
    fun disableEdgesRendering(): AbstractMesh
    /**
     * Enables the edge rendering mode on the mesh.
     * This mode makes the mesh edges visible
     * @param epsilon defines the maximal distance between two angles to detect a face
     * @param checkVerticesInsteadOfIndices indicates that we should check vertex list directly instead of faces
     * @returns the currentAbstractMesh
     * @see [https://www.babylonjs-playground.com/#19O9TU#0]
     */
    fun enableEdgesRendering(epsilon: Number?, checkVerticesInsteadOfIndices: Boolean?): AbstractMesh

    override fun dispose()

    companion object {
        /** No occlusion */
        var OCCLUSION_TYPE_NONE: Number
        /** Occlusion set to optimisitic */
        var OCCLUSION_TYPE_OPTIMISTIC: Number
        /** Occlusion set to strict */
        var OCCLUSION_TYPE_STRICT: Number
        /** Use an accurante occlusion algorithm */
        var OCCLUSION_ALGORITHM_TYPE_ACCURATE: Number
        /** Use a conservative occlusion algorithm */
        var OCCLUSION_ALGORITHM_TYPE_CONSERVATIVE: Number
        /** Default culling strategy : this is an exclusion test and it's the more accurate.
         *  Test order :
         *  Is the bounding sphere outside the frustum ?
         *  If not, are the bounding box vertices outside the frustum ?
         *  It not, then the cullable object is in the frustum.
         */
        val CULLINGSTRATEGY_STANDARD: Number
        /** Culling strategy : Bounding Sphere Only.
         *  This is an exclusion test. It's faster than the standard strategy because the bounding box is not tested.
         *  It's also less accurate than the standard because some not visible objects can still be selected.
         *  Test : is the bounding sphere outside the frustum ?
         *  If not, then the cullable object is in the frustum.
         */
        val CULLINGSTRATEGY_BOUNDINGSPHERE_ONLY: Number
        /** Culling strategy : Optimistic Inclusion.
         *  This in an inclusion test first, then the standard exclusion test.
         *  This can be faster when a cullable object is expected to be almost always in the camera frustum.
         *  This could also be a little slower than the standard test when the tested object center is not the frustum but one of its bounding box vertex is still inside.
         *  Anyway, it's as accurate as the standard strategy.
         *  Test :
         *  Is the cullable object bounding sphere center in the frustum ?
         *  If not, apply the default culling strategy.
         */
        val CULLINGSTRATEGY_OPTIMISTIC_INCLUSION: Number
        /** Culling strategy : Optimistic Inclusion then Bounding Sphere Only.
         *  This in an inclusion test first, then the bounding sphere only exclusion test.
         *  This can be the fastest test when a cullable object is expected to be almost always in the camera frustum.
         *  This could also be a little slower than the BoundingSphereOnly strategy when the tested object center is not in the frustum but its bounding sphere still intersects it.
         *  It's less accurate than the standard strategy and as accurate as the BoundingSphereOnly strategy.
         *  Test :
         *  Is the cullable object bounding sphere center in the frustum ?
         *  If not, apply the Bounding Sphere Only strategy. No Bounding Box is tested here.
         */
        val CULLINGSTRATEGY_OPTIMISTIC_INCLUSION_THEN_BSPHERE_ONLY: Number
        /**
         * No billboard
         */
        val BILLBOARDMODE_NONE: Number
        /** Billboard on X axis */
        val BILLBOARDMODE_X: Number
        /** Billboard on Y axis */
        val BILLBOARDMODE_Y: Number
        /** Billboard on Z axis */
        val BILLBOARDMODE_Z: Number
        /** Billboard on all axes */
        val BILLBOARDMODE_ALL: Number
    }
}

/**
 * Class used to represent renderable models
 */
external class Mesh: AbstractMesh, IGetSetVerticesData {
    /**
     * An event triggered before rendering the mesh
     */
    val onBeforeRenderObservable: Observable<Mesh>
    /**
     * An event triggered before binding the mesh
     */
    val onBeforeBindObservable: Observable<Mesh>
    /**
     * An event triggered after rendering the mesh
     */
    val onAfterRenderObservable: Observable<Mesh>
    /**
     * An event triggered before drawing the mesh
     */
    val onBeforeDrawObservable: Observable<Mesh>
    /**
     * Sets a callback to call before drawing the mesh. It is recommended to use onBeforeDrawObservable instead
     */
    var onBeforeDraw: ()-> Unit
    /**
     * Gets the delay loading state of the mesh (when delay loading is turned on)
     * @see [http://doc.babylonjs.com/how_to/using_the_incremental_loading_system]
     */
    var delayLoadState: Number
    /**
     * Gets the list of instances created from this mesh
     * it is not supposed to be modified manually.
     * Note also that the order of the InstancedMesh wihin the array is not significant and might change.
     * @see [http://doc.babylonjs.com/how_to/how_to_use_instances]
     */
    var instances: Array<InstancedMesh>
    /**
     * Gets the file containing delay loading data for this mesh
     */
    var delayLoadingFile: String
    /**
     * User defined function used to change how LOD level selection is done
     * @see [http://doc.babylonjs.com/how_to/how_to_use_lod]
     */
    var onLODLevelSelection: (distance: Number, mesh: Mesh, selectedLevel: Mesh?)-> Unit
    /**
     * Gets or sets the morph target manager
     * @see [http://doc.babylonjs.com/how_to/how_to_use_morphtargets]
     */
    var morphTargetManager: MorphTargetManager?
    /**
     * Use this property to change the original side orientation defined at construction time
     */
    var overrideMaterialSideOrientation: Number?
    /**
     * Gets the source mesh (the one used to clone this one from)
     */
    val source: Mesh?
    /**
     * Gets or sets a Boolean indicating that this mesh does not use index buffer
     */
    var isUnIndexed: Boolean
    /**
     * @constructor
     * @param name The value used by scene.getMeshByName() to do a lookup.
     * @param scene The scene to add this mesh to.
     * @param parent The parent of this mesh, if it has one
     * @param source An optional Mesh from which geometry is shared, cloned.
     * @param doNotCloneChildren When cloning, skip cloning child meshes of source, default False.
     *                  When false, achieved by calling a clone(), also passing False.
     *                  This will make creation of children, recursive.
     * @param clonePhysicsImpostor When cloning, include cloning mesh physics impostor, default True.
     */
    constructor(name: String, scene: Scene? = definedExternally, parent: Node? = definedExternally, source: Mesh? = definedExternally, doNotCloneChildren: Boolean? = definedExternally, clonePhysicsImpostor: Boolean? = definedExternally)
    /**
     * Gets a Boolean indicating if this mesh has LOD
     */
    val hasLODLevels: Boolean
    /**
     * Gets the list of MeshLODLevel associated with the current mesh
     * @returns an array of MeshLODLevel
     */
    fun getLODLevels(): Array<MeshLODLevel>
    /**
     * Add a mesh as LOD level triggered at the given distance.
     * @see [https://doc.babylonjs.com/how_to/how_to_use_lod]
     * @param distance The distance from the center of the object to show this level
     * @param mesh The mesh to be added as LOD level (can be null)
     * @return This mesh (for chaining)
     */
    fun addLODLevel(distance: Number, mesh: Mesh?): Mesh
    /**
     * Returns the LOD level mesh at the passed distance or null if not found.
     * @see [https://doc.babylonjs.com/how_to/how_to_use_lod]
     * @param distance The distance from the center of the object to show this level
     * @returns a Mesh or `null`
     */
    fun getLODLevelAtDistance(distance: Number): Mesh?
    /**
     * Remove a mesh from the LOD array
     * @see [https://doc.babylonjs.com/how_to/how_to_use_lod]
     * @param mesh defines the mesh to be removed
     * @return This mesh (for chaining)
     */
    fun removeLODLevel(mesh: Mesh): Mesh
    /**
     * Returns the registered LOD mesh distant from the parameter `camera` position if Any, else returns the current mesh.
     * @see [https://doc.babylonjs.com/how_to/how_to_use_lod]
     * @param camera defines the camera to use to compute distance
     * @param boundingSphere defines a custom bounding sphere to use instead of the one from this mesh
     * @return This mesh (for chaining)
     */
    fun getLOD(camera: Camera, boundingSphere: BoundingSphere?): AbstractMesh?
    /**
     * Gets the mesh internal Geometry object
     */
    val geometry: Geometry?
    /**
     * Returns the mesh VertexBuffer object from the requested `kind`
     * @param kind defines which buffer to read from (positions, indices, normals, etc). Possible `kind` values :
     * - VertexBuffer.PositionKind
     * - VertexBuffer.UVKind
     * - VertexBuffer.UV2Kind
     * - VertexBuffer.UV3Kind
     * - VertexBuffer.UV4Kind
     * - VertexBuffer.UV5Kind
     * - VertexBuffer.UV6Kind
     * - VertexBuffer.ColorKind
     * - VertexBuffer.MatricesIndicesKind
     * - VertexBuffer.MatricesIndicesExtraKind
     * - VertexBuffer.MatricesWeightsKind
     * - VertexBuffer.MatricesWeightsExtraKind
     * @returns a FloatArray or null if the mesh has no vertex buffer for this kind.
     */
    fun getVertexBuffer(kind: String): VertexBuffer?
    /**
     * Returns a Boolean defining if the vertex data for the requested `kind` is updatable.
     * @param kind defines which buffer to check (positions, indices, normals, etc). Possible `kind` values :
     * - VertexBuffer.PositionKind
     * - VertexBuffer.UVKind
     * - VertexBuffer.UV2Kind
     * - VertexBuffer.UV3Kind
     * - VertexBuffer.UV4Kind
     * - VertexBuffer.UV5Kind
     * - VertexBuffer.UV6Kind
     * - VertexBuffer.ColorKind
     * - VertexBuffer.MatricesIndicesKind
     * - VertexBuffer.MatricesIndicesExtraKind
     * - VertexBuffer.MatricesWeightsKind
     * - VertexBuffer.MatricesWeightsExtraKind
     * @returns a Boolean
     */
    fun isVertexBufferUpdatable(kind: String): Boolean
    /**
     * Returns a String which contains the list of existing `kinds` of Vertex Data associated with this mesh.
     * @returns an array of strings
     */
    fun getVerticesDataKinds(): Array<String>
    /**
     * Determine if the current mesh is ready to be rendered
     * @param completeCheck defines if a complete check (including materials and lights) has to be done (false by default)
     * @param forceInstanceSupport will check if the mesh will be ready when used with instances (false by default)
     * @returns true if all associated assets are ready (material, textures, shaders)
     */
    fun isReady(completeCheck: Boolean?, forceInstanceSupport: Boolean?): Boolean
    /**
     * Gets a Boolean indicating if the normals aren't to be recomputed on next mesh `positions` array update. This property is pertinent only for updatable parametric shapes.
     */
    val areNormalsFrozen: Boolean
    /**
     * This function affects parametric shapes on vertex position update only : ribbons, tubes, etc. It has no effect at all on other shapes. It prevents the mesh normals from being recomputed on next `positions` array update.
     * @returns the current mesh
     */
    fun freezeNormals(): Mesh
    /**
     * This function affects parametric shapes on vertex position update only : ribbons, tubes, etc. It has no effect at all on other shapes. It reactivates the mesh normals computation if it was previously frozen
     * @returns the current mesh
     */
    fun unfreezeNormals(): Mesh
    /**
     * Sets a value overriding the instance count. Only applicable when custom instanced InterleavedVertexBuffer are used rather than InstancedMeshs
     */
    var overridenInstanceCount: Number
    /**
     * This function will subdivide the mesh into multiple submeshes
     * @param count defines the expected Number of submeshes
     */
    fun subdivide(count: Number)
    /**
     * Flags an associated vertex buffer as updatable
     * @param kind defines which buffer to use (positions, indices, normals, etc). Possible `kind` values :
     * - VertexBuffer.PositionKind
     * - VertexBuffer.UVKind
     * - VertexBuffer.UV2Kind
     * - VertexBuffer.UV3Kind
     * - VertexBuffer.UV4Kind
     * - VertexBuffer.UV5Kind
     * - VertexBuffer.UV6Kind
     * - VertexBuffer.ColorKind
     * - VertexBuffer.MatricesIndicesKind
     * - VertexBuffer.MatricesIndicesExtraKind
     * - VertexBuffer.MatricesWeightsKind
     * - VertexBuffer.MatricesWeightsExtraKind
     * @param updatable defines if the updated vertex buffer must be flagged as updatable
     */
    fun markVerticesDataAsUpdatable(kind: String, updatable: Boolean?)
    /**
     * Sets the mesh global Vertex Buffer
     * @param buffer defines the buffer to use
     * @returns the current mesh
     */
    fun setVerticesBuffer(buffer: VertexBuffer): Mesh
    /**
     * This method updates the vertex positions of an updatable mesh according to the `positionFunction` returned values.
     * @see [http://doc.babylonjs.com/how_to/how_to_dynamically_morph_a_mesh#other-shapes-updatemeshpositions]
     * @param positionFunction is a simple JS function what is passed the mesh `positions` array. It doesn't need to return anything
     * @param computeNormals is a Boolean (default true) to enable/disable the mesh normal recomputation after the vertex position update
     * @returns the current mesh
     */
    fun updateMeshPositions(positionFunction: (data: FloatArray)-> Unit, computeNormals: Boolean?): Mesh
    /**
     * Creates a un-shared specific occurence of the geometry for the mesh.
     * @returns the current mesh
     */
    fun makeGeometryUnique(): Mesh
    /**
     * Invert the geometry to move from a right handed system to a left handed one.
     * @returns the current mesh
     */
    fun toLeftHanded(): Mesh
    /**
     * Registers for this mesh a javascript function called just before the rendering process
     * @param func defines the function to call before rendering this mesh
     * @returns the current mesh
     */
    fun registerBeforeRender(func: (mesh: AbstractMesh)-> Unit): Mesh
    /**
     * Disposes a previously registered javascript function called before the rendering
     * @param func defines the function to remove
     * @returns the current mesh
     */
    fun unregisterBeforeRender(func: (mesh: AbstractMesh)-> Unit): Mesh
    /**
     * Registers for this mesh a javascript function called just after the rendering is complete
     * @param func defines the function to call after rendering this mesh
     * @returns the current mesh
     */
    fun registerAfterRender(func: (mesh: AbstractMesh)-> Unit): Mesh
    /**
     * Disposes a previously registered javascript function called after the rendering.
     * @param func defines the function to remove
     * @returns the current mesh
     */
    fun unregisterAfterRender(func: (mesh: AbstractMesh)-> Unit): Mesh
    /**
     * Triggers the draw call for the mesh. Usually, you don't need to call this method by your own because the mesh rendering is handled by the scene rendering manager
     * @param subMesh defines the subMesh to render
     * @param enableAlphaMode defines if alpha mode can be changed
     * @returns the current mesh
     */
    fun render(subMesh: SubMesh, enableAlphaMode: Boolean): Mesh
    /**
     *   Renormalize the mesh and patch it up if there are no weights
     *   Similar to normalization by adding the weights compute the reciprocal and multiply all elements, this wil ensure that everything adds to 1.
     *   However in the case of zero weights then we set just a single influence to 1.
     *   We check in the function for extra's present and if so we use the normalizeSkinWeightsWithExtras rather than the FourWeights version.
     */
    fun cleanMatrixWeights()
    /**
     * ValidateSkinning is used to determine that a mesh has valid skinning data along with skin metrics, if missing weights,
     * or not normalized it is returned as invalid mesh the String can be used for console logs, or on screen messages to let
     * the user know there was an issue with importing the mesh
     * @returns a validation object with skinned, valid and report String
     */
    fun validateSkinning(): Any
    /*{
        var skinned: Boolean
        var valid: Boolean
        var report: String
    }*/
    /**
     * Sets the mesh material by the material or multiMaterial `id` property
     * @param id is a String identifying the material or the multiMaterial
     * @returns the current mesh
     */
    fun setMaterialByID(id: String): Mesh
    /**
     * Returns as a new array populated with the mesh material and/or skeleton, if Any.
     * @returns an array of IAnimatable
     */
    fun getAnimatables(): Array<IAnimatable>
    /**
     * Modifies the mesh geometry according to the passed transformation matrix.
     * This method returns nothing but it really modifies the mesh even if it's originally not set as updatable.
     * The mesh normals are modified using the same transformation.
     * Note that, under the hood, this method sets a new VertexBuffer each call.
     * @param transform defines the transform matrix to use
     * @see [http://doc.babylonjs.com/resources/baking_transformations]
     * @returns the current mesh
     */
    fun bakeTransformIntoVertices(transform: Matrix): Mesh
    /**
     * Modifies the mesh geometry according to its own current World Matrix.
     * The mesh World Matrix is then reset.
     * This method returns nothing but really modifies the mesh even if it's originally not set as updatable.
     * Note that, under the hood, this method sets a new VertexBuffer each call.
     * @see [http://doc.babylonjs.com/resources/baking_transformations]
     * @returns the current mesh
     */
    fun bakeCurrentTransformIntoVertices(): Mesh
    /**
     * Returns a new Mesh object generated from the current mesh properties.
     * This method must not get confused with createInstance()
     * @param name is a String, the name given to the new mesh
     * @param newParent can be Any Node object (default `null`)
     * @param doNotCloneChildren allows/denies the recursive cloning of the original mesh children if Any (default `false`)
     * @param clonePhysicsImpostor allows/denies the cloning in the same time of the original mesh `body` used by the physics engine, if Any (default `true`)
     * @returns a new mesh
     */
    fun clone(name: String?, newParent: Node?, doNotCloneChildren: Boolean?, clonePhysicsImpostor: Boolean?): Mesh
    /**
     * Modifies the mesh geometry according to a displacement map.
     * A displacement map is a colored image. Each pixel color value (actually a gradient computed from red, green, blue values) will give the displacement to apply to each mesh vertex.
     * The mesh must be set as updatable. Its internal geometry is directly modified, no new buffer are allocated.
     * @param url is a String, the URL from the image file is to be downloaded.
     * @param minHeight is the lower limit of the displacement.
     * @param maxHeight is the upper limit of the displacement.
     * @param onSuccess is an optional Javascript function to be called just after the mesh is modified. It is passed the modified mesh and must return nothing.
     * @param uvOffset is an optional vector2 used to offset UV.
     * @param uvScale is an optional vector2 used to scale UV.
     * @param forceUpdate defines whether or not to force an update of the generated buffers. This is useful to apply on a deserialized model for instance.
     * @returns the Mesh.
     */
    fun applyDisplacementMap(
        url: String, minHeight: Number, maxHeight: Number,
        onSuccess: ((mesh: Mesh)-> Unit)?,
        uvOffset: Vector2?, uvScale: Vector2?, forceUpdate: Boolean?
    ): Mesh
    /**
     * Modifies the mesh geometry according to a displacementMap buffer.
     * A displacement map is a colored image. Each pixel color value (actually a gradient computed from red, green, blue values) will give the displacement to apply to each mesh vertex.
     * The mesh must be set as updatable. Its internal geometry is directly modified, no new buffer are allocated.
     * @param buffer is a `Uint8Array` buffer containing series of `Uint8` lower than 255, the red, green, blue and alpha values of each successive pixel.
     * @param heightMapWidth is the width of the buffer image.
     * @param heightMapHeight is the height of the buffer image.
     * @param minHeight is the lower limit of the displacement.
     * @param maxHeight is the upper limit of the displacement.
     * @param uvOffset is an optional vector2 used to offset UV.
     * @param uvScale is an optional vector2 used to scale UV.
     * @param forceUpdate defines whether or not to force an update of the generated buffers. This is useful to apply on a deserialized model for instance.
     * @returns the Mesh.
     */
    fun applyDisplacementMapFromBuffer(buffer: Uint8Array, heightMapWidth: Number, heightMapHeight: Number, minHeight: Number, maxHeight: Number, uvOffset: Vector2?, uvScale: Vector2?, forceUpdate: Boolean?): Mesh
    /**
     * Modify the mesh to get a flat shading rendering.
     * This means each mesh facet will then have its own normals. Usually new vertices are added in the mesh geometry to get this result.
     * Warning : the mesh is really modified even if not set originally as updatable and, under the hood, a new VertexBuffer is allocated.
     * @returns current mesh
     */
    fun convertToFlatShadedMesh(): Mesh
    /**
     * This method removes all the mesh indices and add new vertices (duplication) in order to unfold facets into buffers.
     * In other words, more vertices, no more indices and a single bigger VBO.
     * The mesh is really modified even if not set originally as updatable. Under the hood, a new VertexBuffer is allocated.
     * @returns current mesh
     */
    fun convertToUnIndexedMesh(): Mesh
    /**
     * Inverses facet orientations.
     * Warning : the mesh is really modified even if not set originally as updatable. A new VertexBuffer is created under the hood each call.
     * @param flipNormals will also inverts the normals
     * @returns current mesh
     */
    fun flipFaces(flipNormals: Boolean?): Mesh
    /**
     * Increase the Number of facets and hence vertices in a mesh
     * Vertex normals are interpolated from existing vertex normals
     * Warning : the mesh is really modified even if not set originally as updatable. A new VertexBuffer is created under the hood each call.
     * @param numberPerEdge the Number of new vertices to add to each edge of a facet, optional default 1
     */
    fun increaseVertices(numberPerEdge: Number)
    /**
     * Force adjacent facets to share vertices and remove Any facets that have all vertices in a line
     * This will undo Any application of covertToFlatShadedMesh
     * Warning : the mesh is really modified even if not set originally as updatable. A new VertexBuffer is created under the hood each call.
     */
    fun forceSharedVertices()
    /**
     * Creates a new InstancedMesh object from the mesh model.
     * @see [http://doc.babylonjs.com/how_to/how_to_use_instances]
     * @param name defines the name of the new instance
     * @returns a new InstancedMesh
     */
    fun createInstance(name: String): InstancedMesh
    /**
     * Synchronises all the mesh instance submeshes to the current mesh submeshes, if Any.
     * After this call, all the mesh instances have the same submeshes than the current mesh.
     * @returns the current mesh
     */
    fun synchronizeInstances(): Mesh
    /**
     * Optimization of the mesh's indices, in case a mesh has duplicated vertices.
     * The function will only reorder the indices and will not remove unused vertices to avoid problems with submeshes.
     * This should be used together with the simplification to avoid disappearing triangles.
     * @param successCallback an optional success callback to be called after the optimization finished.
     * @returns the current mesh
     */
    fun optimizeIndices(successCallback: ((mesh: Mesh?)-> Unit)?): Mesh
    /**
     * Serialize current mesh
     * @param serializationObject defines the object which will receive the serialization data
     */
    fun serialize(serializationObject: Any)

    /**
     * Prepare internal position array for software CPU skinning
     * @returns original positions used for CPU skinning. Useful for integrating Morphing with skeletons in same mesh
     */
    fun setPositionsForCPUSkinning(): Float32Array
    /**
     * Prepare internal normal array for software CPU skinning
     * @returns original normals used for CPU skinning. Useful for integrating Morphing with skeletons in same mesh.
     */
    fun setNormalsForCPUSkinning(): Float32Array
    /**
     * Updates the vertex buffer by applying transformation from the bones
     * @param skeleton defines the skeleton to apply to current mesh
     * @returns the current mesh
     */
    fun applySkeleton(skeleton: Skeleton): Mesh

    override fun isVerticesDataPresent(kind: String): Boolean
    override fun getVerticesData(kind: String, copyWhenShared: Boolean?, forceCopy: Boolean?): FloatArray?
    override fun getIndices(copyWhenShared: Boolean?, forceCopy: Boolean?): IndicesArray?
    override fun setVerticesData(kind: String, data: FloatArray, updatable: Boolean)
    override fun updateVerticesData(kind: String, data: FloatArray, updateExtends: Boolean?, makeItUnique: Boolean?)
    override fun setIndices(indices: IndicesArray, totalVertices: Number?, updatable: Boolean?)
    override fun isInFrustum(frustumPlanes: Array<Plane>): Boolean
    override fun isCompletelyInFrustum(frustumPlanes: Array<Plane>): Boolean

    companion object {
        /**
         * Mesh side orientation : usually the external or front surface
         */
        val FRONTSIDE: Number
        /**
         * Mesh side orientation : usually the internal or back surface
         */
        val BACKSIDE: Number
        /**
         * Mesh side orientation : both internal and external or front and back surfaces
         */
        val DOUBLESIDE: Number
        /**
         * Mesh side orientation : by default, `FRONTSIDE`
         */
        val DEFAULTSIDE: Number
        /**
         * Mesh cap setting : no cap
         */
        val NO_CAP: Number
        /**
         * Mesh cap setting : one cap at the beginning of the mesh
         */
        val CAP_START: Number
        /**
         * Mesh cap setting : one cap at the end of the mesh
         */
        val CAP_END: Number
        /**
         * Mesh cap setting : two caps, one at the beginning  and one at the end of the mesh
         */
        val CAP_ALL: Number

        /**
         * Returns a new Mesh object parsed from the source provided.
         * @param parsedMesh is the source
         * @param scene defines the hosting scene
         * @param rootUrl is the root URL to prefix the `delayLoadingFile` property with
         * @returns a new Mesh
         */
        fun Parse(parsedMesh: Any, scene: Scene, rootUrl: String): Mesh
        /**
         * Creates a ribbon mesh. Please consider using the same method from the MeshBuilder class instead
         * @see [http://doc.babylonjs.com/how_to/parametric_shapes]
         * @param name defines the name of the mesh to create
         * @param pathArray is a required array of paths, what are each an array of successive Vector3. The pathArray parameter depicts the ribbon geometry.
         * @param closeArray creates a seam between the first and the last paths of the path array (default is false)
         * @param closePath creates a seam between the first and the last points of each path of the path array
         * @param offset is taken in account only if the `pathArray` is containing a single path
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @param instance defines an instance of an existing Ribbon object to be updated with the passed `pathArray` parameter ([http://doc.babylonjs.com/how_to/How_to_dynamically_morph_a_mesh#ribbon)]
         * @returns a new Mesh
         */
        fun CreateRibbon(
            name: String, pathArray: Array<Array<Vector3>>,
            closeArray: Boolean, closePath: Boolean, offset: Number, scene: Scene?,
            updatable: Boolean?, sideOrientation: Number?, instance: Mesh?
        ): Mesh
        /**
         * Creates a plane polygonal mesh.  By default, this is a disc. Please consider using the same method from the MeshBuilder class instead
         * @param name defines the name of the mesh to create
         * @param radius sets the radius size (float) of the polygon (default 0.5)
         * @param tessellation sets the Number of polygon sides (positive integer, default 64). So a tessellation valued to 3 will build a triangle, to 4 a square, etc
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @returns a new Mesh
         */
        fun CreateDisc(name: String, radius: Number, tessellation: Number, scene: Scene?, updatable: Boolean?, sideOrientation: Number?): Mesh
        /**
         * Creates a box mesh. Please consider using the same method from the MeshBuilder class instead
         * @param name defines the name of the mesh to create
         * @param size sets the size (float) of each box side (default 1)
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @returns a new Mesh
         */
        fun CreateBox(name: String, size: Number, scene: Scene?, updatable: Boolean?, sideOrientation: Number?): Mesh
        /**
         * Creates a sphere mesh. Please consider using the same method from the MeshBuilder class instead
         * @param name defines the name of the mesh to create
         * @param segments sets the sphere Number of horizontal stripes (positive integer, default 32)
         * @param diameter sets the diameter size (float) of the sphere (default 1)
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @returns a new Mesh
         */
        fun CreateSphere(name: String, segments: Number, diameter: Number, scene: Scene?, updatable: Boolean?, sideOrientation: Number?): Mesh
        /**
         * Creates a hemisphere mesh. Please consider using the same method from the MeshBuilder class instead
         * @param name defines the name of the mesh to create
         * @param segments sets the sphere Number of horizontal stripes (positive integer, default 32)
         * @param diameter sets the diameter size (float) of the sphere (default 1)
         * @param scene defines the hosting scene
         * @returns a new Mesh
         */
        fun CreateHemisphere(name: String, segments: Number, diameter: Number, scene: Scene?): Mesh
        /**
         * Creates a cylinder or a cone mesh. Please consider using the same method from the MeshBuilder class instead
         * @param name defines the name of the mesh to create
         * @param height sets the height size (float) of the cylinder/cone (float, default 2)
         * @param diameterTop set the top cap diameter (floats, default 1)
         * @param diameterBottom set the bottom cap diameter (floats, default 1). This value can't be zero
         * @param tessellation sets the Number of cylinder sides (positive integer, default 24). Set it to 3 to get a prism for instance
         * @param subdivisions sets the Number of rings along the cylinder height (positive integer, default 1)
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @returns a new Mesh
         */
        fun CreateCylinder(name: String, height: Number, diameterTop: Number, diameterBottom: Number, tessellation: Number, subdivisions: Any, scene: Scene?, updatable: Any?, sideOrientation: Number?): Mesh
        /**
         * Creates a torus mesh. Please consider using the same method from the MeshBuilder class instead
         * @param name defines the name of the mesh to create
         * @param diameter sets the diameter size (float) of the torus (default 1)
         * @param thickness sets the diameter size of the tube of the torus (float, default 0.5)
         * @param tessellation sets the Number of torus sides (postive integer, default 16)
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @returns a new Mesh
         */
        fun CreateTorus(name: String, diameter: Number, thickness: Number, tessellation: Number, scene: Scene?, updatable: Boolean?, sideOrientation: Number?): Mesh
        /**
         * Creates a torus knot mesh. Please consider using the same method from the MeshBuilder class instead
         * @param name defines the name of the mesh to create
         * @param radius sets the global radius size (float) of the torus knot (default 2)
         * @param tube sets the diameter size of the tube of the torus (float, default 0.5)
         * @param radialSegments sets the Number of sides on each tube segments (positive integer, default 32)
         * @param tubularSegments sets the Number of tubes to decompose the knot into (positive integer, default 32)
         * @param p the Number of windings on X axis (positive integers, default 2)
         * @param q the Number of windings on Y axis (positive integers, default 3)
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @returns a new Mesh
         */
        fun CreateTorusKnot(name: String, radius: Number, tube: Number, radialSegments: Number, tubularSegments: Number, p: Number, q: Number, scene: Scene?, updatable: Boolean?, sideOrientation: Number?): Mesh
        /**
         * Creates a line mesh. Please consider using the same method from the MeshBuilder class instead.
         * @param name defines the name of the mesh to create
         * @param points is an array successive Vector3
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param instance is an instance of an existing LineMesh object to be updated with the passed `points` parameter ([http://doc.babylonjs.com/how_to/How_to_dynamically_morph_a_mesh#lines-and-dashedlines).]
         * @returns a new Mesh
         */
        fun CreateLines(name: String, points: Array<Vector3>, scene: Scene?, updatable: Boolean?, instance: LinesMesh?): LinesMesh
        /**
         * Creates a dashed line mesh. Please consider using the same method from the MeshBuilder class instead
         * @param name defines the name of the mesh to create
         * @param points is an array successive Vector3
         * @param dashSize is the size of the dashes relatively the dash Number (positive float, default 3)
         * @param gapSize is the size of the gap between two successive dashes relatively the dash Number (positive float, default 1)
         * @param dashNb is the intended total Number of dashes (positive integer, default 200)
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param instance is an instance of an existing LineMesh object to be updated with the passed `points` parameter ([http://doc.babylonjs.com/how_to/How_to_dynamically_morph_a_mesh#lines-and-dashedlines)]
         * @returns a new Mesh
         */
        fun CreateDashedLines(name: String, points: Array<Vector3>, dashSize: Number, gapSize: Number, dashNb: Number, scene: Scene?, updatable: Boolean?, instance: LinesMesh?): LinesMesh
        /**
         * Creates a polygon mesh.Please consider using the same method from the MeshBuilder class instead
         * The polygon's shape will depend on the input parameters and is constructed parallel to a ground mesh.
         * The parameter `shape` is a required array of successive Vector3 representing the corners of the polygon in th XoZ plane, that is y = 0 for all vectors.
         * You can set the mesh side orientation with the values : Mesh.FRONTSIDE (default), Mesh.BACKSIDE or Mesh.DOUBLESIDE
         * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created.
         * Remember you can only change the shape positions, not their Number when updating a polygon.
         * @see [http://doc.babylonjs.com/how_to/parametric_shapes#non-regular-polygon]
         * @param name defines the name of the mesh to create
         * @param shape is a required array of successive Vector3 representing the corners of the polygon in th XoZ plane, that is y = 0 for all vectors
         * @param scene defines the hosting scene
         * @param holes is a required array of arrays of successive Vector3 used to defines holes in the polygon
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @param earcutInjection can be used to inject your own earcut reference
         * @returns a new Mesh
         */
        fun CreatePolygon(name: String, shape: Array<Vector3>, scene: Scene, holes: Array<Array<Vector3>>, updatable: Boolean?, sideOrientation: Number?, earcutInjection: Any?): Mesh
        /**
         * Creates an extruded polygon mesh, with depth in the Y direction. Please consider using the same method from the MeshBuilder class instead.
         * @see [http://doc.babylonjs.com/how_to/parametric_shapes#extruded-non-regular-polygon]
         * @param name defines the name of the mesh to create
         * @param shape is a required array of successive Vector3 representing the corners of the polygon in th XoZ plane, that is y = 0 for all vectors
         * @param depth defines the height of extrusion
         * @param scene defines the hosting scene
         * @param holes is a required array of arrays of successive Vector3 used to defines holes in the polygon
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @param earcutInjection can be used to inject your own earcut reference
         * @returns a new Mesh
         */
        fun ExtrudePolygon(name: String, shape: Array<Vector3>, depth: Number, scene: Scene, holes: Array<Array<Vector3>>?, updatable: Boolean?, sideOrientation: Number?, earcutInjection: Any?): Mesh
        /**
         * Creates an extruded shape mesh.
         * The extrusion is a parametric shape. It has no predefined shape. Its final shape will depend on the input parameters. Please consider using the same method from the MeshBuilder class instead
         * @see [http://doc.babylonjs.com/how_to/parametric_shapes]
         * @see [http://doc.babylonjs.com/how_to/parametric_shapes#extruded-shapes]
         * @param name defines the name of the mesh to create
         * @param shape is a required array of successive Vector3. This array depicts the shape to be extruded in its local space : the shape must be designed in the xOy plane and will be extruded along the Z axis
         * @param path is a required array of successive Vector3. This is the axis curve the shape is extruded along
         * @param scale is the value to scale the shape
         * @param rotation is the angle value to rotate the shape each step (each path point), from the former step (so rotation added each step) along the curve
         * @param cap sets the way the extruded shape is capped. Possible values : Mesh.NO_CAP (default), Mesh.CAP_START, Mesh.CAP_END, Mesh.CAP_ALL
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @param instance is an instance of an existing ExtrudedShape object to be updated with the passed `shape`, `path`, `scale` or `rotation` parameters ([http://doc.babylonjs.com/how_to/How_to_dynamically_morph_a_mesh#extruded-shape)]
         * @returns a new Mesh
         */
        fun ExtrudeShape(name: String, shape: Array<Vector3>, path: Array<Vector3>, scale: Number, rotation: Number, cap: Number, scene: Scene?, updatable: Boolean?, sideOrientation: Number?, instance: Mesh?): Mesh
        /**
         * Creates an custom extruded shape mesh.
         * The custom extrusion is a parametric shape.
         * It has no predefined shape. Its final shape will depend on the input parameters.
         * Please consider using the same method from the MeshBuilder class instead
         * @see [http://doc.babylonjs.com/how_to/parametric_shapes#extruded-shapes]
         * @param name defines the name of the mesh to create
         * @param shape is a required array of successive Vector3. This array depicts the shape to be extruded in its local space : the shape must be designed in the xOy plane and will be extruded along the Z axis
         * @param path is a required array of successive Vector3. This is the axis curve the shape is extruded along
         * @param scaleFunction is a custom Javascript function called on each path point
         * @param rotationFunction is a custom Javascript function called on each path point
         * @param ribbonCloseArray forces the extrusion underlying ribbon to close all the paths in its `pathArray`
         * @param ribbonClosePath forces the extrusion underlying ribbon to close its `pathArray`
         * @param cap sets the way the extruded shape is capped. Possible values : Mesh.NO_CAP (default), Mesh.CAP_START, Mesh.CAP_END, Mesh.CAP_ALL
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @param instance is an instance of an existing ExtrudedShape object to be updated with the passed `shape`, `path`, `scale` or `rotation` parameters ([http://doc.babylonjs.com/how_to/how_to_dynamically_morph_a_mesh#extruded-shape)]
         * @returns a new Mesh
         */
        fun ExtrudeShapeCustom(name: String, shape: Array<Vector3>, path: Array<Vector3>, scaleFunction: () -> Unit, rotationFunction: () -> Unit, ribbonCloseArray: Boolean, ribbonClosePath: Boolean, cap: Number, scene: Scene, updatable: Boolean?, sideOrientation: Number?, instance: Mesh?): Mesh
        /**
         * Creates lathe mesh.
         * The lathe is a shape with a symetry axis : a 2D model shape is rotated around this axis to design the lathe.
         * Please consider using the same method from the MeshBuilder class instead
         * @param name defines the name of the mesh to create
         * @param shape is a required array of successive Vector3. This array depicts the shape to be rotated in its local space : the shape must be designed in the xOy plane and will be rotated around the Y axis. It's usually a 2D shape, so the Vector3 z coordinates are often set to zero
         * @param radius is the radius value of the lathe
         * @param tessellation is the side Number of the lathe.
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @returns a new Mesh
         */
        fun CreateLathe(name: String, shape: Array<Vector3>, radius: Number, tessellation: Number, scene: Scene, updatable: Boolean?, sideOrientation: Number?): Mesh
        /**
         * Creates a plane mesh. Please consider using the same method from the MeshBuilder class instead
         * @param name defines the name of the mesh to create
         * @param size sets the size (float) of both sides of the plane at once (default 1)
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @returns a new Mesh
         */
        fun CreatePlane(name: String, size: Number, scene: Scene, updatable: Boolean?, sideOrientation: Number?): Mesh
        /**
         * Creates a ground mesh.
         * Please consider using the same method from the MeshBuilder class instead
         * @param name defines the name of the mesh to create
         * @param width set the width of the ground
         * @param height set the height of the ground
         * @param subdivisions sets the Number of subdivisions per side
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @returns a new Mesh
         */
        fun CreateGround(name: String, width: Number, height: Number, subdivisions: Number, scene: Scene?, updatable: Boolean?): Mesh
        /**
         * Creates a tiled ground mesh.
         * Please consider using the same method from the MeshBuilder class instead
         * @param name defines the name of the mesh to create
         * @param xmin set the ground minimum X coordinate
         * @param zmin set the ground minimum Y coordinate
         * @param xmax set the ground maximum X coordinate
         * @param zmax set the ground maximum Z coordinate
         * @param subdivisions is an object `{w: positive integer, h: positive integer}` (default `{w: 6, h: 6}`). `w` and `h` are the numbers of subdivisions on the ground width and height. Each subdivision is called a tile
         * @param precision is an object `{w: positive integer, h: positive integer}` (default `{w: 2, h: 2}`). `w` and `h` are the numbers of subdivisions on the ground width and height of each tile
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @returns a new Mesh
         */
        fun CreateTiledGround(name: String, xmin: Number, zmin: Number, xmax: Number, zmax: Number, subdivisions: SizeOptions, precision: SizeOptions, scene: Scene, updatable: Boolean?): Mesh
        /**
         * Creates a ground mesh from a height map.
         * Please consider using the same method from the MeshBuilder class instead
         * @see [http://doc.babylonjs.com/babylon101/height_map]
         * @param name defines the name of the mesh to create
         * @param url sets the URL of the height map image resource
         * @param width set the ground width size
         * @param height set the ground height size
         * @param subdivisions sets the Number of subdivision per side
         * @param minHeight is the minimum altitude on the ground
         * @param maxHeight is the maximum altitude on the ground
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param onReady  is a callback function that will be called  once the mesh is built (the height map download can last some time)
         * @param alphaFilter will filter Any data where the alpha channel is below this value, defaults 0 (all data visible)
         * @returns a new Mesh
         */
        fun CreateGroundFromHeightMap(name: String, url: String, width: Number, height: Number, subdivisions: Number, minHeight: Number, maxHeight: Number, scene: Scene, updatable: Boolean?, onReady: ((mesh: GroundMesh)-> Unit)?, alphaFilter: Number?): GroundMesh
        /**
         * Creates a tube mesh.
         * The tube is a parametric shape.
         * It has no predefined shape. Its final shape will depend on the input parameters.
         * Please consider using the same method from the MeshBuilder class instead
         * @see [http://doc.babylonjs.com/how_to/parametric_shapes]
         * @param name defines the name of the mesh to create
         * @param path is a required array of successive Vector3. It is the curve used as the axis of the tube
         * @param radius sets the tube radius size
         * @param tessellation is the Number of sides on the tubular surface
         * @param radiusFunction is a custom function. If it is not null, it overwrittes the parameter `radius`. This function is called on each point of the tube path and is passed the index `i` of the i-th point and the distance of this point from the first point of the path
         * @param cap sets the way the extruded shape is capped. Possible values : Mesh.NO_CAP (default), Mesh.CAP_START, Mesh.CAP_END, Mesh.CAP_ALL
         * @param scene defines the hosting scene
         * @param updatable defines if the mesh must be flagged as updatable
         * @param sideOrientation defines the mesh side orientation ([http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation)]
         * @param instance is an instance of an existing Tube object to be updated with the passed `pathArray` parameter ([http://doc.babylonjs.com/how_to/How_to_dynamically_morph_a_mesh#tube)]
         * @returns a new Mesh
         */
        fun CreateTube(name: String, path: Array<Vector3>, radius: Number, tessellation: Number, radiusFunction: ((i: Number, distance: Number) -> Number)?, cap: Number, scene: Scene, updatable: Boolean?, sideOrientation: Number?, instance: Mesh?): Mesh
        /**
         * Creates a polyhedron mesh.
         * Please consider using the same method from the MeshBuilder class instead.
         * * The parameter `type` (positive integer, max 14, default 0) sets the polyhedron type to build among the 15 embbeded types. Please refer to the type sheet in the tutorial to choose the wanted type
         * * The parameter `size` (positive float, default 1) sets the polygon size
         * * You can overwrite the `size` on each dimension bu using the parameters `sizeX`, `sizeY` or `sizeZ` (positive floats, default to `size` value)
         * * You can build other polyhedron types than the 15 embbeded ones by setting the parameter `custom` (`polyhedronObject`, default null). If you set the parameter `custom`, this overwrittes the parameter `type`
         * * A `polyhedronObject` is a formatted javascript object. You'll find a full file with pre-set polyhedra here : [https://github.com/BabylonJS/Extensions/tree/master/Polyhedron]
         * * You can set the color and the UV of each side of the polyhedron with the parameters `faceColors` (Color4, default `(1, 1, 1, 1)`) and faceUV (Vector4, default `(0, 0, 1, 1)`)
         * * To understand how to set `faceUV` or `faceColors`, please read this by considering the right Number of faces of your polyhedron, instead of only 6 for the box : [https://doc.babylonjs.com/how_to/createbox_per_face_textures_and_colors]
         * * The parameter `flat` (Boolean, default true). If set to false, it gives the polyhedron a single global face, so less vertices and shared normals. In this case, `faceColors` and `faceUV` are ignored
         * * You can also set the mesh side orientation with the values : Mesh.FRONTSIDE (default), Mesh.BACKSIDE or Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh to create
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns a new Mesh
         */
        fun CreatePolyhedron(name: String, options: PolyhedronOptions, scene: Scene): Mesh
        /**
         * Creates a sphere based upon an icosahedron with 20 triangular faces which can be subdivided
         * * The parameter `radius` sets the radius size (float) of the icosphere (default 1)
         * * You can set some different icosphere dimensions, for instance to build an ellipsoid, by using the parameters `radiusX`, `radiusY` and `radiusZ` (all by default have the same value than `radius`)
         * * The parameter `subdivisions` sets the Number of subdivisions (postive integer, default 4). The more subdivisions, the more faces on the icosphere whatever its size
         * * The parameter `flat` (Boolean, default true) gives each side its own normals. Set it to false to get a smooth continuous light reflection on the surface
         * * You can also set the mesh side orientation with the values : Mesh.FRONTSIDE (default), Mesh.BACKSIDE or Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [http://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns a new Mesh
         * @see [http://doc.babylonjs.com/how_to/polyhedra_shapes#icosphere]
         */
        fun CreateIcoSphere(name: String, options: IcoSphereOptions, scene: Scene): Mesh
        /**
         * Creates a decal mesh.
         * Please consider using the same method from the MeshBuilder class instead.
         * A decal is a mesh usually applied as a model onto the surface of another mesh
         * @param name  defines the name of the mesh
         * @param sourceMesh defines the mesh receiving the decal
         * @param position sets the position of the decal in world coordinates
         * @param normal sets the normal of the mesh where the decal is applied onto in world coordinates
         * @param size sets the decal scaling
         * @param angle sets the angle to rotate the decal
         * @returns a new Mesh
         */
        fun CreateDecal(name: String, sourceMesh: AbstractMesh, position: Vector3, normal: Vector3, size: Vector3, angle: Number): Mesh
        /**
         * Returns an object containing a min and max Vector3 which are the minimum and maximum vectors of each mesh bounding box from the passed array, in the world coordinates
         * @param meshes defines the list of meshes to scan
         * @returns an object `{min:` Vector3`, max:` Vector3`}`
         */
        fun MinMax(meshes: Array<AbstractMesh>): Any
        /* { var min: Vector3; var max: Vector3 } */

        /**
         * Returns the center of the `{min:` Vector3`, max:` Vector3`}` or the center of MinMax vector3 computed from a mesh array
         * @param meshesOrMinMaxVector could be an array of meshes or a `{min:` Vector3`, max:` Vector3`}` object
         * @returns a vector3
         */
        fun Center(meshesOrMinMaxVector: Any /* { var min: Vector3; var max: Vector3 } | Array<AbstractMesh>*/): Vector3
        /**
         * Merge the array of meshes into a single mesh for performance reasons.
         * @param meshes defines he vertices source.  They should all be of the same material.  Entries can empty
         * @param disposeSource when true (default), dispose of the vertices from the source meshes
         * @param allow32BitsIndices when the sum of the vertices > 64k, this must be set to true
         * @param meshSubclass when set, vertices inserted into this Mesh.  Meshes can then be merged into a Mesh sub-class.
         * @param subdivideWithSubMeshes when true (false default), subdivide mesh to his subMesh array with meshes source.
         * @param multiMultiMaterials when true (false default), subdivide mesh and accept multiple multi materials, ignores subdivideWithSubMeshes.
         * @returns a new mesh
         */
        fun MergeMeshes(meshes: Array<Mesh>, disposeSource: Boolean?, allow32BitsIndices: Boolean?, meshSubclass: Mesh?, subdivideWithSubMeshes: Boolean?, multiMultiMaterials: Boolean?): Mesh?
    }
}

/**
 * Class containing static functions to help procedurally build meshes
 */
external class MeshBuilder {
    companion object {
        /**
         * Creates a box mesh
         * * The parameter `size` sets the size (float) of each box side (default 1)
         * * You can set some different box dimensions by using the parameters `width`, `height` and `depth` (all by default have the same value of `size`)
         * * You can set different colors and different images to each box side by using the parameters `faceColors` (an array of 6 Color3 elements) and `faceUV` (an array of 6 Vector4 elements)
         * * Please read this tutorial : [https://doc.babylonjs.com/how_to/createbox_per_face_textures_and_colors]
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @see [https://doc.babylonjs.com/how_to/set_shapes#box]
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the box mesh
         */
        fun CreateBox(name: String, options: BoxOptions, scene: Scene?): Mesh
        /**
         * Creates a sphere mesh
         * * The parameter `diameter` sets the diameter size (float) of the sphere (default 1)
         * * You can set some different sphere dimensions, for instance to build an ellipsoid, by using the parameters `diameterX`, `diameterY` and `diameterZ` (all by default have the same value of `diameter`)
         * * The parameter `segments` sets the sphere Number of horizontal stripes (positive integer, default 32)
         * * You can create an unclosed sphere with the parameter `arc` (positive float, default 1), valued between 0 and 1, what is the ratio of the circumference (latitude) : 2 x PI x ratio
         * * You can create an unclosed sphere on its height with the parameter `slice` (positive float, default1), valued between 0 and 1, what is the height ratio (longitude)
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the sphere mesh
         * @see [https://doc.babylonjs.com/how_to/set_shapes#sphere]
         */
        fun CreateSphere(name: String, options: SphereOptions, scene: Scene?): Mesh
        /**
         * Creates a plane polygonal mesh.  By default, this is a disc
         * * The parameter `radius` sets the radius size (float) of the polygon (default 0.5)
         * * The parameter `tessellation` sets the Number of polygon sides (positive integer, default 64). So a tessellation valued to 3 will build a triangle, to 4 a square, etc
         * * You can create an unclosed polygon with the parameter `arc` (positive float, default 1), valued between 0 and 1, what is the ratio of the circumference : 2 x PI x ratio
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the plane polygonal mesh
         * @see [https://doc.babylonjs.com/how_to/set_shapes#disc-or-regular-polygon]
         */
        fun CreateDisc(name: String, options: DiscOptions, scene: Scene?): Mesh
        /**
         * Creates a sphere based upon an icosahedron with 20 triangular faces which can be subdivided
         * * The parameter `radius` sets the radius size (float) of the icosphere (default 1)
         * * You can set some different icosphere dimensions, for instance to build an ellipsoid, by using the parameters `radiusX`, `radiusY` and `radiusZ` (all by default have the same value of `radius`)
         * * The parameter `subdivisions` sets the Number of subdivisions (postive integer, default 4). The more subdivisions, the more faces on the icosphere whatever its size
         * * The parameter `flat` (Boolean, default true) gives each side its own normals. Set it to false to get a smooth continuous light reflection on the surface
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the icosahedron mesh
         * @see [https://doc.babylonjs.com/how_to/polyhedra_shapes#icosphere]
         */
        fun CreateIcoSphere(name: String, options: IcoSphereOptions, scene: Scene?): Mesh
        /**
         * Creates a ribbon mesh. The ribbon is a parametric shape.  It has no predefined shape. Its final shape will depend on the input parameters
         * * The parameter `pathArray` is a required array of paths, what are each an array of successive Vector3. The pathArray parameter depicts the ribbon geometry
         * * The parameter `closeArray` (Boolean, default false) creates a seam between the first and the last paths of the path array
         * * The parameter `closePath` (Boolean, default false) creates a seam between the first and the last points of each path of the path array
         * * The parameter `offset` (positive integer, default : rounded half size of the pathArray length), is taken in account only if the `pathArray` is containing a single path
         * * It's the offset to join the points from the same path. Ex : offset = 10 means the point 1 is joined to the point 11
         * * The optional parameter `instance` is an instance of an existing Ribbon object to be updated with the passed `pathArray` parameter : [https://doc.babylonjs.com/how_to/how_to_dynamically_morph_a_mesh#ribbon]
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The optional parameter `invertUV` (Boolean, default false) swaps in the geometry the U and V coordinates to apply a texture
         * * The parameter `uvs` is an optional flat array of `Vector2` to update/set each ribbon vertex with its own custom UV values instead of the computed ones
         * * The parameters `colors` is an optional flat array of `Color4` to set/update each ribbon vertex with its own custom color values
         * * Note that if you use the parameters `uvs` or `colors`, the passed arrays must be populated with the right Number of elements, it is to say the Number of ribbon vertices. Remember that if you set `closePath` to `true`, there's one extra vertex per path in the geometry
         * * Moreover, you can use the parameter `color` with `instance` (to update the ribbon), only if you previously used it at creation time
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the ribbon mesh
         * @see [https://doc.babylonjs.com/how_to/ribbon_tutorial]
         * @see [https://doc.babylonjs.com/how_to/parametric_shapes]
         */
        fun CreateRibbon(name: String, options: RibbonOptions, scene: Scene?): Mesh
        /**
         * Creates a cylinder or a cone mesh
         * * The parameter `height` sets the height size (float) of the cylinder/cone (float, default 2).
         * * The parameter `diameter` sets the diameter of the top and bottom cap at once (float, default 1).
         * * The parameters `diameterTop` and `diameterBottom` overwrite the parameter `diameter` and set respectively the top cap and bottom cap diameter (floats, default 1). The parameter "diameterBottom" can't be zero.
         * * The parameter `tessellation` sets the Number of cylinder sides (positive integer, default 24). Set it to 3 to get a prism for instance.
         * * The parameter `subdivisions` sets the Number of rings along the cylinder height (positive integer, default 1).
         * * The parameter `hasRings` (Boolean, default false) makes the subdivisions independent from each other, so they become different faces.
         * * The parameter `enclose`  (Boolean, default false) adds two extra faces per subdivision to a sliced cylinder to close it around its height axis.
         * * The parameter `arc` (float, default 1) is the ratio (max 1) to apply to the circumference to slice the cylinder.
         * * You can set different colors and different images to each box side by using the parameters `faceColors` (an array of n Color3 elements) and `faceUV` (an array of n Vector4 elements).
         * * The value of n is the Number of cylinder faces. If the cylinder has only 1 subdivisions, n equals : top face + cylinder surface + bottom face = 3
         * * Now, if the cylinder has 5 independent subdivisions (hasRings = true), n equals : top face + 5 stripe surfaces + bottom face = 2 + 5 = 7
         * * Finally, if the cylinder has 5 independent subdivisions and is enclose, n equals : top face + 5 x (stripe surface + 2 closing faces) + bottom face = 2 + 5 * 3 = 17
         * * Each array (color or UVs) is always ordered the same way : the first element is the bottom cap, the last element is the top cap. The other elements are each a ring surface.
         * * If `enclose` is false, a ring surface is one element.
         * * If `enclose` is true, a ring surface is 3 successive elements in the array : the tubular surface, then the two closing faces.
         * * Example how to set colors and textures on a sliced cylinder : [https://www.html5gamedevs.com/topic/17945-creating-a-closed-slice-of-a-cylinder/#comment-106379]
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created.
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the cylinder mesh
         * @see [https://doc.babylonjs.com/how_to/set_shapes#cylinder-or-cone]
         */
        fun CreateCylinder(name: String, options: CylinderOptions, scene: Scene?): Mesh
        /**
         * Creates a torus mesh
         * * The parameter `diameter` sets the diameter size (float) of the torus (default 1)
         * * The parameter `thickness` sets the diameter size of the tube of the torus (float, default 0.5)
         * * The parameter `tessellation` sets the Number of torus sides (postive integer, default 16)
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created.
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the torus mesh
         * @see [https://doc.babylonjs.com/how_to/set_shapes#torus]
         */
        fun CreateTorus(name: String, options: TorusOptions, scene: Scene?): Mesh
        /**
         * Creates a torus knot mesh
         * * The parameter `radius` sets the global radius size (float) of the torus knot (default 2)
         * * The parameter `radialSegments` sets the Number of sides on each tube segments (positive integer, default 32)
         * * The parameter `tubularSegments` sets the Number of tubes to decompose the knot into (positive integer, default 32)
         * * The parameters `p` and `q` are the Number of windings on each axis (positive integers, default 2 and 3)
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created.
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the torus knot mesh
         * @see  [https://doc.babylonjs.com/how_to/set_shapes#torus-knot]
         */
        fun CreateTorusKnot(name: String, options: TorusKnotOptions, scene: Scene?): Mesh
        /**
         * Creates a line system mesh. A line system is a pool of many lines gathered in a single mesh
         * * A line system mesh is considered as a parametric shape since it has no predefined original shape. Its shape is determined by the passed array of lines as an input parameter
         * * Like every other parametric shape, it is dynamically updatable by passing an existing instance of LineSystem to this static function
         * * The parameter `lines` is an array of lines, each line being an array of successive Vector3
         * * The optional parameter `instance` is an instance of an existing LineSystem object to be updated with the passed `lines` parameter
         * * The optional parameter `colors` is an array of line colors, each line colors being an array of successive Color4, one per line point
         * * The optional parameter `useVertexAlpha` is to be set to `false` (default `true`) when you don't need the alpha blending (faster)
         * * Updating a simple Line mesh, you just need to update every line in the `lines` array : [https://doc.babylonjs.com/how_to/how_to_dynamically_morph_a_mesh#lines-and-dashedlines]
         * * When updating an instance, remember that only line point positions can change, not the Number of points, neither the Number of lines
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @see [https://doc.babylonjs.com/how_to/parametric_shapes#line-system]
         * @param name defines the name of the new line system
         * @param options defines the options used to create the line system
         * @param scene defines the hosting scene
         * @returns a new line system mesh
         */
        fun CreateLineSystem(name: String, options: LineSystemOptions, scene: Scene?): LinesMesh
        /**
         * Creates a line mesh
         * A line mesh is considered as a parametric shape since it has no predefined original shape. Its shape is determined by the passed array of points as an input parameter
         * * Like every other parametric shape, it is dynamically updatable by passing an existing instance of LineMesh to this static function
         * * The parameter `points` is an array successive Vector3
         * * The optional parameter `instance` is an instance of an existing LineMesh object to be updated with the passed `points` parameter : [https://doc.babylonjs.com/how_to/how_to_dynamically_morph_a_mesh#lines-and-dashedlines]
         * * The optional parameter `colors` is an array of successive Color4, one per line point
         * * The optional parameter `useVertexAlpha` is to be set to `false` (default `true`) when you don't need alpha blending (faster)
         * * When updating an instance, remember that only point positions can change, not the Number of points
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @see [https://doc.babylonjs.com/how_to/parametric_shapes#lines]
         * @param name defines the name of the new line system
         * @param options defines the options used to create the line system
         * @param scene defines the hosting scene
         * @returns a new line mesh
         */
        fun CreateLines(name: String, options: LinesOptions, scene: Scene?): LinesMesh
        /**
         * Creates a dashed line mesh
         * * A dashed line mesh is considered as a parametric shape since it has no predefined original shape. Its shape is determined by the passed array of points as an input parameter
         * * Like every other parametric shape, it is dynamically updatable by passing an existing instance of LineMesh to this static function
         * * The parameter `points` is an array successive Vector3
         * * The parameter `dashNb` is the intended total Number of dashes (positive integer, default 200)
         * * The parameter `dashSize` is the size of the dashes relatively the dash Number (positive float, default 3)
         * * The parameter `gapSize` is the size of the gap between two successive dashes relatively the dash Number (positive float, default 1)
         * * The optional parameter `instance` is an instance of an existing LineMesh object to be updated with the passed `points` parameter : [https://doc.babylonjs.com/how_to/how_to_dynamically_morph_a_mesh#lines-and-dashedlines]
         * * When updating an instance, remember that only point positions can change, not the Number of points
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the dashed line mesh
         * @see [https://doc.babylonjs.com/how_to/parametric_shapes#dashed-lines]
         */
        fun CreateDashedLines(name: String, options: DashedLinesOptions, scene: Scene?): LinesMesh
        /**
         * Creates an extruded shape mesh. The extrusion is a parametric shape. It has no predefined shape. Its final shape will depend on the input parameters.
         * * The parameter `shape` is a required array of successive Vector3. This array depicts the shape to be extruded in its local space : the shape must be designed in the xOy plane and will be extruded along the Z axis.
         * * The parameter `path` is a required array of successive Vector3. This is the axis curve the shape is extruded along.
         * * The parameter `rotation` (float, default 0 radians) is the angle value to rotate the shape each step (each path point), from the former step (so rotation added each step) along the curve.
         * * The parameter `scale` (float, default 1) is the value to scale the shape.
         * * The parameter `cap` sets the way the extruded shape is capped. Possible values : BABYLON.Mesh.NO_CAP (default), BABYLON.Mesh.CAP_START, BABYLON.Mesh.CAP_END, BABYLON.Mesh.CAP_ALL
         * * The optional parameter `instance` is an instance of an existing ExtrudedShape object to be updated with the passed `shape`, `path`, `scale` or `rotation` parameters : [https://doc.babylonjs.com/how_to/how_to_dynamically_morph_a_mesh#extruded-shape]
         * * Remember you can only change the shape or path point positions, not their Number when updating an extruded shape.
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The optional parameter `invertUV` (Boolean, default false) swaps in the geometry the U and V coordinates to apply a texture.
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created.
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the extruded shape mesh
         * @see [https://doc.babylonjs.com/how_to/parametric_shapes]
         * @see [https://doc.babylonjs.com/how_to/parametric_shapes#extruded-shapes]
         */
        fun ExtrudeShape(name: String, options: ExtrudeOptions, scene: Scene?): Mesh
        /**
         * Creates an custom extruded shape mesh.
         * The custom extrusion is a parametric shape. It has no predefined shape. Its final shape will depend on the input parameters.
         * * The parameter `shape` is a required array of successive Vector3. This array depicts the shape to be extruded in its local space : the shape must be designed in the xOy plane and will be extruded along the Z axis.
         * * The parameter `path` is a required array of successive Vector3. This is the axis curve the shape is extruded along.
         * * The parameter `rotationFunction` (JS function) is a custom Javascript function called on each path point. This function is passed the position i of the point in the path and the distance of this point from the begining of the path
         * * It must returns a float value that will be the rotation in radians applied to the shape on each path point.
         * * The parameter `scaleFunction` (JS function) is a custom Javascript function called on each path point. This function is passed the position i of the point in the path and the distance of this point from the begining of the path
         * * It must returns a float value that will be the scale value applied to the shape on each path point
         * * The parameter `ribbonClosePath` (Boolean, default false) forces the extrusion underlying ribbon to close all the paths in its `pathArray`
         * * The parameter `ribbonCloseArray` (Boolean, default false) forces the extrusion underlying ribbon to close its `pathArray`
         * * The parameter `cap` sets the way the extruded shape is capped. Possible values : BABYLON.Mesh.NO_CAP (default), BABYLON.Mesh.CAP_START, BABYLON.Mesh.CAP_END, BABYLON.Mesh.CAP_ALL
         * * The optional parameter `instance` is an instance of an existing ExtrudedShape object to be updated with the passed `shape`, `path`, `scale` or `rotation` parameters : [https://doc.babylonjs.com/how_to/how_to_dynamically_morph_a_mesh#extruded-shape]
         * * Remember you can only change the shape or path point positions, not their Number when updating an extruded shape
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The optional parameter `invertUV` (Boolean, default false) swaps in the geometry the U and V coordinates to apply a texture
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the custom extruded shape mesh
         * @see [https://doc.babylonjs.com/how_to/parametric_shapes#custom-extruded-shapes]
         * @see [https://doc.babylonjs.com/how_to/parametric_shapes]
         * @see [https://doc.babylonjs.com/how_to/parametric_shapes#extruded-shapes]
         */
        fun ExtrudeShapeCustom(name: String, options: ExtrudeCustomOptions, scene: Scene?): Mesh
        /**
         * Creates lathe mesh.
         * The lathe is a shape with a symetry axis : a 2D model shape is rotated around this axis to design the lathe
         * * The parameter `shape` is a required array of successive Vector3. This array depicts the shape to be rotated in its local space : the shape must be designed in the xOy plane and will be rotated around the Y axis. It's usually a 2D shape, so the Vector3 z coordinates are often set to zero
         * * The parameter `radius` (positive float, default 1) is the radius value of the lathe
         * * The parameter `tessellation` (positive integer, default 64) is the side Number of the lathe
         * * The parameter `clip` (positive integer, default 0) is the Number of sides to not create without effecting the general shape of the sides
         * * The parameter `arc` (positive float, default 1) is the ratio of the lathe. 0.5 builds for instance half a lathe, so an opened shape
         * * The parameter `closed` (Boolean, default true) opens/closes the lathe circumference. This should be set to false when used with the parameter "arc"
         * * The parameter `cap` sets the way the extruded shape is capped. Possible values : BABYLON.Mesh.NO_CAP (default), BABYLON.Mesh.CAP_START, BABYLON.Mesh.CAP_END, BABYLON.Mesh.CAP_ALL
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The optional parameter `invertUV` (Boolean, default false) swaps in the geometry the U and V coordinates to apply a texture
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the lathe mesh
         * @see [https://doc.babylonjs.com/how_to/parametric_shapes#lathe]
         */
        fun CreateLathe(name: String, options: LatheOptions, scene: Scene?): Mesh
        /**
         * Creates a plane mesh
         * * The parameter `size` sets the size (float) of both sides of the plane at once (default 1)
         * * You can set some different plane dimensions by using the parameters `width` and `height` (both by default have the same value of `size`)
         * * The parameter `sourcePlane` is a Plane instance. It builds a mesh plane from a Math plane
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the plane mesh
         * @see [https://doc.babylonjs.com/how_to/set_shapes#plane]
         */
        fun CreatePlane(name: String, options: PlaneOptions, scene: Scene?): Mesh
        /**
         * Creates a ground mesh
         * * The parameters `width` and `height` (floats, default 1) set the width and height sizes of the ground
         * * The parameter `subdivisions` (positive integer) sets the Number of subdivisions per side
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the ground mesh
         * @see [https://doc.babylonjs.com/how_to/set_shapes#ground]
         */
        fun CreateGround(name: String, options: GroundOptions, scene: Scene?): Mesh
        /**
         * Creates a tiled ground mesh
         * * The parameters `xmin` and `xmax` (floats, default -1 and 1) set the ground minimum and maximum X coordinates
         * * The parameters `zmin` and `zmax` (floats, default -1 and 1) set the ground minimum and maximum Z coordinates
         * * The parameter `subdivisions` is a javascript object `{w: positive integer, h: positive integer}` (default `{w: 6, h: 6}`). `w` and `h` are the numbers of subdivisions on the ground width and height. Each subdivision is called a tile
         * * The parameter `precision` is a javascript object `{w: positive integer, h: positive integer}` (default `{w: 2, h: 2}`). `w` and `h` are the numbers of subdivisions on the ground width and height of each tile
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created.
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the tiled ground mesh
         * @see [https://doc.babylonjs.com/how_to/set_shapes#tiled-ground]
         */
        fun CreateTiledGround(name: String, options: TiledGroundOptions, scene: Scene?): Mesh
        /**
         * Creates a ground mesh from a height map
         * * The parameter `url` sets the URL of the height map image resource.
         * * The parameters `width` and `height` (positive floats, default 10) set the ground width and height sizes.
         * * The parameter `subdivisions` (positive integer, default 1) sets the Number of subdivision per side.
         * * The parameter `minHeight` (float, default 0) is the minimum altitude on the ground.
         * * The parameter `maxHeight` (float, default 1) is the maximum altitude on the ground.
         * * The parameter `colorFilter` (optional Color3, default (0.3, 0.59, 0.11) ) is the filter to apply to the image pixel colors to compute the height.
         * * The parameter `onReady` is a javascript callback function that will be called  once the mesh is just built (the height map download can last some time).
         * * The parameter `alphaFilter` will filter Any data where the alpha channel is below this value, defaults 0 (all data visible)
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created.
         * @param name defines the name of the mesh
         * @param url defines the url to the height map
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the ground mesh
         * @see [https://doc.babylonjs.com/babylon101/height_map]
         * @see [https://doc.babylonjs.com/how_to/set_shapes#ground-from-a-height-map]
         */
        fun CreateGroundFromHeightMap(name: String, url: String, options: GroundMapOptions, scene: Scene?): GroundMesh
        /**
         * Creates a polygon mesh
         * The polygon's shape will depend on the input parameters and is constructed parallel to a ground mesh
         * * The parameter `shape` is a required array of successive Vector3 representing the corners of the polygon in th XoZ plane, that is y = 0 for all vectors
         * * You can set the mesh side orientation with the values : Mesh.FRONTSIDE (default), Mesh.BACKSIDE or Mesh.DOUBLESIDE
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4)
         * * Remember you can only change the shape positions, not their Number when updating a polygon
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @param earcutInjection can be used to inject your own earcut reference
         * @returns the polygon mesh
         */
        fun CreatePolygon(name: String, options: PolygonOptions, scene: Scene?, earcutInjection: Any?): Mesh
        /**
         * Creates an extruded polygon mesh, with depth in the Y direction.
         * * You can set different colors and different images to the top, bottom and extruded side by using the parameters `faceColors` (an array of 3 Color3 elements) and `faceUV` (an array of 3 Vector4 elements)
         * @see [https://doc.babylonjs.com/how_to/createbox_per_face_textures_and_colors]
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @param earcutInjection can be used to inject your own earcut reference
         * @returns the polygon mesh
         */
        fun ExtrudePolygon(name: String, options: PolygonOptions, scene: Scene?, earcutInjection: Any?): Mesh
        /**
         * Creates a tube mesh.
         * The tube is a parametric shape. It has no predefined shape. Its final shape will depend on the input parameters
         * * The parameter `path` is a required array of successive Vector3. It is the curve used as the axis of the tube
         * * The parameter `radius` (positive float, default 1) sets the tube radius size
         * * The parameter `tessellation` (positive float, default 64) is the Number of sides on the tubular surface
         * * The parameter `radiusFunction` (javascript function, default null) is a vanilla javascript function. If it is not null, it overwrittes the parameter `radius`
         * * This function is called on each point of the tube path and is passed the index `i` of the i-th point and the distance of this point from the first point of the path. It must return a radius value (positive float)
         * * The parameter `arc` (positive float, maximum 1, default 1) is the ratio to apply to the tube circumference : 2 x PI x arc
         * * The parameter `cap` sets the way the extruded shape is capped. Possible values : BABYLON.Mesh.NO_CAP (default), BABYLON.Mesh.CAP_START, BABYLON.Mesh.CAP_END, BABYLON.Mesh.CAP_ALL
         * * The optional parameter `instance` is an instance of an existing Tube object to be updated with the passed `pathArray` parameter : [https://doc.babylonjs.com/how_to/how_to_dynamically_morph_a_mesh#tube]
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The optional parameter `invertUV` (Boolean, default false) swaps in the geometry the U and V coordinates to apply a texture
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the tube mesh
         * @see [https://doc.babylonjs.com/how_to/parametric_shapes]
         * @see [https://doc.babylonjs.com/how_to/set_shapes#tube]
         */
        fun CreateTube(name: String, options: TubeOptions, scene: Scene?): Mesh
        /**
         * Creates a polyhedron mesh
         * * The parameter `type` (positive integer, max 14, default 0) sets the polyhedron type to build among the 15 embbeded types. Please refer to the type sheet in the tutorial to choose the wanted type
         * * The parameter `size` (positive float, default 1) sets the polygon size
         * * You can overwrite the `size` on each dimension bu using the parameters `sizeX`, `sizeY` or `sizeZ` (positive floats, default to `size` value)
         * * You can build other polyhedron types than the 15 embbeded ones by setting the parameter `custom` (`polyhedronObject`, default null). If you set the parameter `custom`, this overwrittes the parameter `type`
         * * A `polyhedronObject` is a formatted javascript object. You'll find a full file with pre-set polyhedra here : [https://github.com/BabylonJS/Extensions/tree/master/Polyhedron]
         * * You can set the color and the UV of each side of the polyhedron with the parameters `faceColors` (Color4, default `(1, 1, 1, 1)`) and faceUV (Vector4, default `(0, 0, 1, 1)`)
         * * To understand how to set `faceUV` or `faceColors`, please read this by considering the right Number of faces of your polyhedron, instead of only 6 for the box : [https://doc.babylonjs.com/how_to/createbox_per_face_textures_and_colors]
         * * The parameter `flat` (Boolean, default true). If set to false, it gives the polyhedron a single global face, so less vertices and shared normals. In this case, `faceColors` and `faceUV` are ignored
         * * You can also set the mesh side orientation with the values : BABYLON.Mesh.FRONTSIDE (default), BABYLON.Mesh.BACKSIDE or BABYLON.Mesh.DOUBLESIDE
         * * If you create a double-sided mesh, you can choose what parts of the texture image to crop and stick respectively on the front and the back sides with the parameters `frontUVs` and `backUVs` (Vector4). Detail here : [https://doc.babylonjs.com/babylon101/discover_basic_elements#side-orientation]
         * * The mesh can be set to updatable with the Boolean parameter `updatable` (default false) if its internal geometry is supposed to change once created
         * @param name defines the name of the mesh
         * @param options defines the options used to create the mesh
         * @param scene defines the hosting scene
         * @returns the polyhedron mesh
         * @see [https://doc.babylonjs.com/how_to/polyhedra_shapes]
         */
        fun CreatePolyhedron(name: String, options: PolyhedronOptions, scene: Scene?): Mesh
        /**
         * Creates a decal mesh.
         * A decal is a mesh usually applied as a model onto the surface of another mesh. So don't forget the parameter `sourceMesh` depicting the decal
         * * The parameter `position` (Vector3, default `(0, 0, 0)`) sets the position of the decal in World coordinates
         * * The parameter `normal` (Vector3, default `Vector3.Up`) sets the normal of the mesh where the decal is applied onto in World coordinates
         * * The parameter `size` (Vector3, default `(1, 1, 1)`) sets the decal scaling
         * * The parameter `angle` (float in radian, default 0) sets the angle to rotate the decal
         * @param name defines the name of the mesh
         * @param sourceMesh defines the mesh where the decal must be applied
         * @param options defines the options used to create the mesh
         * @returns the decal mesh
         * @see [https://doc.babylonjs.com/how_to/decals]
         */
        fun CreateDecal(name: String, sourceMesh: AbstractMesh, options: DecalOptions): Mesh
    }
}

/**
 * Creates an instance based on a source mesh.
 */
external class InstancedMesh: AbstractMesh {

    constructor(name: String, source: Mesh)

    /**
     * The source mesh of the instance
     */
    val sourceMesh: Mesh

    /**
     * Returns an array of integers or a typed array (Int32Array, Uint32Array, Uint16Array) populated with the mesh indices.
     * @param kind kind of verticies to retreive (eg. positons, normals, uvs, etc.)
     * @param copyWhenShared If true (default false) and and if the mesh geometry is shared among some other meshes, the returned array is a copy of the internal one.
     * @returns a float array or a Float32Array of the requested kind of data : positons, normals, uvs, etc.
     */
    fun getVerticesData(kind: String, copyWhenShared: Boolean?): FloatArray?

    override fun getVerticesData(kind: String, copyWhenShared: Boolean?, forceCopy: Boolean?): FloatArray?
    override fun getIndices(copyWhenShared: Boolean?, forceCopy: Boolean?): IndicesArray?
    override fun setVerticesData(kind: String, data: FloatArray, updatable: Boolean)
    override fun setIndices(indices: IndicesArray, totalVertices: Number?, updatable: Boolean?)
    override fun isInFrustum(frustumPlanes: Array<Plane>): Boolean
    override fun isCompletelyInFrustum(frustumPlanes: Array<Plane>): Boolean
    override fun isVerticesDataPresent(kind: String): Boolean
    override fun updateVerticesData(kind: String, data: FloatArray, updateExtends: Boolean?, makeItUnique: Boolean?)
}
