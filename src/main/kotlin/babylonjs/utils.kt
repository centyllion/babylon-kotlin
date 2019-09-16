@file:Suppress("unused")
package babylonjs

// TODO some clever type definition are Any fornow
/**
 * Alias type for number that are floats
 * @ignorenaming
 */
typealias Float = Number
/**
 * Alias type for number that are doubles.
 * @ignorenaming
 */
typealias Double = Number
/**
 * Alias type for number that are integer
 * @ignorenaming
 */
typealias Int = Number
/** Alias type for number array or Float32Array */
typealias FloatArray = Array<Number> /*| Float32Array*/
/** Alias type for number array or Float32Array or Int32Array or Uint32Array or Uint16Array */
typealias IndicesArray = Array<Number> /*| Int32Array | Uint32Array | Uint16Array*/
/**
 * Alias for types that can be used by a Buffer or VertexBuffer.
 */
typealias DataArray = Array<Number> /*| ArrayBuffer | ArrayBufferView*/
/**
 * Alias type for primitive types
 * @ignorenaming
 */
//type Primitive = undefined | null | boolean | string | number | Function;

/**
 * Defines how a node can be built from a string name.
 */
typealias NodeConstructor = ((name: String, scene: Scene, options: Any?) -> Unit) -> Node

/**
 * Type used to define predicate used to select faces when a mesh intersection is detected
 */
typealias TrianglePickingPredicate = (p0: Vector3, p1: Vector3, p2: Vector3, ray: Ray) -> Boolean


interface MinMax {
    val minimum: Vector3
    val maximum: Vector3
}

interface ErrorObject {
    val message: String?
    val exception: Any?
}

interface ExtensionDescription {
    val isBinary: Boolean
}

interface ImportedMesh {
    val meshes: Array<AbstractMesh>
    val particleSystems: Array<IParticleSystem>
    val skeletons: Array<Skeleton>
    val animationGroups: Array<AnimationGroup>
}

interface DragStatus {
    val delta: Vector3
    val dragPlanePoint: Vector3
    val dragPlaneNormal: Vector3
    val dragDistance: Number
    val pointerId: Number
}

interface DragStartEnd {
    val dragPlanePoint: Vector3
    val pointerId: Number
}
