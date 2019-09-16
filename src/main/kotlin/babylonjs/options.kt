@file:Suppress("unused")
package babylonjs


/** Interface defining initialization parameters for Scene class */
class SceneOptions(
    /**
     * Defines that scene should keep up-to-date a map of geometry to enable fast look-up by uniqueId
     * It will improve performance when the number of geometries becomes important.
     */
    var useGeometryUniqueIdsMap: Boolean? = null,
    /**
     * Defines that each material of the scene should keep up-to-date a map of referencing meshes for fast diposing
     * It will improve performance when the number of mesh becomes important, but might consume a bit more memory
     */
    var useMaterialMeshMap: Boolean? = null,
    /**
     * Defines that each mesh of the scene should keep up-to-date a map of referencing cloned meshes for fast diposing
     * It will improve performance when the number of mesh becomes important, but might consume a bit more memory
     */
    var useClonedMeshMap: Boolean? = null,
    /** Defines if the creation of the scene should impact the engine (Eg. UtilityLayer's scene) */
    var virtual: Boolean? = null
)

class BoxOptions(
    var size: Number? = null,
    var width: Number? = null,
    var height: Number? = null,
    var depth: Number? = null,
    var faceUV: Array<Vector4>? = null,
    var faceColors: Array<Color4>? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4? = null,
    var updatable: Boolean? = null
)


class SphereOptions(
    var segments: Number? = null,
    var diameter: Number? = null,
    var diameterX: Number? = null,
    var diameterY: Number? = null,
    var diameterZ: Number? = null,
    var arc: Number? = null,
    var slice: Number? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4? = null,
    var updatable: Boolean? = null
)

class DiscOptions(
    var radius: Number? = null,
    var tessellation: Number? = null,
    var arc: Number? = null,
    var updatable: Boolean? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4?
)

class IcoSphereOptions(
    var radius: Number? = null,
    var radiusX: Number? = null,
    var radiusY: Number? = null,
    var radiusZ: Number? = null,
    var flat: Boolean? = null,
    var subdivisions: Number? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4? = null,
    var updatable: Boolean?
)

class RibbonOptions(
    var pathArray: Array<Array<Vector3>>,
    var closeArray: Boolean? = null,
    var closePath: Boolean? = null,
    var offset: Number? = null,
    var updatable: Boolean? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4? = null,
    var instance: Mesh? = null,
    var invertUV: Boolean? = null,
    var uvs: Array<Vector2>? = null,
    var colors: Array<Color4>?
)

class CylinderOptions(
    var height: Number? = null,
    var diameterTop: Number? = null,
    var diameterBottom: Number? = null,
    var diameter: Number? = null,
    var tessellation: Number? = null,
    var subdivisions: Number? = null,
    var arc: Number? = null,
    var faceColors: Array<Color4>? = null,
    var faceUV: Array<Vector4>? = null,
    var updatable: Boolean? = null,
    var hasRings: Boolean? = null,
    var enclose: Boolean? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4? = null
)

class TorusOptions(
    var diameter: Number? = null,
    var thickness: Number? = null,
    var tessellation: Number? = null,
    var updatable: Boolean? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4?
)

class TorusKnotOptions(
    var radius: Number? = null,
    var tube: Number? = null,
    var radialSegments: Number? = null,
    var tubularSegments: Number? = null,
    var p: Number? = null,
    var q: Number? = null,
    var updatable: Boolean? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4?
)

class LineSystemOptions(
    var lines: Array<Array<Vector3>>,
    var updatable: Boolean? = null,
    var instance: LinesMesh? = null,
    var colors: Array<Array<Color4>>? = null,
    var useVertexAlpha: Boolean?
)

class LinesOptions(
    var points: Array<Vector3>,
    var updatable: Boolean? = null,
    var instance: LinesMesh? = null,
    var colors: Array<Color4>? = null,
    var useVertexAlpha: Boolean?
)

class DashedLinesOptions(
    var points: Array<Vector3>,
    var dashSize: Number? = null,
    var gapSize: Number? = null,
    var dashNb: Number? = null,
    var updatable: Boolean? = null,
    var instance: LinesMesh?
)

class ExtrudeOptions(
    var shape: Array<Vector3>,
    var path: Array<Vector3>,
    var scale: Number? = null,
    var rotation: Number? = null,
    var cap: Number? = null,
    var updatable: Boolean? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4? = null,
    var instance: Mesh? = null,
    var invertUV: Boolean?
)

class ExtrudeCustomOptions(
    var shape: Array<Vector3>,
    var path: Array<Vector3>,
    var scaleFunction: Any? = null,
    var rotationFunction: Any? = null,
    var ribbonCloseArray: Boolean? = null,
    var ribbonClosePath: Boolean? = null,
    var cap: Number? = null,
    var updatable: Boolean? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4? = null,
    var instance: Mesh? = null,
    var invertUV: Boolean?
)

class LatheOptions(
    var shape: Array<Vector3>,
    var radius: Number? = null,
    var tessellation: Number? = null,
    var clip: Number? = null,
    var arc: Number? = null,
    var closed: Boolean? = null,
    var updatable: Boolean? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4? = null,
    var cap: Number? = null,
    var invertUV: Boolean?
)

class PlaneOptions(
    var size: Number? = null,
    var width: Number? = null,
    var height: Number? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4? = null,
    var updatable: Boolean? = null,
    var sourcePlane: Plane? = null
)

class GroundOptions(
    var width: Number? = null,
    var height: Number? = null,
    var subdivisions: Number? = null,
    var subdivisionsX: Number? = null,
    var subdivisionsY: Number? = null,
    var updatable: Boolean?
)

class SizeOptions(var w: Number, var h: Number)

class TiledGroundOptions(
    var xmin: Number,
    var zmin: Number,
    var xmax: Number,
    var zmax: Number,
    var subdivisions: SizeOptions? = null,
    var precision: SizeOptions? = null,
    var updatable: Boolean?
)

class GroundMapOptions(
    var width: Number? = null,
    var height: Number? = null,
    var subdivisions: Number? = null,
    var minHeight: Number? = null,
    var maxHeight: Number? = null,
    var colorFilter: Color3? = null,
    var alphaFilter: Number? = null,
    var updatable: Boolean? = null,
    var onReady: ((mesh: GroundMesh)-> Unit)?
)

class PolygonOptions(
    var shape: Array<Vector3>,
    var holes: Array<Array<Vector3>>,
    var depth: Number? = null,
    var faceUV: Array<Vector4>? = null,
    var faceColors: Array<Color4>? = null,
    var updatable: Boolean? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4?
)

class TubeOptions(
    var path: Array<Vector3>,
    var radius: Number? = null,
    var tessellation: Number? = null,
    var radiusFunction: ((i: Number, distance: Number) -> Number)? = null,
    var cap: Number? = null,
    var arc: Number? = null,
    var updatable: Boolean? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4? = null,
    var instance: Mesh? = null,
    var invertUV: Boolean?
)

class PolyhedronOptions(
    var type: Number? = null,
    var size: Number? = null,
    var sizeX: Number? = null,
    var sizeY: Number? = null,
    var sizeZ: Number? = null,
    var custom: Any? = null,
    var faceUV: Array<Vector4>? = null,
    var faceColors: Array<Color4>? = null,
    var flat: Boolean? = null,
    var updatable: Boolean? = null,
    var sideOrientation: Number? = null,
    var frontUVs: Vector4? = null,
    var backUVs: Vector4?
)

class DecalOptions(
    var position: Vector3? = null,
    var normal: Vector3? = null,
    var size: Vector3? = null,
    var angle: Number?
)

class TextureOptions(
    var width: Number? = null,
    var height: Number? = null
)

class DragBehaviorOptions(
    var dragAxis: Vector3? = null,
    var dragPlaneNormal: Vector3? = null
)
