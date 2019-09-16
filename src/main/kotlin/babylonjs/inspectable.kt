@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals")
package babylonjs

/**
 * Enum that determines the text-wrapping mode to use.
 */
external enum class InspectableType {
    /**
     * Checkbox for booleans
     */
    Checkbox /*= 0*/,
    /**
     * Sliders for numbers
     */
    Slider /*= 1*/,
    /**
     * Vector3
     */
    Vector3 /*= 2*/,
    /**
     * Quaternions
     */
    Quaternion /*= 3*/,
    /**
     * Color3
     */
    Color3 /*= 4*/
}
/**
 * Interface used to define custom inspectable properties.
 * This interface is used by the inspector to display custom property grids
 * @see [https://doc.babylonjs.com/how_to/debug_layer#extensibility]
 */
external interface IInspectable {
    /**
     * Gets the label to display
     */
    val label: String
    /**
     * Gets the name of the property to edit
     */
    val propertyName: String
    /**
     * Gets the type of the editor to use
     */
    val type: InspectableType
    /**
     * Gets the minimum value of the property when using in "slider" mode
     */
    val min: Number?
    /**
     * Gets the maximum value of the property when using in "slider" mode
     */
    val max: Number?
    /**
     * Gets the setp to use when using in "slider" mode
     */
    val step: Number
}
