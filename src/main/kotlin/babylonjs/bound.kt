@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals", "FunctionName")
package babylonjs

/**
 * Interface for cullable objects
 * @see [https://doc.babylonjs.com/babylon101/materials#back-face-culling]
 */
external interface ICullable {
    /**
     * Checks if the object or part of the object is in the frustum
     * @param frustumPlanes Camera near/planes
     * @returns true if the object is in frustum otherwise false
     */
    fun isInFrustum(frustumPlanes: Array<Plane>): Boolean
    /**
     * Checks if a cullable object (mesh...) is in the camera frustum
     * Unlike isInFrustum this cheks the full bounding box
     * @param frustumPlanes Camera near/planes
     * @returns true if the object is in frustum otherwise false
     */
    fun isCompletelyInFrustum(frustumPlanes: Array<Plane>): Boolean
}

/**
 * Info for a bounding data of a mesh
 */
external class BoundingInfo: ICullable {
    override fun isCompletelyInFrustum(frustumPlanes: Array<Plane>): Boolean
    override fun isInFrustum(frustumPlanes: Array<Plane>): Boolean

    /**
     * Bounding box for the mesh
     */
    val boundingBox: BoundingBox
    /**
     * Bounding sphere for the mesh
     */
    val boundingSphere: BoundingSphere
    /**
     * Constructs bounding info
     * @param minimum min vector of the bounding box/sphere
     * @param maximum max vector of the bounding box/sphere
     * @param worldMatrix defines the new world matrix
     */
    constructor(minimum: Vector3, maximum: Vector3, worldMatrix: Matrix?)
    /**
     * Recreates the entire bounding info from scratch as if we call the constructor in place
     * @param min defines the new minimum vector (in local space)
     * @param max defines the new maximum vector (in local space)
     * @param worldMatrix defines the new world matrix
     */
    fun reConstruct(min: Vector3, max: Vector3, worldMatrix: Matrix?)
    /**
     * min vector of the bounding box/sphere
     */
    val minimum: Vector3
    /**
     * max vector of the bounding box/sphere
     */
    val maximum: Vector3
    /**
     * If the info is locked and won't be updated to avoid perf overhead
     */
    var isLocked: Boolean
    /**
     * Updates the bounding sphere and box
     * @param world world matrix to be used to update
     */
    fun update(world: Matrix)
    /**
     * Recreate the bounding info to be centered around a specific point given a specific extend.
     * @param center New center of the bounding info
     * @param extend New extend of the bounding info
     * @returns the current bounding info
     */
    fun centerOn(center: Vector3, extend: Vector3): BoundingInfo
    /**
     * Scale the current bounding info by applying a scale factor
     * @param factor defines the scale factor to apply
     * @returns the current bounding info
     */
    fun scale(factor: Number): BoundingInfo
    /**
     * Returns `true` if the bounding info is within the frustum defined by the passed array of planes.
     * @param frustumPlanes defines the frustum to test
     * @param strategy defines the strategy to use for the culling (default is BABYLON.AbstractMesh.CULLINGSTRATEGY_STANDARD)
     * @returns true if the bounding info is in the frustum planes
     */
    fun isInFrustum(frustumPlanes: Array<Plane>, strategy: Number?): Boolean
    /**
     * Gets the world distance between the min and max points of the bounding box
     */
    val diagonalLength: Number
    /**
     * Checks if a point is inside the bounding box and bounding sphere or the mesh
     * @see [https://doc.babylonjs.com/babylon101/intersect_collisions_-_mesh]
     * @param point the point to check intersection with
     * @returns if the point intersects
     */
    fun intersectsPoint(point: Vector3): Boolean
    /**
     * Checks if another bounding info intersects the bounding box and bounding sphere or the mesh
     * @see [https://doc.babylonjs.com/babylon101/intersect_collisions_-_mesh]
     * @param boundingInfo the bounding info to check intersection with
     * @param precise if the intersection should be done using OBB
     * @returns if the bounding info intersects
     */
    fun intersects(boundingInfo: BoundingInfo, precise: Boolean): Boolean
}

/**
 * Class used to store bounding box information
 */
external class BoundingBox: ICullable {
    override fun isInFrustum(frustumPlanes: Array<Plane>): Boolean

    override fun isCompletelyInFrustum(frustumPlanes: Array<Plane>): Boolean

    /**
     * Gets the 8 vectors representing the bounding box in local space
     */
    val vectors: Array<Vector3>
    /**
     * Gets the center of the bounding box in local space
     */
    val center: Vector3
    /**
     * Gets the center of the bounding box in world space
     */
    val centerWorld: Vector3
    /**
     * Gets the extend size in local space
     */
    val extendSize: Vector3
    /**
     * Gets the extend size in world space
     */
    val extendSizeWorld: Vector3
    /**
     * Gets the OBB (object bounding box) directions
     */
    val directions: Array<Vector3>
    /**
     * Gets the 8 vectors representing the bounding box in world space
     */
    val vectorsWorld: Array<Vector3>
    /**
     * Gets the minimum vector in world space
     */
    val minimumWorld: Vector3
    /**
     * Gets the maximum vector in world space
     */
    val maximumWorld: Vector3
    /**
     * Gets the minimum vector in local space
     */
    val minimum: Vector3
    /**
     * Gets the maximum vector in local space
     */
    val maximum: Vector3
    /**
     * Creates a new bounding box
     * @param min defines the minimum vector (in local space)
     * @param max defines the maximum vector (in local space)
     * @param worldMatrix defines the new world matrix
     */
    constructor(min: Vector3, max: Vector3, worldMatrix: Matrix?)
    /**
     * Recreates the entire bounding box from scratch as if we call the constructor in place
     * @param min defines the new minimum vector (in local space)
     * @param max defines the new maximum vector (in local space)
     * @param worldMatrix defines the new world matrix
     */
    fun reConstruct(min: Vector3, max: Vector3, worldMatrix: Matrix?)
    /**
     * Scale the current bounding box by applying a scale factor
     * @param factor defines the scale factor to apply
     * @returns the current bounding box
     */
    fun scale(factor: Number): BoundingBox
    /**
     * Gets the world matrix of the bounding box
     * @returns a matrix
     */
    fun getWorldMatrix(): Matrix
    /**
     * Tests if a point is inside the bounding box
     * @param point defines the point to test
     * @returns true if the point is inside the bounding box
     */
    fun intersectsPoint(point: Vector3): Boolean
    /**
     * Tests if the bounding box intersects with a bounding sphere
     * @param sphere defines the sphere to test
     * @returns true if there is an intersection
     */
    fun intersectsSphere(sphere: BoundingSphere): Boolean
    /**
     * Tests if the bounding box intersects with a box defined by a min and max vectors
     * @param min defines the min vector to use
     * @param max defines the max vector to use
     * @returns true if there is an intersection
     */
    fun intersectsMinMax(min: Vector3, max: Vector3): Boolean
    
    companion object {
        /**
         * Tests if two bounding boxes are intersections
         * @param box0 defines the first box to test
         * @param box1 defines the second box to test
         * @returns true if there is an intersection
         */
        fun Intersects(box0: BoundingBox, box1: BoundingBox): Boolean
        /**
         * Tests if a bounding box defines by a min/max vectors intersects a sphere
         * @param minPoint defines the minimum vector of the bounding box
         * @param maxPoint defines the maximum vector of the bounding box
         * @param sphereCenter defines the sphere center
         * @param sphereRadius defines the sphere radius
         * @returns true if there is an intersection
         */
        fun IntersectsSphere(minPoint: Vector3, maxPoint: Vector3, sphereCenter: Vector3, sphereRadius: Number): Boolean
        /**
         * Tests if a bounding box defined with 8 vectors is entirely inside frustum planes
         * @param boundingVectors defines an array of 8 vectors representing a bounding box
         * @param frustumPlanes defines the frustum planes to test
         * @return true if there is an inclusion
         */
        fun IsCompletelyInFrustum(boundingVectors: Array<Vector3>, frustumPlanes: Array<Plane>): Boolean
        /**
         * Tests if a bounding box defined with 8 vectors intersects frustum planes
         * @param boundingVectors defines an array of 8 vectors representing a bounding box
         * @param frustumPlanes defines the frustum planes to test
         * @return true if there is an intersection
         */
        fun IsInFrustum(boundingVectors: Array<Vector3>, frustumPlanes: Array<Plane>): Boolean
    }
}

/**
 * Class used to store bounding sphere information
 */
external class BoundingSphere {
    /**
     * Gets the center of the bounding sphere in local space
     */
    val center: Vector3
    /**
     * Radius of the bounding sphere in local space
     */
    var radius: Number
    /**
     * Gets the center of the bounding sphere in world space
     */
    val centerWorld: Vector3
    /**
     * Radius of the bounding sphere in world space
     */
    var radiusWorld: Number
    /**
     * Gets the minimum vector in local space
     */
    val minimum: Vector3
    /**
     * Gets the maximum vector in local space
     */
    val maximum: Vector3
    /**
     * Creates a new bounding sphere
     * @param min defines the minimum vector (in local space)
     * @param max defines the maximum vector (in local space)
     * @param worldMatrix defines the new world matrix
     */
    constructor(min: Vector3, max: Vector3, worldMatrix: Matrix?)
    /**
     * Recreates the entire bounding sphere from scratch as if we call the constructor in place
     * @param min defines the new minimum vector (in local space)
     * @param max defines the new maximum vector (in local space)
     * @param worldMatrix defines the new world matrix
     */
    fun reConstruct(min: Vector3, max: Vector3, worldMatrix: Matrix?)
    /**
     * Scale the current bounding sphere by applying a scale factor
     * @param factor defines the scale factor to apply
     * @returns the current bounding box
     */
    fun scale(factor: Number): BoundingSphere
    /**
     * Gets the world matrix of the bounding box
     * @returns a matrix
     */
    fun getWorldMatrix(): Matrix
    /**
     * Tests if the bounding sphere is intersecting the frustum planes
     * @param frustumPlanes defines the frustum planes to test
     * @returns true if there is an intersection
     */
    fun isInFrustum(frustumPlanes: Array<Plane>): Boolean
    /**
     * Tests if the bounding sphere center is in between the frustum planes.
     * Used for optimistic fast inclusion.
     * @param frustumPlanes defines the frustum planes to test
     * @returns true if the sphere center is in between the frustum planes
     */
    fun isCenterInFrustum(frustumPlanes: Array<Plane>): Boolean
    /**
     * Tests if a point is inside the bounding sphere
     * @param point defines the point to test
     * @returns true if the point is inside the bounding sphere
     */
    fun intersectsPoint(point: Vector3): Boolean
    
    companion object {
        /**
         * Checks if two sphere intersct
         * @param sphere0 sphere 0
         * @param sphere1 sphere 1
         * @returns true if the speres intersect
         */
        fun Intersects(sphere0: BoundingSphere, sphere1: BoundingSphere): Boolean
    }
}
