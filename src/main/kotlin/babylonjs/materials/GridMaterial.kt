@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals", "FunctionName", "PropertyName")
@file:JsModule("babylonjs-materials")
package babylonjs.materials

import babylonjs.AbstractMesh
import babylonjs.Color3
import babylonjs.PushMaterial
import babylonjs.Scene
import babylonjs.SubMesh
import babylonjs.Vector3

external class GridMaterial: PushMaterial {
    /**
     * Main color of the grid (e.g. between lines)
     */
    var mainColor: Color3
    /**
     * Color of the grid lines.
     */
    var lineColor: Color3
    /**
     * The scale of the grid compared to unit.
     */
    var gridRatio: Number
    /**
     * Allows setting an offset for the grid lines.
     */
    var gridOffset: Vector3
    /**
     * The frequency of thicker lines.
     */
    var majorUnitFrequency: Number
    /**
     * The visibility of minor units in the grid.
     */
    var minorUnitVisibility: Number
    /**
     * The grid opacity outside of the lines.
     */
    var opacity: Number
    /**
     * Determine RBG output is premultiplied by alpha value.
     */
    var preMultiplyAlpha: Boolean
    /**
     * constructor
     * @param name The name given to the material in order to identify it afterwards.
     * @param scene The scene the material is used in.
     */
    constructor(name: String, scene: Scene)
    
    fun isReadyForSubMesh(mesh: AbstractMesh, subMesh: SubMesh, useInstances: Boolean?): Boolean
    fun dispose(forceDisposeEffect: Boolean?)

    companion object {
        fun Parse(source: Any, scene: Scene, rootUrl: String): GridMaterial
    }
}
