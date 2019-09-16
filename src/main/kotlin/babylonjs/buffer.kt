@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals")
package babylonjs

/**
 * Class used to store gfx data (like WebGLBuffer)
 */
external class DataBuffer {
    /**
     * Gets or sets the number of objects referencing this buffer
     */
    var references: Number
    /** Gets or sets the size of the underlying buffer */
    var capacity: Number
    /**
     * Gets or sets a boolean indicating if the buffer contains 32bits indices
     */
    var is32Bits: Boolean
    /**
     * Gets the underlying buffer
     */
    val underlyingResource: Any
}
