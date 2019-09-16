@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals", "FunctionName")
package babylonjs

/**
 * Class representing a ray with position and direction
 */
external class Ray {
    /** origin point */
    var origin: Vector3
    /** direction */
    var direction: Vector3
    /** length of the ray */
    var length: Number
    /**
     * Creates a new ray
     * @param origin origin point
     * @param direction direction
     * @param length length of the ray
     */
    constructor(
        /** origin point */
        origin: Vector3,
        /** direction */
        direction: Vector3,
        /** length of the ray */
        length: Number?)
    /**
     * Checks if the ray intersects a box
     * @param minimum bound of the box
     * @param maximum bound of the box
     * @param intersectionTreshold extra extend to be added to the box in all direction
     * @returns if the box was hit
     */
    fun intersectsBoxMinMax(minimum: Vector3, maximum: Vector3, intersectionTreshold: Number? = definedExternally): Boolean
    /**
     * Checks if the ray intersects a box
     * @param box the bounding box to check
     * @param intersectionTreshold extra extend to be added to the BoundingBox in all direction
     * @returns if the box was hit
     */
    fun intersectsBox(box: BoundingBox, intersectionTreshold: Number? = definedExternally): Boolean
    /**
     * If the ray hits a sphere
     * @param sphere the bounding sphere to check
     * @param intersectionTreshold extra extend to be added to the BoundingSphere in all direction
     * @returns true if it hits the sphere
     */
    fun intersectsSphere(sphere: BoundingSphere, intersectionTreshold: Number? = definedExternally): Boolean
    /**
     * If the ray hits a triange
     * @param vertex0 triangle vertex
     * @param vertex1 triangle vertex
     * @param vertex2 triangle vertex
     * @returns intersection information if hit
     */
    fun intersectsTriangle(vertex0: Vector3, vertex1: Vector3, vertex2: Vector3): IntersectionInfo?
    /**
     * Checks if ray intersects a plane
     * @param plane the plane to check
     * @returns the distance away it was hit
     */
    fun intersectsPlane(plane: Plane): Number?
    /**
     * Checks if ray intersects a mesh
     * @param mesh the mesh to check
     * @param fastCheck if only the bounding box should checked
     * @returns picking info of the intersecton
     */
    fun intersectsMesh(mesh: AbstractMesh, fastCheck: Boolean? = definedExternally): PickingInfo
    /**
     * Checks if ray intersects a mesh
     * @param meshes the meshes to check
     * @param fastCheck if only the bounding box should checked
     * @param results array to store result in
     * @returns Array of picking infos
     */
    fun intersectsMeshes(meshes: Array<AbstractMesh>, fastCheck: Boolean = definedExternally, results: Array<PickingInfo>? = definedExternally): Array<PickingInfo>
    /**
     * Intersection test between the ray and a given segment whithin a given tolerance (threshold)
     * @param sega the first point of the segment to test the intersection against
     * @param segb the second point of the segment to test the intersection against
     * @param threshold the tolerance margin, if the ray doesn't intersect the segment but is close to the given threshold, the intersection is successful
     * @return the distance from the ray origin to the intersection point if there's intersection, or -1 if there's no intersection
     */
    fun intersectionSegment(sega: Vector3, segb: Vector3, threshold: Number): Number
    /**
     * Update the ray from viewport position
     * @param x position
     * @param y y position
     * @param viewportWidth viewport width
     * @param viewportHeight viewport height
     * @param world world matrix
     * @param view view matrix
     * @param projection projection matrix
     * @returns this ray updated
     */
    fun update(x: Number, y: Number, viewportWidth: Number, viewportHeight: Number, world: Matrix, view: Matrix, projection: Matrix): Ray

    /**
     * Unproject a ray from screen space to object space
     * @param sourceX defines the screen space x coordinate to use
     * @param sourceY defines the screen space y coordinate to use
     * @param viewportWidth defines the current width of the viewport
     * @param viewportHeight defines the current height of the viewport
     * @param world defines the world matrix to use (can be set to Identity to go to world space)
     * @param view defines the view matrix to use
     * @param projection defines the projection matrix to use
     */
    fun unprojectRayToRef(sourceX: Float, sourceY: Float, viewportWidth: Number, viewportHeight: Number, world: Matrix, view: Matrix, projection: Matrix)
    
    companion object {
        /**
         * Creates a ray with origin and direction of 0,0,0
         * @returns the new ray
         */
        fun Zero(): Ray
        /**
         * Creates a new ray from screen space and viewport
         * @param x position
         * @param y y position
         * @param viewportWidth viewport width
         * @param viewportHeight viewport height
         * @param world world matrix
         * @param view view matrix
         * @param projection projection matrix
         * @returns new ray
         */
        fun CreateNew(x: Number, y: Number, viewportWidth: Number, viewportHeight: Number, world: Matrix, view: Matrix, projection: Matrix): Ray
        /**
         * Function will create a new transformed ray starting from origin and ending at the end point. Ray's length will be set, and ray will be
         * transformed to the given world matrix.
         * @param origin The origin point
         * @param end The end point
         * @param world a matrix to transform the ray to. Default is the identity matrix.
         * @returns the new ray
         */
        fun CreateNewFromTo(origin: Vector3, end: Vector3, world: Matrix?): Ray
        /**
         * Transforms a ray by a matrix
         * @param ray ray to transform
         * @param matrix matrix to apply
         * @returns the resulting new ray
         */
        fun Transform(ray: Ray, matrix: Matrix): Ray
        /**
         * Transforms a ray by a matrix
         * @param ray ray to transform
         * @param matrix matrix to apply
         * @param result ray to store result in
         */
        fun TransformToRef(ray: Ray, matrix: Matrix, result: Ray)
    }
}

external class IntersectionInfo {
    val bu: Number
    val bv: Number
    val distance: Number
    val faceId: Number
    val subMeshId: Number
    constructor(bu: Number?, bv: Number?, distance: Number);
}
