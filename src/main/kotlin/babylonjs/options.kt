@file:Suppress("unused")
package babylonjs

/** Interface defining initialization parameters for Scene class */
external interface SceneOptions {
    /**
     * Defines that scene should keep up-to-date a map of geometry to enable fast look-up by uniqueId
     * It will improve performance when the number of geometries becomes important.
     */
    var useGeometryUniqueIdsMap: Boolean?

    /**
     * Defines that each material of the scene should keep up-to-date a map of referencing meshes for fast diposing
     * It will improve performance when the number of mesh becomes important, but might consume a bit more memory
     */
    var useMaterialMeshMap: Boolean?

    /**
     * Defines that each mesh of the scene should keep up-to-date a map of referencing cloned meshes for fast diposing
     * It will improve performance when the number of mesh becomes important, but might consume a bit more memory
     */
    var useClonedMeshMap: Boolean?

    /** Defines if the creation of the scene should impact the engine (Eg. UtilityLayer's scene) */
    var virtual: Boolean?
}

class BasicSceneOptions(
    override var useGeometryUniqueIdsMap: Boolean?,
    override var useMaterialMeshMap: Boolean?,
    override var useClonedMeshMap: Boolean?,
    override var virtual: Boolean?,
): SceneOptions

external interface BoxOptions {
    var size: Number?
    var width: Number?
    var height: Number?
    var depth: Number?
    var faceUV: Array<Vector4>?
    var faceColors: Array<Color4>?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
    var updatable: Boolean?
}

external interface SphereOptions {
    var segments: Number?
    var diameter: Number?
    var diameterX: Number?
    var diameterY: Number?
    var diameterZ: Number?
    var arc: Number?
    var slice: Number?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
    var updatable: Boolean?
}

external interface DiscOptions {
    var radius: Number?
    var tessellation: Number?
    var arc: Number?
    var updatable: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
}

external interface IcoSphereOptions {
    var radius: Number?
    var radiusX: Number?
    var radiusY: Number?
    var radiusZ: Number?
    var flat: Boolean?
    var subdivisions: Number?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
    var updatable: Boolean?
}

external interface RibbonOptions{
    var pathArray: Array<Array<Vector3>>?
    var closeArray: Boolean?
    var closePath: Boolean?
    var offset: Number?
    var updatable: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
    var instance: Mesh?
    var invertUV: Boolean?
    var uvs: Array<Vector2>?
    var colors: Array<Color4>?
}

external interface CylinderOptions{
    var height: Number?
    var diameterTop: Number?
    var diameterBottom: Number?
    var diameter: Number?
    var tessellation: Number?
    var subdivisions: Number?
    var arc: Number?
    var faceColors: Array<Color4>?
    var faceUV: Array<Vector4>?
    var updatable: Boolean?
    var hasRings: Boolean?
    var enclose: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
}

external interface TorusOptions{
    var diameter: Number?
    var thickness: Number?
    var tessellation: Number?
    var updatable: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
}

external interface TorusKnotOptions{
    var radius: Number?
    var tube: Number?
    var radialSegments: Number?
    var tubularSegments: Number?
    var p: Number?
    var q: Number?
    var updatable: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
}

external interface LineSystemOptions{
    var lines: Array<Array<Vector3>>?
    var updatable: Boolean?
    var instance: LinesMesh?
    var colors: Array<Array<Color4>>?
    var useVertexAlpha: Boolean?
}

external interface LinesOptions{
    var points: Array<Vector3>?
    var updatable: Boolean?
    var instance: LinesMesh?
    var colors: Array<Color4>?
    var useVertexAlpha: Boolean?
}

external interface DashedLinesOptions{
    var points: Array<Vector3>?
    var dashSize: Number?
    var gapSize: Number?
    var dashNb: Number?
    var updatable: Boolean?
    var instance: LinesMesh?
}

external interface ExtrudeOptions{
    var shape: Array<Vector3>?
    var path: Array<Vector3>?
    var scale: Number?
    var rotation: Number?
    var cap: Number?
    var updatable: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
    var instance: Mesh?
    var invertUV: Boolean?
}

external interface ExtrudeCustomOptions{
    var shape: Array<Vector3>?
    var path: Array<Vector3>?
    var scaleFunction: Any?
    var rotationFunction: Any?
    var ribbonCloseArray: Boolean?
    var ribbonClosePath: Boolean?
    var cap: Number?
    var updatable: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
    var instance: Mesh?
    var invertUV: Boolean?
}

external interface LatheOptions{
    var shape: Array<Vector3>?
    var radius: Number?
    var tessellation: Number?
    var clip: Number?
    var arc: Number?
    var closed: Boolean?
    var updatable: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
    var cap: Number?
    var invertUV: Boolean?
}

external interface PlaneOptions{
    var size: Number?
    var width: Number?
    var height: Number?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
    var updatable: Boolean?
    var sourcePlane: Plane?
}

external interface GroundOptions{
    var width: Number?
    var height: Number?
    var subdivisions: Number?
    var subdivisionsX: Number?
    var subdivisionsY: Number?
    var updatable: Boolean?
}

external interface SizeOptions{
    var w: Number?
    var h: Number?
}

external interface TiledGroundOptions{
    var xmin: Number?
    var zmin: Number?
    var xmax: Number?
    var zmax: Number?
    var subdivisions: SizeOptions?
    var precision: SizeOptions?
    var updatable: Boolean?
}

external interface GroundMapOptions{
    var width: Number?
    var height: Number?
    var subdivisions: Number?
    var minHeight: Number?
    var maxHeight: Number?
    var colorFilter: Color3?
    var alphaFilter: Number?
    var updatable: Boolean?
    var onReady: ((mesh: GroundMesh)-> Unit)?
}

external interface PolygonOptions{
    var shape: Array<Vector3>?
    var holes: Array<Array<Vector3>>?
    var depth: Number?
    var faceUV: Array<Vector4>?
    var faceColors: Array<Color4>?
    var updatable: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
}

external interface TubeOptions{
    var path: Array<Vector3>?
    var radius: Number?
    var tessellation: Number?
    var radiusFunction: ((i: Number, distance: Number) -> Number)?
    var cap: Number?
    var arc: Number?
    var updatable: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
    var instance: Mesh?
    var invertUV: Boolean?
}

external interface PolyhedronOptions{
    var type: Number?
    var size: Number?
    var sizeX: Number?
    var sizeY: Number?
    var sizeZ: Number?
    var custom: Any?
    var faceUV: Array<Vector4>?
    var faceColors: Array<Color4>?
    var flat: Boolean?
    var updatable: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
}

external interface DecalOptions{
    var position: Vector3?
    var normal: Vector3?
    var size: Vector3?
    var angle: Number?
}

external interface TextureOptions{
    var width: Number?
    var height: Number?
}

external interface DragBehaviorOptions{
    var dragAxis: Vector3?
    var dragPlaneNormal: Vector3?
}
