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

class BasicBoxOptions(
    override var size: Number? = null,
    override var width: Number? = null,
    override var height: Number? = null,
    override var depth: Number? = null,
    override var faceUV: Array<Vector4>? = null,
    override var faceColors: Array<Color4>? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
    override var updatable: Boolean? = null,
): BoxOptions

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

class BasicSphereOptions(
    override var segments: Number? = null,
    override var diameter: Number? = null,
    override var diameterX: Number? = null,
    override var diameterY: Number? = null,
    override var diameterZ: Number? = null,
    override var arc: Number? = null,
    override var slice: Number? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
    override var updatable: Boolean? = null,
): SphereOptions

external interface DiscOptions {
    var radius: Number?
    var tessellation: Number?
    var arc: Number?
    var updatable: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
}

class BasicDiscOptions(
    override var radius: Number? = null,
    override var tessellation: Number? = null,
    override var arc: Number? = null,
    override var updatable: Boolean? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
): DiscOptions

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

class BasicIcoSphereOptions (
    override var radius: Number? = null,
    override var radiusX: Number? = null,
    override var radiusY: Number? = null,
    override var radiusZ: Number? = null,
    override var flat: Boolean? = null,
    override var subdivisions: Number? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
    override var updatable: Boolean? = null,
): IcoSphereOptions

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

class BasicRibbonOptions(
    override var pathArray: Array<Array<Vector3>>? = null,
    override var closeArray: Boolean? = null,
    override var closePath: Boolean? = null,
    override var offset: Number? = null,
    override var updatable: Boolean? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
    override var instance: Mesh? = null,
    override var invertUV: Boolean? = null,
    override var uvs: Array<Vector2>? = null,
    override var colors: Array<Color4>? = null,
): RibbonOptions

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

class BasicCylinderOptions(
    override var height: Number? = null,
    override var diameterTop: Number? = null,
    override var diameterBottom: Number? = null,
    override var diameter: Number? = null,
    override var tessellation: Number? = null,
    override var subdivisions: Number? = null,
    override var arc: Number? = null,
    override var faceColors: Array<Color4>? = null,
    override var faceUV: Array<Vector4>? = null,
    override var updatable: Boolean? = null,
    override var hasRings: Boolean? = null,
    override var enclose: Boolean? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
): CylinderOptions

external interface TorusOptions{
    var diameter: Number?
    var thickness: Number?
    var tessellation: Number?
    var updatable: Boolean?
    var sideOrientation: Number?
    var frontUVs: Vector4?
    var backUVs: Vector4?
}

class BasicTorusOptions(
    override var diameter: Number? = null,
    override var thickness: Number? = null,
    override var tessellation: Number? = null,
    override var updatable: Boolean? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
): TorusOptions

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

class BasicTorusKnotOptions(
    override var radius: Number? = null,
    override var tube: Number? = null,
    override var radialSegments: Number? = null,
    override var tubularSegments: Number? = null,
    override var p: Number? = null,
    override var q: Number? = null,
    override var updatable: Boolean? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
): TorusKnotOptions

external interface LineSystemOptions{
    var lines: Array<Array<Vector3>>?
    var updatable: Boolean?
    var instance: LinesMesh?
    var colors: Array<Array<Color4>>?
    var useVertexAlpha: Boolean?
}

class BasicLineSystemOptions(
    override var lines: Array<Array<Vector3>>? = null,
    override var updatable: Boolean? = null,
    override var instance: LinesMesh? = null,
    override var colors: Array<Array<Color4>>? = null,
    override var useVertexAlpha: Boolean? = null,
): LineSystemOptions

external interface LinesOptions{
    var points: Array<Vector3>?
    var updatable: Boolean?
    var instance: LinesMesh?
    var colors: Array<Color4>?
    var useVertexAlpha: Boolean?
}

class BasicLinesOptions(
    override var points: Array<Vector3>? = null,
    override var updatable: Boolean? = null,
    override var instance: LinesMesh? = null,
    override var colors: Array<Color4>? = null,
    override var useVertexAlpha: Boolean? = null,
): LinesOptions

external interface DashedLinesOptions{
    var points: Array<Vector3>?
    var dashSize: Number?
    var gapSize: Number?
    var dashNb: Number?
    var updatable: Boolean?
    var instance: LinesMesh?
}

class BasicDashedLinesOptions(
    override var points: Array<Vector3>? = null,
    override var dashSize: Number? = null,
    override var gapSize: Number? = null,
    override var dashNb: Number? = null,
    override var updatable: Boolean? = null,
    override var instance: LinesMesh? = null,
): DashedLinesOptions

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

class BasicExtrudeOptions(
    override var shape: Array<Vector3>? = null,
    override var path: Array<Vector3>? = null,
    override var scale: Number? = null,
    override var rotation: Number? = null,
    override var cap: Number? = null,
    override var updatable: Boolean? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
    override var instance: Mesh? = null,
    override var invertUV: Boolean? = null,
): ExtrudeOptions

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

class BasicExtrudeCustomOptions(
    override var shape: Array<Vector3>? = null,
    override var path: Array<Vector3>? = null,
    override var scaleFunction: Any? = null,
    override var rotationFunction: Any? = null,
    override var ribbonCloseArray: Boolean? = null,
    override var ribbonClosePath: Boolean? = null,
    override var cap: Number? = null,
    override var updatable: Boolean? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
    override var instance: Mesh? = null,
    override var invertUV: Boolean? = null,
): ExtrudeCustomOptions

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

class BasicLatheOptions(
    override var shape: Array<Vector3>? = null,
    override var radius: Number? = null,
    override var tessellation: Number? = null,
    override var clip: Number? = null,
    override var arc: Number? = null,
    override var closed: Boolean? = null,
    override var updatable: Boolean? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
    override var cap: Number? = null,
    override var invertUV: Boolean? = null,
): LatheOptions

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

class BasicPlaneOptions(
    override var size: Number? = null,
    override var width: Number? = null,
    override var height: Number? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
    override var updatable: Boolean? = null,
    override var sourcePlane: Plane? = null,
): PlaneOptions

external interface GroundOptions{
    var width: Number?
    var height: Number?
    var subdivisions: Number?
    var subdivisionsX: Number?
    var subdivisionsY: Number?
    var updatable: Boolean?
}

class BasicGroundOptions(
    override var width: Number? = null,
    override var height: Number? = null,
    override var subdivisions: Number? = null,
    override var subdivisionsX: Number? = null,
    override var subdivisionsY: Number? = null,
    override var updatable: Boolean? = null,
): GroundOptions

external interface SizeOptions{
    var w: Number?
    var h: Number?
}

class BasicSizeOptions(
    override var w: Number? = null,
    override var h: Number? = null,
): SizeOptions

external interface TiledGroundOptions{
    var xmin: Number?
    var zmin: Number?
    var xmax: Number?
    var zmax: Number?
    var subdivisions: SizeOptions?
    var precision: SizeOptions?
    var updatable: Boolean?
}

class BasicTiledGroundOptions(
    override var xmin: Number? = null,
    override var zmin: Number? = null,
    override var xmax: Number? = null,
    override var zmax: Number? = null,
    override var subdivisions: SizeOptions? = null,
    override var precision: SizeOptions? = null,
    override var updatable: Boolean? = null,
): TiledGroundOptions

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

class BasicGroundMapOptions(
    override var width: Number? = null,
    override var height: Number? = null,
    override var subdivisions: Number? = null,
    override var minHeight: Number? = null,
    override var maxHeight: Number? = null,
    override var colorFilter: Color3? = null,
    override var alphaFilter: Number? = null,
    override var updatable: Boolean? = null,
    override var onReady: ((mesh: GroundMesh)-> Unit)? = null,
): GroundMapOptions

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

class BasicPolygonOptions(
    override var shape: Array<Vector3>? = null,
    override var holes: Array<Array<Vector3>>? = null,
    override var depth: Number? = null,
    override var faceUV: Array<Vector4>? = null,
    override var faceColors: Array<Color4>? = null,
    override var updatable: Boolean? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
): PolygonOptions

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

class BasicTubeOptions(
    override var path: Array<Vector3>? = null,
    override var radius: Number? = null,
    override var tessellation: Number? = null,
    override var radiusFunction: ((i: Number, distance: Number) -> Number)? = null,
    override var cap: Number? = null,
    override var arc: Number? = null,
    override var updatable: Boolean? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
    override var instance: Mesh? = null,
    override var invertUV: Boolean? = null,
): TubeOptions

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

class BasicPolyhedronOptions(
    override var type: Number? = null,
    override var size: Number? = null,
    override var sizeX: Number? = null,
    override var sizeY: Number? = null,
    override var sizeZ: Number? = null,
    override var custom: Any? = null,
    override var faceUV: Array<Vector4>? = null,
    override var faceColors: Array<Color4>? = null,
    override var flat: Boolean? = null,
    override var updatable: Boolean? = null,
    override var sideOrientation: Number? = null,
    override var frontUVs: Vector4? = null,
    override var backUVs: Vector4? = null,
): PolyhedronOptions

external interface DecalOptions{
    var position: Vector3?
    var normal: Vector3?
    var size: Vector3?
    var angle: Number?
}

class BasicDecalOptions(
    override var position: Vector3? = null,
    override var normal: Vector3? = null,
    override var size: Vector3? = null,
    override var angle: Number? = null,
): DecalOptions

external interface TextureOptions{
    var width: Number?
    var height: Number?
}

class BasicTextureOptions(
    override var width: Number? = null,
    override var height: Number? = null,
): TextureOptions

external interface DragBehaviorOptions{
    var dragAxis: Vector3?
    var dragPlaneNormal: Vector3?
}

class BasicDragBehaviorOptions(
    override var dragAxis: Vector3? = null,
    override var dragPlaneNormal: Vector3? = null,
): DragBehaviorOptions
