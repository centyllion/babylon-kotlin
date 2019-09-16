@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals", "FunctionName")
package babylonjs

import org.khronos.webgl.Float32Array

/**
 * Scalar computation library
 */
external class Scalar {
    companion object {
        /**
         * Two pi constants convenient for computation.
         */
        val TwoPi: Number
        /**
         * Boolean : true if the absolute difference between a and b is lower than epsilon (default = 1.401298E-45)
         * @param a Number
         * @param b Number
         * @param epsilon (default = 1.401298E-45)
         * @returns true if the absolute difference between a and b is lower than epsilon (default = 1.401298E-45)
         */
        fun WithinEpsilon(a: Number, b: Number, epsilon: Number?): Boolean
        /**
         * Returns a String : the upper case translation of the Number i to hexadecimal.
         * @param i Number
         * @returns the upper case translation of the Number i to hexadecimal.
         */
        fun ToHex(i: Number): String
        /**
         * Returns -1 if value is negative and +1 is value is positive.
         * @param value the value
         * @returns the value itself if it's equal to zero.
         */
        fun Sign(value: Number): Number
        /**
         * Returns the value itself if it's between min and max.
         * Returns min if the value is lower than min.
         * Returns max if the value is greater than max.
         * @param value the value to clmap
         * @param min the min value to clamp to (default: 0)
         * @param max the max value to clamp to (default: 1)
         * @returns the clamped value
         */
        fun Clamp(value: Number, min: Number?, max: Number?): Number
        /**
         * the log2 of value.
         * @param value the value to compute log2 of
         * @returns the log2 of value.
         */
        fun Log2(value: Number): Number
        /**
         * Loops the value, so that it is never larger than length and never smaller than 0.
         *
         * This is similar to the modulo operator but it works with floating point numbers.
         * For example, using 3.0 for t and 2.5 for length, the result would be 0.5.
         * With t = 5 and length = 2.5, the result would be 0.0.
         * Note, however, that the behaviour is not defined for negative numbers as it is for the modulo operator
         * @param value the value
         * @param length the length
         * @returns the looped value
         */
        fun Repeat(value: Number, length: Number): Number
        /**
         * Normalize the value between 0.0 and 1.0 using min and max values
         * @param value value to normalize
         * @param min max to normalize between
         * @param max min to normalize between
         * @returns the normalized value
         */
        fun Normalize(value: Number, min: Number, max: Number): Number
        /**
         * Denormalize the value from 0.0 and 1.0 using min and max values
         * @param normalized value to denormalize
         * @param min max to denormalize between
         * @param max min to denormalize between
         * @returns the denormalized value
         */
        fun Denormalize(normalized: Number, min: Number, max: Number): Number
        /**
         * Calculates the shortest difference between two given angles given in degrees.
         * @param current current angle in degrees
         * @param target target angle in degrees
         * @returns the delta
         */
        fun DeltaAngle(current: Number, target: Number): Number
        /**
         * PingPongs the value t, so that it is never larger than length and never smaller than 0.
         * @param tx value
         * @param length length
         * @returns The returned value will move back and forth between 0 and length
         */
        fun PingPong(tx: Number, length: Number): Number
        /**
         * Interpolates between min and max with smoothing at the limits.
         *
         * This function interpolates between min and max in a similar way to Lerp. However, the interpolation will gradually speed up
         * from the start and slow down toward the end. This is useful for creating natural-looking animation, fading and other transitions.
         * @param from from
         * @param to to
         * @param tx value
         * @returns the smooth stepped value
         */
        fun SmoothStep(from: Number, to: Number, tx: Number): Number
        /**
         * Moves a value current towards target.
         *
         * This is essentially the same as Mathf.Lerp but instead the function will ensure that the speed never exceeds maxDelta.
         * Negative values of maxDelta pushes the value away from target.
         * @param current current value
         * @param target target value
         * @param maxDelta max distance to move
         * @returns resulting value
         */
        fun MoveTowards(current: Number, target: Number, maxDelta: Number): Number
        /**
         * Same as MoveTowards but makes sure the values interpolate correctly when they wrap around 360 degrees.
         *
         * Variables current and target are assumed to be in degrees. For optimization reasons, negative values of maxDelta
         *  are not supported and may cause oscillation. To push current away from a target angle, add 180 to that angle instead.
         * @param current current value
         * @param target target value
         * @param maxDelta max distance to move
         * @returns resulting angle
         */
        fun MoveTowardsAngle(current: Number, target: Number, maxDelta: Number): Number
        /**
         * Creates a new scalar with values linearly interpolated of "amount" between the start scalar and the end scalar.
         * @param start start value
         * @param end target value
         * @param amount amount to lerp between
         * @returns the lerped value
         */
        fun Lerp(start: Number, end: Number, amount: Number): Number
        /**
         * Same as Lerp but makes sure the values interpolate correctly when they wrap around 360 degrees.
         * The parameter t is clamped to the range [0, 1]. Variables a and b are assumed to be in degrees.
         * @param start start value
         * @param end target value
         * @param amount amount to lerp between
         * @returns the lerped value
         */
        fun LerpAngle(start: Number, end: Number, amount: Number): Number
        /**
         * Calculates the linear parameter t that produces the interpolant value within the range [a, b].
         * @param a start value
         * @param b target value
         * @param value value between a and b
         * @returns the inverseLerp value
         */
        fun InverseLerp(a: Number, b: Number, value: Number): Number
        /**
         * Returns a new scalar located for "amount" (float) on the Hermite spline defined by the scalars "value1", "value3", "tangent1", "tangent2".
         * @see http://mathworld.wolfram.com/HermitePolynomial.html
         * @param value1 spline value
         * @param tangent1 spline value
         * @param value2 spline value
         * @param tangent2 spline value
         * @param amount input value
         * @returns hermite result
         */
        fun Hermite(value1: Number, tangent1: Number, value2: Number, tangent2: Number, amount: Number): Number
        /**
         * Returns a random float Number between and min and max values
         * @param min min value of random
         * @param max max value of random
         * @returns random value
         */
        fun RandomRange(min: Number, max: Number): Number
        /**
         * This function returns percentage of a Number in a given range.
         *
         * RangeToPercent(40,20,60) will return 0.5 (50%)
         * RangeToPercent(34,0,100) will return 0.34 (34%)
         * @param Number to convert to percentage
         * @param min min range
         * @param max max range
         * @returns the percentage
         */
        fun RangeToPercent(Number: Number, min: Number, max: Number): Number
        /**
         * This function returns Number that corresponds to the percentage in a given range.
         *
         * PercentToRange(0.34,0,100) will return 34.
         * @param percent to convert to Number
         * @param min min range
         * @param max max range
         * @returns the Number
         */
        fun PercentToRange(percent: Number, min: Number, max: Number): Number
        /**
         * Returns the angle converted to equivalent value between -Math.PI and Math.PI radians.
         * @param angle The angle to normalize in radian.
         * @return The converted angle.
         */
        fun NormalizeRadians(angle: Number): Number
    }
}

/**
 * Constant used to convert a value to gamma space
 * @ignorenaming
 */
external val ToGammaSpace: Number = definedExternally
/**
 * Constant used to convert a value to linear space
 * @ignorenaming
 */
external val ToLinearSpace: Number = definedExternally
/**
 * Constant used to define the minimal Number value in Babylon.js
 * @ignorenaming
 */
external val Epsilon: Number = definedExternally
/**
 * Class used to hold a RBG color
 */
external class Color3 {
    /**
     * Defines the red component (between 0 and 1, default is 0)
     */
    val r: Number
    /**
     * Defines the green component (between 0 and 1, default is 0)
     */
    val g: Number
    /**
     * Defines the blue component (between 0 and 1, default is 0)
     */
    val b: Number
    /**
     * Creates a new Color3 object from red, green, blue values, all between 0 and 1
     * @param r defines the red component (between 0 and 1, default is 0)
     * @param g defines the green component (between 0 and 1, default is 0)
     * @param b defines the blue component (between 0 and 1, default is 0)
     */
    constructor(
        /**
         * Defines the red component (between 0 and 1, default is 0)
         */
        r: Number?,
        /**
         * Defines the green component (between 0 and 1, default is 0)
         */
        g: Number?,
        /**
         * Defines the blue component (between 0 and 1, default is 0)
         */
        b: Number?)
    /**
     * Returns the String "Color3"
     * @returns "Color3"
     */
    fun getClassName(): String
    /**
     * Compute the Color3 hash code
     * @returns an unique Number that can be used to hash Color3 objects
     */
    fun getHashCode(): Number
    /**
     * Stores in the given array from the given starting index the red, green, blue values as successive elements
     * @param array defines the array where to store the r,g,b components
     * @param index defines an optional index in the target array to define where to start storing values
     * @returns the current Color3 object
     */
    fun toArray(array: FloatArray, index: Number?): Color3
    /**
     * Returns a new Color4 object from the current Color3 and the given alpha
     * @param alpha defines the alpha component on the new Color4 object (default is 1)
     * @returns a new Color4 object
     */
    fun toColor4(alpha: Number?): Color4
    /**
     * Returns a new array populated with 3 numeric elements : red, green and blue values
     * @returns the new array
     */
    fun asArray(): Array<Number>
    /**
     * Returns the luminance value
     * @returns a float value
     */
    fun toLuminance(): Number
    /**
     * Multiply each Color3 rgb values by the given Color3 rgb values in a new Color3 object
     * @param otherColor defines the second operand
     * @returns the new Color3 object
     */
    fun multiply(otherColor: Color3): Color3
    /**
     * Multiply the rgb values of the Color3 and the given Color3 and stores the result in the object "result"
     * @param otherColor defines the second operand
     * @param result defines the Color3 object where to store the result
     * @returns the current Color3
     */
    fun multiplyToRef(otherColor: Color3, result: Color3): Color3
    /**
     * Determines equality between Color3 objects
     * @param otherColor defines the second operand
     * @returns true if the rgb values are equal to the given ones
     */
    fun equals(otherColor: Color3): Boolean
    /**
     * Determines equality between the current Color3 object and a set of r,b,g values
     * @param r defines the red component to check
     * @param g defines the green component to check
     * @param b defines the blue component to check
     * @returns true if the rgb values are equal to the given ones
     */
    fun equalsFloats(r: Number, g: Number, b: Number): Boolean
    /**
     * Multiplies in place each rgb value by scale
     * @param scale defines the scaling factor
     * @returns the updated Color3
     */
    fun scale(scale: Number): Color3
    /**
     * Multiplies the rgb values by scale and stores the result into "result"
     * @param scale defines the scaling factor
     * @param result defines the Color3 object where to store the result
     * @returns the unmodified current Color3
     */
    fun scaleToRef(scale: Number, result: Color3): Color3
    /**
     * Scale the current Color3 values by a factor and add the result to a given Color3
     * @param scale defines the scale factor
     * @param result defines color to store the result into
     * @returns the unmodified current Color3
     */
    fun scaleAndAddToRef(scale: Number, result: Color3): Color3
    /**
     * Clamps the rgb values by the min and max values and stores the result into "result"
     * @param min defines minimum clamping value (default is 0)
     * @param max defines maximum clamping value (default is 1)
     * @param result defines color to store the result into
     * @returns the original Color3
     */
    fun clampToRef(min: Number?, max: Number?, result: Color3): Color3
    /**
     * Creates a new Color3 set with the added values of the current Color3 and of the given one
     * @param otherColor defines the second operand
     * @returns the new Color3
     */
    fun add(otherColor: Color3): Color3
    /**
     * Stores the result of the addition of the current Color3 and given one rgb values into "result"
     * @param otherColor defines the second operand
     * @param result defines Color3 object to store the result into
     * @returns the unmodified current Color3
     */
    fun addToRef(otherColor: Color3, result: Color3): Color3
    /**
     * Returns a new Color3 set with the subtracted values of the given one from the current Color3
     * @param otherColor defines the second operand
     * @returns the new Color3
     */
    fun subtract(otherColor: Color3): Color3
    /**
     * Stores the result of the subtraction of given one from the current Color3 rgb values into "result"
     * @param otherColor defines the second operand
     * @param result defines Color3 object to store the result into
     * @returns the unmodified current Color3
     */
    fun subtractToRef(otherColor: Color3, result: Color3): Color3
    /**
     * Copy the current object
     * @returns a new Color3 copied the current one
     */
    fun clone(): Color3
    /**
     * Copies the rgb values from the source in the current Color3
     * @param source defines the source Color3 object
     * @returns the updated Color3 object
     */
    fun copyFrom(source: Color3): Color3
    /**
     * Updates the Color3 rgb values from the given floats
     * @param r defines the red component to read from
     * @param g defines the green component to read from
     * @param b defines the blue component to read from
     * @returns the current Color3 object
     */
    fun copyFromFloats(r: Number, g: Number, b: Number): Color3
    /**
     * Updates the Color3 rgb values from the given floats
     * @param r defines the red component to read from
     * @param g defines the green component to read from
     * @param b defines the blue component to read from
     * @returns the current Color3 object
     */
    fun set(r: Number, g: Number, b: Number): Color3
    /**
     * Compute the Color3 hexadecimal code as a String
     * @returns a String containing the hexadecimal representation of the Color3 object
     */
    fun toHexString(): String
    /**
     * Computes a new Color3 converted from the current one to linear space
     * @returns a new Color3 object
     */
    fun toLinearSpace(): Color3
    /**
     * Converts the Color3 values to linear space and stores the result in "convertedColor"
     * @param convertedColor defines the Color3 object where to store the linear space version
     * @returns the unmodified Color3
     */
    fun toLinearSpaceToRef(convertedColor: Color3): Color3
    /**
     * Computes a new Color3 converted from the current one to gamma space
     * @returns a new Color3 object
     */
    fun toGammaSpace(): Color3
    /**
     * Converts the Color3 values to gamma space and stores the result in "convertedColor"
     * @param convertedColor defines the Color3 object where to store the gamma space version
     * @returns the unmodified Color3
     */
    fun toGammaSpaceToRef(convertedColor: Color3): Color3

    companion object {
        /**
         * Creates a new Color3 from the String containing valid hexadecimal values
         * @param hex defines a String containing valid hexadecimal values
         * @returns a new Color3 object
         */
        fun FromHexString(hex: String): Color3
        /**
         * Creates a new Color3 from the starting index of the given array
         * @param array defines the source array
         * @param offset defines an offset in the source array
         * @returns a new Color3 object
         */
        fun FromArray(array: Collection<Number>, offset: Number?): Color3
        /**
         * Creates a new Color3 from integer values (< 256)
         * @param r defines the red component to read from (value between 0 and 255)
         * @param g defines the green component to read from (value between 0 and 255)
         * @param b defines the blue component to read from (value between 0 and 255)
         * @returns a new Color3 object
         */
        fun FromInts(r: Number, g: Number, b: Number): Color3
        /**
         * Creates a new Color3 with values linearly interpolated of "amount" between the start Color3 and the end Color3
         * @param start defines the start Color3 value
         * @param end defines the end Color3 value
         * @param amount defines the gradient value between start and end
         * @returns a new Color3 object
         */
        fun Lerp(start: Color3, end: Color3, amount: Number): Color3
        /**
         * Creates a new Color3 with values linearly interpolated of "amount" between the start Color3 and the end Color3
         * @param left defines the start value
         * @param right defines the end value
         * @param amount defines the gradient factor
         * @param result defines the Color3 object where to store the result
         */
        fun LerpToRef(left: Color3, right: Color3, amount: Number, result: Color3)
        /**
         * Returns a Color3 value containing a red color
         * @returns a new Color3 object
         */
        fun Red(): Color3
        /**
         * Returns a Color3 value containing a green color
         * @returns a new Color3 object
         */
        fun Green(): Color3
        /**
         * Returns a Color3 value containing a blue color
         * @returns a new Color3 object
         */
        fun Blue(): Color3
        /**
         * Returns a Color3 value containing a black color
         * @returns a new Color3 object
         */
        fun Black(): Color3
        /**
         * Gets a Color3 value containing a black color that must not be updated
         */
        val BlackReadOnly: Color3
        /**
         * Returns a Color3 value containing a white color
         * @returns a new Color3 object
         */
        fun White(): Color3
        /**
         * Returns a Color3 value containing a purple color
         * @returns a new Color3 object
         */
        fun Purple(): Color3
        /**
         * Returns a Color3 value containing a magenta color
         * @returns a new Color3 object
         */
        fun Magenta(): Color3
        /**
         * Returns a Color3 value containing a yellow color
         * @returns a new Color3 object
         */
        fun Yellow(): Color3
        /**
         * Returns a Color3 value containing a gray color
         * @returns a new Color3 object
         */
        fun Gray(): Color3
        /**
         * Returns a Color3 value containing a teal color
         * @returns a new Color3 object
         */
        fun Teal(): Color3
        /**
         * Returns a Color3 value containing a random color
         * @returns a new Color3 object
         */
        fun Random(): Color3
    }
}
/**
 * Class used to hold a RBGA color
 */
external class Color4 {
    /**
     * Defines the red component (between 0 and 1, default is 0)
     */
    val r: Number
    /**
     * Defines the green component (between 0 and 1, default is 0)
     */
    val g: Number
    /**
     * Defines the blue component (between 0 and 1, default is 0)
     */
    val b: Number
    /**
     * Defines the alpha component (between 0 and 1, default is 1)
     */
    val a: Number
    /**
     * Creates a new Color4 object from red, green, blue values, all between 0 and 1
     * @param r defines the red component (between 0 and 1, default is 0)
     * @param g defines the green component (between 0 and 1, default is 0)
     * @param b defines the blue component (between 0 and 1, default is 0)
     * @param a defines the alpha component (between 0 and 1, default is 1)
     */
    constructor(
        /**
         * Defines the red component (between 0 and 1, default is 0)
         */
        r: Number?,
        /**
         * Defines the green component (between 0 and 1, default is 0)
         */
        g: Number?,
        /**
         * Defines the blue component (between 0 and 1, default is 0)
         */
        b: Number?,
        /**
         * Defines the alpha component (between 0 and 1, default is 1)
         */
        a: Number?)
    /**
     * Adds in place the given Color4 values to the current Color4 object
     * @param right defines the second operand
     * @returns the current updated Color4 object
     */
    fun addInPlace(right: Color4): Color4
    /**
     * Creates a new array populated with 4 numeric elements : red, green, blue, alpha values
     * @returns the new array
     */
    fun asArray(): Array<Number>
    /**
     * Stores from the starting index in the given array the Color4 successive values
     * @param array defines the array where to store the r,g,b components
     * @param index defines an optional index in the target array to define where to start storing values
     * @returns the current Color4 object
     */
    fun toArray(array: Array<Number>, index: Number?): Color4
    /**
     * Determines equality between Color4 objects
     * @param otherColor defines the second operand
     * @returns true if the rgba values are equal to the given ones
     */
    fun equals(otherColor: Color4): Boolean
    /**
     * Creates a new Color4 set with the added values of the current Color4 and of the given one
     * @param right defines the second operand
     * @returns a new Color4 object
     */
    fun add(right: Color4): Color4
    /**
     * Creates a new Color4 set with the subtracted values of the given one from the current Color4
     * @param right defines the second operand
     * @returns a new Color4 object
     */
    fun subtract(right: Color4): Color4
    /**
     * Subtracts the given ones from the current Color4 values and stores the results in "result"
     * @param right defines the second operand
     * @param result defines the Color4 object where to store the result
     * @returns the current Color4 object
     */
    fun subtractToRef(right: Color4, result: Color4): Color4
    /**
     * Creates a new Color4 with the current Color4 values multiplied by scale
     * @param scale defines the scaling factor to apply
     * @returns a new Color4 object
     */
    fun scale(scale: Number): Color4
    /**
     * Multiplies the current Color4 values by scale and stores the result in "result"
     * @param scale defines the scaling factor to apply
     * @param result defines the Color4 object where to store the result
     * @returns the current unmodified Color4
     */
    fun scaleToRef(scale: Number, result: Color4): Color4
    /**
     * Scale the current Color4 values by a factor and add the result to a given Color4
     * @param scale defines the scale factor
     * @param result defines the Color4 object where to store the result
     * @returns the unmodified current Color4
     */
    fun scaleAndAddToRef(scale: Number, result: Color4): Color4
    /**
     * Clamps the rgb values by the min and max values and stores the result into "result"
     * @param min defines minimum clamping value (default is 0)
     * @param max defines maximum clamping value (default is 1)
     * @param result defines color to store the result into.
     * @returns the cuurent Color4
     */
    fun clampToRef(min: Number?, max: Number?, result: Color4): Color4
    /**
     * Multipy an Color4 value by another and return a new Color4 object
     * @param color defines the Color4 value to multiply by
     * @returns a new Color4 object
     */
    fun multiply(color: Color4): Color4
    /**
     * Multipy a Color4 value by another and push the result in a reference value
     * @param color defines the Color4 value to multiply by
     * @param result defines the Color4 to fill the result in
     * @returns the result Color4
     */
    fun multiplyToRef(color: Color4, result: Color4): Color4
    /**
     * Returns the String "Color4"
     * @returns "Color4"
     */
    fun getClassName(): String
    /**
     * Compute the Color4 hash code
     * @returns an unique Number that can be used to hash Color4 objects
     */
    fun getHashCode(): Number
    /**
     * Creates a new Color4 copied from the current one
     * @returns a new Color4 object
     */
    fun clone(): Color4
    /**
     * Copies the given Color4 values into the current one
     * @param source defines the source Color4 object
     * @returns the current updated Color4 object
     */
    fun copyFrom(source: Color4): Color4
    /**
     * Copies the given float values into the current one
     * @param r defines the red component to read from
     * @param g defines the green component to read from
     * @param b defines the blue component to read from
     * @param a defines the alpha component to read from
     * @returns the current updated Color4 object
     */
    fun copyFromFloats(r: Number, g: Number, b: Number, a: Number): Color4
    /**
     * Copies the given float values into the current one
     * @param r defines the red component to read from
     * @param g defines the green component to read from
     * @param b defines the blue component to read from
     * @param a defines the alpha component to read from
     * @returns the current updated Color4 object
     */
    fun set(r: Number, g: Number, b: Number, a: Number): Color4
    /**
     * Compute the Color4 hexadecimal code as a String
     * @returns a String containing the hexadecimal representation of the Color4 object
     */
    fun toHexString(): String
    /**
     * Computes a new Color4 converted from the current one to linear space
     * @returns a new Color4 object
     */
    fun toLinearSpace(): Color4
    /**
     * Converts the Color4 values to linear space and stores the result in "convertedColor"
     * @param convertedColor defines the Color4 object where to store the linear space version
     * @returns the unmodified Color4
     */
    fun toLinearSpaceToRef(convertedColor: Color4): Color4
    /**
     * Computes a new Color4 converted from the current one to gamma space
     * @returns a new Color4 object
     */
    fun toGammaSpace(): Color4
    /**
     * Converts the Color4 values to gamma space and stores the result in "convertedColor"
     * @param convertedColor defines the Color4 object where to store the gamma space version
     * @returns the unmodified Color4
     */
    fun toGammaSpaceToRef(convertedColor: Color4): Color4

    companion object {
        /**
         * Creates a new Color4 from the String containing valid hexadecimal values
         * @param hex defines a String containing valid hexadecimal values
         * @returns a new Color4 object
         */
        fun FromHexString(hex: String): Color4
        /**
         * Creates a new Color4 object set with the linearly interpolated values of "amount" between the left Color4 object and the right Color4 object
         * @param left defines the start value
         * @param right defines the end value
         * @param amount defines the gradient factor
         * @returns a new Color4 object
         */
        fun Lerp(left: Color4, right: Color4, amount: Number): Color4
        /**
         * Set the given "result" with the linearly interpolated values of "amount" between the left Color4 object and the right Color4 object
         * @param left defines the start value
         * @param right defines the end value
         * @param amount defines the gradient factor
         * @param result defines the Color4 object where to store data
         */
        fun LerpToRef(left: Color4, right: Color4, amount: Number, result: Color4)
        /**
         * Creates a new Color4 from a Color3 and an alpha value
         * @param color3 defines the source Color3 to read from
         * @param alpha defines the alpha component (1.0 by default)
         * @returns a new Color4 object
         */
        fun FromColor3(color3: Color3, alpha: Number?): Color4
        /**
         * Creates a new Color4 from the starting index element of the given array
         * @param array defines the source array to read from
         * @param offset defines the offset in the source array
         * @returns a new Color4 object
         */
        fun FromArray(array: Collection<Number>, offset: Number?): Color4
        /**
         * Creates a new Color3 from integer values (< 256)
         * @param r defines the red component to read from (value between 0 and 255)
         * @param g defines the green component to read from (value between 0 and 255)
         * @param b defines the blue component to read from (value between 0 and 255)
         * @param a defines the alpha component to read from (value between 0 and 255)
         * @returns a new Color3 object
         */
        fun FromInts(r: Number, g: Number, b: Number, a: Number): Color4
        /**
         * Check the content of a given array and convert it to an array containing RGBA data
         * If the original array was already containing count * 4 values then it is returned directly
         * @param colors defines the array to check
         * @param count defines the Number of RGBA data to expect
         * @returns an array containing count * 4 values (RGBA)
         */
        fun CheckColors4(colors: Array<Number>, count: Number): Array<Number>
    }
}
/**
 * Class representing a vector containing 2 coordinates
 */
external class Vector2 {
    /** defines the first coordinate */
    val x: Number
    /** defines the second coordinate */
    val y: Number
    /**
     * Creates a new Vector2 from the given x and y coordinates
     * @param x defines the first coordinate
     * @param y defines the second coordinate
     */
    constructor(
        /** defines the first coordinate */
        x: Number?,
        /** defines the second coordinate */
        y: Number?)
    /**
     * Gets class name
     * @returns the String "Vector2"
     */
    fun getClassName(): String
    /**
     * Gets current vector hash code
     * @returns the Vector2 hash code as a Number
     */
    fun getHashCode(): Number
    /**
     * Sets the Vector2 coordinates in the given array or Float32Array from the given index.
     * @param array defines the source array
     * @param index defines the offset in source array
     * @returns the current Vector2
     */
    fun toArray(array: FloatArray, index: Number?): Vector2
    /**
     * Copy the current vector to an array
     * @returns a new array with 2 elements: the Vector2 coordinates.
     */
    fun asArray(): Array<Number>
    /**
     * Sets the Vector2 coordinates with the given Vector2 coordinates
     * @param source defines the source Vector2
     * @returns the current updated Vector2
     */
    fun copyFrom(source: Vector2): Vector2
    /**
     * Sets the Vector2 coordinates with the given floats
     * @param x defines the first coordinate
     * @param y defines the second coordinate
     * @returns the current updated Vector2
     */
    fun copyFromFloats(x: Number, y: Number): Vector2
    /**
     * Sets the Vector2 coordinates with the given floats
     * @param x defines the first coordinate
     * @param y defines the second coordinate
     * @returns the current updated Vector2
     */
    fun set(x: Number, y: Number): Vector2
    /**
     * Add another vector with the current one
     * @param otherVector defines the other vector
     * @returns a new Vector2 set with the addition of the current Vector2 and the given one coordinates
     */
    fun add(otherVector: Vector2): Vector2
    /**
     * Sets the "result" coordinates with the addition of the current Vector2 and the given one coordinates
     * @param otherVector defines the other vector
     * @param result defines the target vector
     * @returns the unmodified current Vector2
     */
    fun addToRef(otherVector: Vector2, result: Vector2): Vector2
    /**
     * Set the Vector2 coordinates by adding the given Vector2 coordinates
     * @param otherVector defines the other vector
     * @returns the current updated Vector2
     */
    fun addInPlace(otherVector: Vector2): Vector2
    /**
     * Gets a new Vector2 by adding the current Vector2 coordinates to the given Vector3 x, y coordinates
     * @param otherVector defines the other vector
     * @returns a new Vector2
     */
    fun addVector3(otherVector: Vector3): Vector2
    /**
     * Gets a new Vector2 set with the subtracted coordinates of the given one from the current Vector2
     * @param otherVector defines the other vector
     * @returns a new Vector2
     */
    fun subtract(otherVector: Vector2): Vector2
    /**
     * Sets the "result" coordinates with the subtraction of the given one from the current Vector2 coordinates.
     * @param otherVector defines the other vector
     * @param result defines the target vector
     * @returns the unmodified current Vector2
     */
    fun subtractToRef(otherVector: Vector2, result: Vector2): Vector2
    /**
     * Sets the current Vector2 coordinates by subtracting from it the given one coordinates
     * @param otherVector defines the other vector
     * @returns the current updated Vector2
     */
    fun subtractInPlace(otherVector: Vector2): Vector2
    /**
     * Multiplies in place the current Vector2 coordinates by the given ones
     * @param otherVector defines the other vector
     * @returns the current updated Vector2
     */
    fun multiplyInPlace(otherVector: Vector2): Vector2
    /**
     * Returns a new Vector2 set with the multiplication of the current Vector2 and the given one coordinates
     * @param otherVector defines the other vector
     * @returns a new Vector2
     */
    fun multiply(otherVector: Vector2): Vector2
    /**
     * Sets "result" coordinates with the multiplication of the current Vector2 and the given one coordinates
     * @param otherVector defines the other vector
     * @param result defines the target vector
     * @returns the unmodified current Vector2
     */
    fun multiplyToRef(otherVector: Vector2, result: Vector2): Vector2
    /**
     * Gets a new Vector2 set with the Vector2 coordinates multiplied by the given floats
     * @param x defines the first coordinate
     * @param y defines the second coordinate
     * @returns a new Vector2
     */
    fun multiplyByFloats(x: Number, y: Number): Vector2
    /**
     * Returns a new Vector2 set with the Vector2 coordinates divided by the given one coordinates
     * @param otherVector defines the other vector
     * @returns a new Vector2
     */
    fun divide(otherVector: Vector2): Vector2
    /**
     * Sets the "result" coordinates with the Vector2 divided by the given one coordinates
     * @param otherVector defines the other vector
     * @param result defines the target vector
     * @returns the unmodified current Vector2
     */
    fun divideToRef(otherVector: Vector2, result: Vector2): Vector2
    /**
     * Divides the current Vector2 coordinates by the given ones
     * @param otherVector defines the other vector
     * @returns the current updated Vector2
     */
    fun divideInPlace(otherVector: Vector2): Vector2
    /**
     * Gets a new Vector2 with current Vector2 negated coordinates
     * @returns a new Vector2
     */
    fun negate(): Vector2
    /**
     * Multiply the Vector2 coordinates by scale
     * @param scale defines the scaling factor
     * @returns the current updated Vector2
     */
    fun scaleInPlace(scale: Number): Vector2
    /**
     * Returns a new Vector2 scaled by "scale" from the current Vector2
     * @param scale defines the scaling factor
     * @returns a new Vector2
     */
    fun scale(scale: Number): Vector2
    /**
     * Scale the current Vector2 values by a factor to a given Vector2
     * @param scale defines the scale factor
     * @param result defines the Vector2 object where to store the result
     * @returns the unmodified current Vector2
     */
    fun scaleToRef(scale: Number, result: Vector2): Vector2
    /**
     * Scale the current Vector2 values by a factor and add the result to a given Vector2
     * @param scale defines the scale factor
     * @param result defines the Vector2 object where to store the result
     * @returns the unmodified current Vector2
     */
    fun scaleAndAddToRef(scale: Number, result: Vector2): Vector2
    /**
     * Gets a Boolean if two vectors are equals
     * @param otherVector defines the other vector
     * @returns true if the given vector coordinates strictly equal the current Vector2 ones
     */
    fun equals(otherVector: Vector2): Boolean
    /**
     * Gets a Boolean if two vectors are equals (using an epsilon value)
     * @param otherVector defines the other vector
     * @param epsilon defines the minimal distance to consider equality
     * @returns true if the given vector coordinates are close to the current ones by a distance of epsilon.
     */
    fun equalsWithEpsilon(otherVector: Vector2, epsilon: Number?): Boolean
    /**
     * Gets a new Vector2 from current Vector2 floored values
     * @returns a new Vector2
     */
    fun floor(): Vector2
    /**
     * Gets a new Vector2 from current Vector2 floored values
     * @returns a new Vector2
     */
    fun fract(): Vector2
    /**
     * Gets the length of the vector
     * @returns the vector length (float)
     */
    fun length(): Number
    /**
     * Gets the vector squared length
     * @returns the vector squared length (float)
     */
    fun lengthSquared(): Number
    /**
     * Normalize the vector
     * @returns the current updated Vector2
     */
    fun normalize(): Vector2
    /**
     * Gets a new Vector2 copied from the Vector2
     * @returns a new Vector2
     */
    fun clone(): Vector2

    companion object {
        /**
         * Gets a new Vector2(0, 0)
         * @returns a new Vector2
         */
        fun Zero(): Vector2
        /**
         * Gets a new Vector2(1, 1)
         * @returns a new Vector2
         */
        fun One(): Vector2
        /**
         * Gets a new Vector2 set from the given index element of the given array
         * @param array defines the data source
        `     * @param offset defines the offset in the data source
         * @returns a new Vector2
         */
        fun FromArray(array: Collection<Number>, offset: Number?): Vector2
        /**
         * Sets "result" from the given index element of the given array
         * @param array defines the data source
         * @param offset defines the offset in the data source
         * @param result defines the target vector
         */
        fun FromArrayToRef(array: Collection<Number>, offset: Number, result: Vector2)
        /**
         * Gets a new Vector2 located for "amount" (float) on the CatmullRom spline defined by the given four Vector2
         * @param value1 defines 1st point of control
         * @param value2 defines 2nd point of control
         * @param value3 defines 3rd point of control
         * @param value4 defines 4th point of control
         * @param amount defines the interpolation factor
         * @returns a new Vector2
         */
        fun CatmullRom(value1: Vector2, value2: Vector2, value3: Vector2, value4: Vector2, amount: Number): Vector2
        /**
         * Returns a new Vector2 set with same the coordinates than "value" ones if the vector "value" is in the square defined by "min" and "max".
         * If a coordinate of "value" is lower than "min" coordinates, the returned Vector2 is given this "min" coordinate.
         * If a coordinate of "value" is greater than "max" coordinates, the returned Vector2 is given this "max" coordinate
         * @param value defines the value to clamp
         * @param min defines the lower limit
         * @param max defines the upper limit
         * @returns a new Vector2
         */
        fun Clamp(value: Vector2, min: Vector2, max: Vector2): Vector2
        /**
         * Returns a new Vector2 located for "amount" (float) on the Hermite spline defined by the vectors "value1", "value3", "tangent1", "tangent2"
         * @param value1 defines the 1st control point
         * @param tangent1 defines the outgoing tangent
         * @param value2 defines the 2nd control point
         * @param tangent2 defines the incoming tangent
         * @param amount defines the interpolation factor
         * @returns a new Vector2
         */
        fun Hermite(value1: Vector2, tangent1: Vector2, value2: Vector2, tangent2: Vector2, amount: Number): Vector2
        /**
         * Returns a new Vector2 located for "amount" (float) on the linear interpolation between the vector "start" adn the vector "end".
         * @param start defines the start vector
         * @param end defines the end vector
         * @param amount defines the interpolation factor
         * @returns a new Vector2
         */
        fun Lerp(start: Vector2, end: Vector2, amount: Number): Vector2
        /**
         * Gets the dot product of the vector "left" and the vector "right"
         * @param left defines first vector
         * @param right defines second vector
         * @returns the dot product (float)
         */
        fun Dot(left: Vector2, right: Vector2): Number
        /**
         * Returns a new Vector2 equal to the normalized given vector
         * @param vector defines the vector to normalize
         * @returns a new Vector2
         */
        fun Normalize(vector: Vector2): Vector2
        /**
         * Gets a new Vector2 set with the minimal coordinate values from the "left" and "right" vectors
         * @param left defines 1st vector
         * @param right defines 2nd vector
         * @returns a new Vector2
         */
        fun Minimize(left: Vector2, right: Vector2): Vector2
        /**
         * Gets a new Vecto2 set with the maximal coordinate values from the "left" and "right" vectors
         * @param left defines 1st vector
         * @param right defines 2nd vector
         * @returns a new Vector2
         */
        fun Maximize(left: Vector2, right: Vector2): Vector2
        /**
         * Gets a new Vector2 set with the transformed coordinates of the given vector by the given transformation matrix
         * @param vector defines the vector to transform
         * @param transformation defines the matrix to apply
         * @returns a new Vector2
         */
        fun Transform(vector: Vector2, transformation: Matrix): Vector2
        /**
         * Transforms the given vector coordinates by the given transformation matrix and stores the result in the vector "result" coordinates
         * @param vector defines the vector to transform
         * @param transformation defines the matrix to apply
         * @param result defines the target vector
         */
        fun TransformToRef(vector: Vector2, transformation: Matrix, result: Vector2)
        /**
         * Determines if a given vector is included in a triangle
         * @param p defines the vector to test
         * @param p0 defines 1st triangle point
         * @param p1 defines 2nd triangle point
         * @param p2 defines 3rd triangle point
         * @returns true if the point "p" is in the triangle defined by the vertors "p0", "p1", "p2"
         */
        fun PointInTriangle(p: Vector2, p0: Vector2, p1: Vector2, p2: Vector2): Boolean
        /**
         * Gets the distance between the vectors "value1" and "value2"
         * @param value1 defines first vector
         * @param value2 defines second vector
         * @returns the distance between vectors
         */
        fun Distance(value1: Vector2, value2: Vector2): Number
        /**
         * Returns the squared distance between the vectors "value1" and "value2"
         * @param value1 defines first vector
         * @param value2 defines second vector
         * @returns the squared distance between vectors
         */
        fun DistanceSquared(value1: Vector2, value2: Vector2): Number
        /**
         * Gets a new Vector2 located at the center of the vectors "value1" and "value2"
         * @param value1 defines first vector
         * @param value2 defines second vector
         * @returns a new Vector2
         */
        fun Center(value1: Vector2, value2: Vector2): Vector2
        /**
         * Gets the shortest distance (float) between the point "p" and the segment defined by the two points "segA" and "segB".
         * @param p defines the middle point
         * @param segA defines one point of the segment
         * @param segB defines the other point of the segment
         * @returns the shortest distance
         */
        fun DistanceOfPointFromSegment(p: Vector2, segA: Vector2, segB: Vector2): Number
    }
}
/**
 * Classed used to store (x,y,z) vector representation
 * A Vector3 is the main object used in 3D geometry
 * It can represent etiher the coordinates of a point the space, either a direction
 * Reminder: js uses a left handed forward facing system
 */
external class Vector3 {
    /**
     * Defines the first coordinates (on X axis)
     */
    val x: Number
    /**
     * Defines the second coordinates (on Y axis)
     */
    val y: Number
    /**
     * Defines the third coordinates (on Z axis)
     */
    val z: Number
    /**
     * Creates a new Vector3 object from the given x, y, z (floats) coordinates.
     * @param x defines the first coordinates (on X axis)
     * @param y defines the second coordinates (on Y axis)
     * @param z defines the third coordinates (on Z axis)
     */
    constructor(
        /**
         * Defines the first coordinates (on X axis)
         */
        x: Number? = definedExternally,
        /**
         * Defines the second coordinates (on Y axis)
         */
        y: Number? = definedExternally,
        /**
         * Defines the third coordinates (on Z axis)
         */
        z: Number? = definedExternally)
    /**
     * Gets the class name
     * @returns the String "Vector3"
     */
    fun getClassName(): String
    /**
     * Creates the Vector3 hash code
     * @returns a Number which tends to be unique between Vector3 instances
     */
    fun getHashCode(): Number
    /**
     * Creates an array containing three elements : the coordinates of the Vector3
     * @returns a new array of numbers
     */
    fun asArray(): Array<Number>
    /**
     * Populates the given array or Float32Array from the given index with the successive coordinates of the Vector3
     * @param array defines the destination array
     * @param index defines the offset in the destination array
     * @returns the current Vector3
     */
    fun toArray(array: FloatArray, index: Number?): Vector3
    /**
     * Converts the current Vector3 into a quaternion (considering that the Vector3 contains Euler angles representation of a rotation)
     * @returns a new Quaternion object, computed from the Vector3 coordinates
     */
    fun toQuaternion(): Quaternion
    /**
     * Adds the given vector to the current Vector3
     * @param otherVector defines the second operand
     * @returns the current updated Vector3
     */
    fun addInPlace(otherVector: Vector3): Vector3
    /**
     * Adds the given coordinates to the current Vector3
     * @param x defines the x coordinate of the operand
     * @param y defines the y coordinate of the operand
     * @param z defines the z coordinate of the operand
     * @returns the current updated Vector3
     */
    fun addInPlaceFromFloats(x: Number, y: Number, z: Number): Vector3
    /**
     * Gets a new Vector3, result of the addition the current Vector3 and the given vector
     * @param otherVector defines the second operand
     * @returns the resulting Vector3
     */
    fun add(otherVector: Vector3): Vector3
    /**
     * Adds the current Vector3 to the given one and stores the result in the vector "result"
     * @param otherVector defines the second operand
     * @param result defines the Vector3 object where to store the result
     * @returns the current Vector3
     */
    fun addToRef(otherVector: Vector3, result: Vector3): Vector3
    /**
     * Subtract the given vector from the current Vector3
     * @param otherVector defines the second operand
     * @returns the current updated Vector3
     */
    fun subtractInPlace(otherVector: Vector3): Vector3
    /**
     * Returns a new Vector3, result of the subtraction of the given vector from the current Vector3
     * @param otherVector defines the second operand
     * @returns the resulting Vector3
     */
    fun subtract(otherVector: Vector3): Vector3
    /**
     * Subtracts the given vector from the current Vector3 and stores the result in the vector "result".
     * @param otherVector defines the second operand
     * @param result defines the Vector3 object where to store the result
     * @returns the current Vector3
     */
    fun subtractToRef(otherVector: Vector3, result: Vector3): Vector3
    /**
     * Returns a new Vector3 set with the subtraction of the given floats from the current Vector3 coordinates
     * @param x defines the x coordinate of the operand
     * @param y defines the y coordinate of the operand
     * @param z defines the z coordinate of the operand
     * @returns the resulting Vector3
     */
    fun subtractFromFloats(x: Number, y: Number, z: Number): Vector3
    /**
     * Subtracts the given floats from the current Vector3 coordinates and set the given vector "result" with this result
     * @param x defines the x coordinate of the operand
     * @param y defines the y coordinate of the operand
     * @param z defines the z coordinate of the operand
     * @param result defines the Vector3 object where to store the result
     * @returns the current Vector3
     */
    fun subtractFromFloatsToRef(x: Number, y: Number, z: Number, result: Vector3): Vector3
    /**
     * Gets a new Vector3 set with the current Vector3 negated coordinates
     * @returns a new Vector3
     */
    fun negate(): Vector3
    /**
     * Multiplies the Vector3 coordinates by the float "scale"
     * @param scale defines the multiplier factor
     * @returns the current updated Vector3
     */
    fun scaleInPlace(scale: Number): Vector3
    /**
     * Returns a new Vector3 set with the current Vector3 coordinates multiplied by the float "scale"
     * @param scale defines the multiplier factor
     * @returns a new Vector3
     */
    fun scale(scale: Number): Vector3
    /**
     * Multiplies the current Vector3 coordinates by the float "scale" and stores the result in the given vector "result" coordinates
     * @param scale defines the multiplier factor
     * @param result defines the Vector3 object where to store the result
     * @returns the current Vector3
     */
    fun scaleToRef(scale: Number, result: Vector3): Vector3
    /**
     * Scale the current Vector3 values by a factor and add the result to a given Vector3
     * @param scale defines the scale factor
     * @param result defines the Vector3 object where to store the result
     * @returns the unmodified current Vector3
     */
    fun scaleAndAddToRef(scale: Number, result: Vector3): Vector3
    /**
     * Returns true if the current Vector3 and the given vector coordinates are strictly equal
     * @param otherVector defines the second operand
     * @returns true if both vectors are equals
     */
    fun equals(otherVector: Vector3): Boolean
    /**
     * Returns true if the current Vector3 and the given vector coordinates are distant less than epsilon
     * @param otherVector defines the second operand
     * @param epsilon defines the minimal distance to define values as equals
     * @returns true if both vectors are distant less than epsilon
     */
    fun equalsWithEpsilon(otherVector: Vector3, epsilon: Number?): Boolean
    /**
     * Returns true if the current Vector3 coordinates equals the given floats
     * @param x defines the x coordinate of the operand
     * @param y defines the y coordinate of the operand
     * @param z defines the z coordinate of the operand
     * @returns true if both vectors are equals
     */
    fun equalsToFloats(x: Number, y: Number, z: Number): Boolean
    /**
     * Multiplies the current Vector3 coordinates by the given ones
     * @param otherVector defines the second operand
     * @returns the current updated Vector3
     */
    fun multiplyInPlace(otherVector: Vector3): Vector3
    /**
     * Returns a new Vector3, result of the multiplication of the current Vector3 by the given vector
     * @param otherVector defines the second operand
     * @returns the new Vector3
     */
    fun multiply(otherVector: Vector3): Vector3
    /**
     * Multiplies the current Vector3 by the given one and stores the result in the given vector "result"
     * @param otherVector defines the second operand
     * @param result defines the Vector3 object where to store the result
     * @returns the current Vector3
     */
    fun multiplyToRef(otherVector: Vector3, result: Vector3): Vector3
    /**
     * Returns a new Vector3 set with the result of the mulliplication of the current Vector3 coordinates by the given floats
     * @param x defines the x coordinate of the operand
     * @param y defines the y coordinate of the operand
     * @param z defines the z coordinate of the operand
     * @returns the new Vector3
     */
    fun multiplyByFloats(x: Number, y: Number, z: Number): Vector3
    /**
     * Returns a new Vector3 set with the result of the division of the current Vector3 coordinates by the given ones
     * @param otherVector defines the second operand
     * @returns the new Vector3
     */
    fun divide(otherVector: Vector3): Vector3
    /**
     * Divides the current Vector3 coordinates by the given ones and stores the result in the given vector "result"
     * @param otherVector defines the second operand
     * @param result defines the Vector3 object where to store the result
     * @returns the current Vector3
     */
    fun divideToRef(otherVector: Vector3, result: Vector3): Vector3
    /**
     * Divides the current Vector3 coordinates by the given ones.
     * @param otherVector defines the second operand
     * @returns the current updated Vector3
     */
    fun divideInPlace(otherVector: Vector3): Vector3
    /**
     * Updates the current Vector3 with the minimal coordinate values between its and the given vector ones
     * @param other defines the second operand
     * @returns the current updated Vector3
     */
    fun minimizeInPlace(other: Vector3): Vector3
    /**
     * Updates the current Vector3 with the maximal coordinate values between its and the given vector ones.
     * @param other defines the second operand
     * @returns the current updated Vector3
     */
    fun maximizeInPlace(other: Vector3): Vector3
    /**
     * Updates the current Vector3 with the minimal coordinate values between its and the given coordinates
     * @param x defines the x coordinate of the operand
     * @param y defines the y coordinate of the operand
     * @param z defines the z coordinate of the operand
     * @returns the current updated Vector3
     */
    fun minimizeInPlaceFromFloats(x: Number, y: Number, z: Number): Vector3
    /**
     * Updates the current Vector3 with the maximal coordinate values between its and the given coordinates.
     * @param x defines the x coordinate of the operand
     * @param y defines the y coordinate of the operand
     * @param z defines the z coordinate of the operand
     * @returns the current updated Vector3
     */
    fun maximizeInPlaceFromFloats(x: Number, y: Number, z: Number): Vector3
    /**
     * Due to float precision, scale of a mesh could be uniform but float values are off by a small fraction
     * Check if is non uniform within a certain amount of decimal places to account for this
     * @param epsilon the amount the values can differ
     * @returns if the the vector is non uniform to a certain Number of decimal places
     */
    fun isNonUniformWithinEpsilon(epsilon: Number): Boolean
    /**
     * Gets a Boolean indicating that the vector is non uniform meaning x, y or z are not all the same
     */
    val isNonUniform: Boolean
    /**
     * Gets a new Vector3 from current Vector3 floored values
     * @returns a new Vector3
     */
    fun floor(): Vector3
    /**
     * Gets a new Vector3 from current Vector3 floored values
     * @returns a new Vector3
     */
    fun fract(): Vector3
    /**
     * Gets the length of the Vector3
     * @returns the length of the Vecto3
     */
    fun length(): Number
    /**
     * Gets the squared length of the Vector3
     * @returns squared length of the Vector3
     */
    fun lengthSquared(): Number
    /**
     * Normalize the current Vector3.
     * Please note that this is an in place operation.
     * @returns the current updated Vector3
     */
    fun normalize(): Vector3
    /**
     * Reorders the x y z properties of the vector in place
     * @param order new ordering of the properties (eg. for vector 1,2,3 with "ZYX" will produce 3,2,1)
     * @returns the current updated vector
     */
    fun reorderInPlace(order: String): Vector3
    /**
     * Rotates the vector around 0,0,0 by a quaternion
     * @param quaternion the rotation quaternion
     * @param result vector to store the result
     * @returns the resulting vector
     */
    fun rotateByQuaternionToRef(quaternion: Quaternion, result: Vector3): Vector3
    /**
     * Rotates a vector around a given point
     * @param quaternion the rotation quaternion
     * @param point the point to rotate around
     * @param result vector to store the result
     * @returns the resulting vector
     */
    fun rotateByQuaternionAroundPointToRef(quaternion: Quaternion, point: Vector3, result: Vector3): Vector3
    /**
     * Normalize the current Vector3 with the given input length.
     * Please note that this is an in place operation.
     * @param len the length of the vector
     * @returns the current updated Vector3
     */
    fun normalizeFromLength(len: Number): Vector3
    /**
     * Normalize the current Vector3 to a new vector
     * @returns the new Vector3
     */
    fun normalizeToNew(): Vector3
    /**
     * Normalize the current Vector3 to the reference
     * @param reference define the Vector3 to update
     * @returns the updated Vector3
     */
    fun normalizeToRef(reference: Vector3): Vector3
    /**
     * Creates a new Vector3 copied from the current Vector3
     * @returns the new Vector3
     */
    fun clone(): Vector3
    /**
     * Copies the given vector coordinates to the current Vector3 ones
     * @param source defines the source Vector3
     * @returns the current updated Vector3
     */
    fun copyFrom(source: Vector3): Vector3
    /**
     * Copies the given floats to the current Vector3 coordinates
     * @param x defines the x coordinate of the operand
     * @param y defines the y coordinate of the operand
     * @param z defines the z coordinate of the operand
     * @returns the current updated Vector3
     */
    fun copyFromFloats(x: Number, y: Number, z: Number): Vector3
    /**
     * Copies the given floats to the current Vector3 coordinates
     * @param x defines the x coordinate of the operand
     * @param y defines the y coordinate of the operand
     * @param z defines the z coordinate of the operand
     * @returns the current updated Vector3
     */
    fun set(x: Number, y: Number, z: Number): Vector3
    /**
     * Copies the given float to the current Vector3 coordinates
     * @param v defines the x, y and z coordinates of the operand
     * @returns the current updated Vector3
     */
    fun setAll(v: Number): Vector3

    companion object {
        /**
         * Get the clip factor between two vectors
         * @param vector0 defines the first operand
         * @param vector1 defines the second operand
         * @param axis defines the axis to use
         * @param size defines the size along the axis
         * @returns the clip factor
         */
        fun GetClipFactor(vector0: Vector3, vector1: Vector3, axis: Vector3, size: Number): Number
        /**
         * Get angle between two vectors
         * @param vector0 angle between vector0 and vector1
         * @param vector1 angle between vector0 and vector1
         * @param normal direction of the normal
         * @return the angle between vector0 and vector1
         */
        fun GetAngleBetweenVectors(vector0: Vector3, vector1: Vector3, normal: Vector3): Number
        /**
         * Returns a new Vector3 set from the index "offset" of the given array
         * @param array defines the source array
         * @param offset defines the offset in the source array
         * @returns the new Vector3
         */
        fun FromArray(array: Collection<Number>, offset: Number?): Vector3
        /**
         * Returns a new Vector3 set from the index "offset" of the given Float32Array
         * This function is deprecated. Use FromArray instead
         * @param array defines the source array
         * @param offset defines the offset in the source array
         * @returns the new Vector3
         */
        fun FromFloatArray(array: Float32Array, offset: Number?): Vector3
        /**
         * Sets the given vector "result" with the element values from the index "offset" of the given array
         * @param array defines the source array
         * @param offset defines the offset in the source array
         * @param result defines the Vector3 where to store the result
         */
        fun FromArrayToRef(array: Collection<Number>, offset: Number, result: Vector3)
        /**
         * Sets the given vector "result" with the element values from the index "offset" of the given Float32Array
         * This function is deprecated.  Use FromArrayToRef instead.
         * @param array defines the source array
         * @param offset defines the offset in the source array
         * @param result defines the Vector3 where to store the result
         */
        fun FromFloatArrayToRef(array: Float32Array, offset: Number, result: Vector3)
        /**
         * Sets the given vector "result" with the given floats.
         * @param x defines the x coordinate of the source
         * @param y defines the y coordinate of the source
         * @param z defines the z coordinate of the source
         * @param result defines the Vector3 where to store the result
         */
        fun FromFloatsToRef(x: Number, y: Number, z: Number, result: Vector3)
        /**
         * Returns a new Vector3 set to (0.0, 0.0, 0.0)
         * @returns a new empty Vector3
         */
        fun Zero(): Vector3
        /**
         * Returns a new Vector3 set to (1.0, 1.0, 1.0)
         * @returns a new unit Vector3
         */
        fun One(): Vector3
        /**
         * Returns a new Vector3 set to (0.0, 1.0, 0.0)
         * @returns a new up Vector3
         */
        fun Up(): Vector3
        /**
         * Gets a up Vector3 that must not be updated
         */
        val UpReadOnly: Vector3
        /**
         * Returns a new Vector3 set to (0.0, -1.0, 0.0)
         * @returns a new down Vector3
         */
        fun Down(): Vector3
        /**
         * Returns a new Vector3 set to (0.0, 0.0, 1.0)
         * @returns a new forward Vector3
         */
        fun Forward(): Vector3
        /**
         * Returns a new Vector3 set to (0.0, 0.0, -1.0)
         * @returns a new forward Vector3
         */
        fun Backward(): Vector3
        /**
         * Returns a new Vector3 set to (1.0, 0.0, 0.0)
         * @returns a new right Vector3
         */
        fun Right(): Vector3
        /**
         * Returns a new Vector3 set to (-1.0, 0.0, 0.0)
         * @returns a new left Vector3
         */
        fun Left(): Vector3
        /**
         * Returns a new Vector3 set with the result of the transformation by the given matrix of the given vector.
         * This method computes tranformed coordinates only, not transformed direction vectors (ie. it takes translation in account)
         * @param vector defines the Vector3 to transform
         * @param transformation defines the transformation matrix
         * @returns the transformed Vector3
         */
        fun TransformCoordinates(vector: Vector3, transformation: Matrix): Vector3
        /**
         * Sets the given vector "result" coordinates with the result of the transformation by the given matrix of the given vector
         * This method computes tranformed coordinates only, not transformed direction vectors (ie. it takes translation in account)
         * @param vector defines the Vector3 to transform
         * @param transformation defines the transformation matrix
         * @param result defines the Vector3 where to store the result
         */
        fun TransformCoordinatesToRef(vector: Vector3, transformation: Matrix, result: Vector3)
        /**
         * Sets the given vector "result" coordinates with the result of the transformation by the given matrix of the given floats (x, y, z)
         * This method computes tranformed coordinates only, not transformed direction vectors
         * @param x define the x coordinate of the source vector
         * @param y define the y coordinate of the source vector
         * @param z define the z coordinate of the source vector
         * @param transformation defines the transformation matrix
         * @param result defines the Vector3 where to store the result
         */
        fun TransformCoordinatesFromFloatsToRef(x: Number, y: Number, z: Number, transformation: Matrix, result: Vector3)
        /**
         * Returns a new Vector3 set with the result of the normal transformation by the given matrix of the given vector
         * This methods computes transformed normalized direction vectors only (ie. it does not apply translation)
         * @param vector defines the Vector3 to transform
         * @param transformation defines the transformation matrix
         * @returns the new Vector3
         */
        fun TransformNormal(vector: Vector3, transformation: Matrix): Vector3
        /**
         * Sets the given vector "result" with the result of the normal transformation by the given matrix of the given vector
         * This methods computes transformed normalized direction vectors only (ie. it does not apply translation)
         * @param vector defines the Vector3 to transform
         * @param transformation defines the transformation matrix
         * @param result defines the Vector3 where to store the result
         */
        fun TransformNormalToRef(vector: Vector3, transformation: Matrix, result: Vector3)
        /**
         * Sets the given vector "result" with the result of the normal transformation by the given matrix of the given floats (x, y, z)
         * This methods computes transformed normalized direction vectors only (ie. it does not apply translation)
         * @param x define the x coordinate of the source vector
         * @param y define the y coordinate of the source vector
         * @param z define the z coordinate of the source vector
         * @param transformation defines the transformation matrix
         * @param result defines the Vector3 where to store the result
         */
        fun TransformNormalFromFloatsToRef(x: Number, y: Number, z: Number, transformation: Matrix, result: Vector3)
        /**
         * Returns a new Vector3 located for "amount" on the CatmullRom interpolation spline defined by the vectors "value1", "value2", "value3", "value4"
         * @param value1 defines the first control point
         * @param value2 defines the second control point
         * @param value3 defines the third control point
         * @param value4 defines the fourth control point
         * @param amount defines the amount on the spline to use
         * @returns the new Vector3
         */
        fun CatmullRom(value1: Vector3, value2: Vector3, value3: Vector3, value4: Vector3, amount: Number): Vector3
        /**
         * Returns a new Vector3 set with the coordinates of "value", if the vector "value" is in the cube defined by the vectors "min" and "max"
         * If a coordinate value of "value" is lower than one of the "min" coordinate, then this "value" coordinate is set with the "min" one
         * If a coordinate value of "value" is greater than one of the "max" coordinate, then this "value" coordinate is set with the "max" one
         * @param value defines the current value
         * @param min defines the lower range value
         * @param max defines the upper range value
         * @returns the new Vector3
         */
        fun Clamp(value: Vector3, min: Vector3, max: Vector3): Vector3
        /**
         * Sets the given vector "result" with the coordinates of "value", if the vector "value" is in the cube defined by the vectors "min" and "max"
         * If a coordinate value of "value" is lower than one of the "min" coordinate, then this "value" coordinate is set with the "min" one
         * If a coordinate value of "value" is greater than one of the "max" coordinate, then this "value" coordinate is set with the "max" one
         * @param value defines the current value
         * @param min defines the lower range value
         * @param max defines the upper range value
         * @param result defines the Vector3 where to store the result
         */
        fun ClampToRef(value: Vector3, min: Vector3, max: Vector3, result: Vector3)
        /**
         * Returns a new Vector3 located for "amount" (float) on the Hermite interpolation spline defined by the vectors "value1", "tangent1", "value2", "tangent2"
         * @param value1 defines the first control point
         * @param tangent1 defines the first tangent vector
         * @param value2 defines the second control point
         * @param tangent2 defines the second tangent vector
         * @param amount defines the amount on the interpolation spline (between 0 and 1)
         * @returns the new Vector3
         */
        fun Hermite(value1: Vector3, tangent1: Vector3, value2: Vector3, tangent2: Vector3, amount: Number): Vector3
        /**
         * Returns a new Vector3 located for "amount" (float) on the linear interpolation between the vectors "start" and "end"
         * @param start defines the start value
         * @param end defines the end value
         * @param amount max defines amount between both (between 0 and 1)
         * @returns the new Vector3
         */
        fun Lerp(start: Vector3, end: Vector3, amount: Number): Vector3
        /**
         * Sets the given vector "result" with the result of the linear interpolation from the vector "start" for "amount" to the vector "end"
         * @param start defines the start value
         * @param end defines the end value
         * @param amount max defines amount between both (between 0 and 1)
         * @param result defines the Vector3 where to store the result
         */
        fun LerpToRef(start: Vector3, end: Vector3, amount: Number, result: Vector3)
        /**
         * Returns the dot product (float) between the vectors "left" and "right"
         * @param left defines the left operand
         * @param right defines the right operand
         * @returns the dot product
         */
        fun Dot(left: Vector3, right: Vector3): Number
        /**
         * Returns a new Vector3 as the cross product of the vectors "left" and "right"
         * The cross product is then orthogonal to both "left" and "right"
         * @param left defines the left operand
         * @param right defines the right operand
         * @returns the cross product
         */
        fun Cross(left: Vector3, right: Vector3): Vector3
        /**
         * Sets the given vector "result" with the cross product of "left" and "right"
         * The cross product is then orthogonal to both "left" and "right"
         * @param left defines the left operand
         * @param right defines the right operand
         * @param result defines the Vector3 where to store the result
         */
        fun CrossToRef(left: Vector3, right: Vector3, result: Vector3)
        /**
         * Returns a new Vector3 as the normalization of the given vector
         * @param vector defines the Vector3 to normalize
         * @returns the new Vector3
         */
        fun Normalize(vector: Vector3): Vector3
        /**
         * Sets the given vector "result" with the normalization of the given first vector
         * @param vector defines the Vector3 to normalize
         * @param result defines the Vector3 where to store the result
         */
        fun NormalizeToRef(vector: Vector3, result: Vector3)
        /**
         * Project a Vector3 onto screen space
         * @param vector defines the Vector3 to project
         * @param world defines the world matrix to use
         * @param transform defines the transform (view x projection) matrix to use
         * @param viewport defines the screen viewport to use
         * @returns the new Vector3
         */
        fun Project(vector: Vector3, world: Matrix, transform: Matrix, viewport: Viewport): Vector3
        /**
         * Unproject from screen space to object space
         * @param source defines the screen space Vector3 to use
         * @param viewportWidth defines the current width of the viewport
         * @param viewportHeight defines the current height of the viewport
         * @param world defines the world matrix to use (can be set to Identity to go to world space)
         * @param transform defines the transform (view x projection) matrix to use
         * @returns the new Vector3
         */
        fun UnprojectFromTransform(source: Vector3, viewportWidth: Number, viewportHeight: Number, world: Matrix, transform: Matrix): Vector3
        /**
         * Unproject from screen space to object space
         * @param source defines the screen space Vector3 to use
         * @param viewportWidth defines the current width of the viewport
         * @param viewportHeight defines the current height of the viewport
         * @param world defines the world matrix to use (can be set to Identity to go to world space)
         * @param view defines the view matrix to use
         * @param projection defines the projection matrix to use
         * @returns the new Vector3
         */
        fun Unproject(source: Vector3, viewportWidth: Number, viewportHeight: Number, world: Matrix, view: Matrix, projection: Matrix): Vector3
        /**
         * Unproject from screen space to object space
         * @param source defines the screen space Vector3 to use
         * @param viewportWidth defines the current width of the viewport
         * @param viewportHeight defines the current height of the viewport
         * @param world defines the world matrix to use (can be set to Identity to go to world space)
         * @param view defines the view matrix to use
         * @param projection defines the projection matrix to use
         * @param result defines the Vector3 where to store the result
         */
        fun UnprojectToRef(source: Vector3, viewportWidth: Number, viewportHeight: Number, world: Matrix, view: Matrix, projection: Matrix, result: Vector3)
        /**
         * Unproject from screen space to object space
         * @param sourceX defines the screen space x coordinate to use
         * @param sourceY defines the screen space y coordinate to use
         * @param sourceZ defines the screen space z coordinate to use
         * @param viewportWidth defines the current width of the viewport
         * @param viewportHeight defines the current height of the viewport
         * @param world defines the world matrix to use (can be set to Identity to go to world space)
         * @param view defines the view matrix to use
         * @param projection defines the projection matrix to use
         * @param result defines the Vector3 where to store the result
         */
        fun UnprojectFloatsToRef(sourceX: Float, sourceY: Float, sourceZ: Float, viewportWidth: Number, viewportHeight: Number, world: Matrix, view: Matrix, projection: Matrix, result: Vector3)
        /**
         * Gets the minimal coordinate values between two Vector3
         * @param left defines the first operand
         * @param right defines the second operand
         * @returns the new Vector3
         */
        fun Minimize(left: Vector3, right: Vector3): Vector3
        /**
         * Gets the maximal coordinate values between two Vector3
         * @param left defines the first operand
         * @param right defines the second operand
         * @returns the new Vector3
         */
        fun Maximize(left: Vector3, right: Vector3): Vector3
        /**
         * Returns the distance between the vectors "value1" and "value2"
         * @param value1 defines the first operand
         * @param value2 defines the second operand
         * @returns the distance
         */
        fun Distance(value1: Vector3, value2: Vector3): Number
        /**
         * Returns the squared distance between the vectors "value1" and "value2"
         * @param value1 defines the first operand
         * @param value2 defines the second operand
         * @returns the squared distance
         */
        fun DistanceSquared(value1: Vector3, value2: Vector3): Number
        /**
         * Returns a new Vector3 located at the center between "value1" and "value2"
         * @param value1 defines the first operand
         * @param value2 defines the second operand
         * @returns the new Vector3
         */
        fun Center(value1: Vector3, value2: Vector3): Vector3
        /**
         * Given three orthogonal normalized left-handed oriented Vector3 axis in space (target system),
         * RotationFromAxis() returns the rotation Euler angles (ex : rotation.x, rotation.y, rotation.z) to apply
         * to something in order to rotate it from its local system to the given target system
         * Note: axis1, axis2 and axis3 are normalized during this operation
         * @param axis1 defines the first axis
         * @param axis2 defines the second axis
         * @param axis3 defines the third axis
         * @returns a new Vector3
         */
        fun RotationFromAxis(axis1: Vector3, axis2: Vector3, axis3: Vector3): Vector3
        /**
         * The same than RotationFromAxis but updates the given ref Vector3 parameter instead of returning a new Vector3
         * @param axis1 defines the first axis
         * @param axis2 defines the second axis
         * @param axis3 defines the third axis
         * @param ref defines the Vector3 where to store the result
         */
        fun RotationFromAxisToRef(axis1: Vector3, axis2: Vector3, axis3: Vector3, ref: Vector3)
    }
}
/**
 * Vector4 class created for EulerAngle class conversion to Quaternion
 */
external class Vector4 {
    /** x value of the vector */
    val x: Number
    /** y value of the vector */
    val y: Number
    /** z value of the vector */
    val z: Number
    /** w value of the vector */
    val w: Number
    /**
     * Creates a Vector4 object from the given floats.
     * @param x x value of the vector
     * @param y y value of the vector
     * @param z z value of the vector
     * @param w w value of the vector
     */
    constructor(
        /** x value of the vector */
        x: Number,
        /** y value of the vector */
        y: Number,
        /** z value of the vector */
        z: Number,
        /** w value of the vector */
        w: Number)

    /**
     * Returns the String "Vector4".
     * @returns "Vector4"
     */
    fun getClassName(): String
    /**
     * Returns the Vector4 hash code.
     * @returns a unique hash code
     */
    fun getHashCode(): Number
    /**
     * Returns a new array populated with 4 elements : the Vector4 coordinates.
     * @returns the resulting array
     */
    fun asArray(): Array<Number>
    /**
     * Populates the given array from the given index with the Vector4 coordinates.
     * @param array array to populate
     * @param index index of the array to start at (default: 0)
     * @returns the Vector4.
     */
    fun toArray(array: FloatArray, index: Number?): Vector4
    /**
     * Adds the given vector to the current Vector4.
     * @param otherVector the vector to add
     * @returns the updated Vector4.
     */
    fun addInPlace(otherVector: Vector4): Vector4
    /**
     * Returns a new Vector4 as the result of the addition of the current Vector4 and the given one.
     * @param otherVector the vector to add
     * @returns the resulting vector
     */
    fun add(otherVector: Vector4): Vector4
    /**
     * Updates the given vector "result" with the result of the addition of the current Vector4 and the given one.
     * @param otherVector the vector to add
     * @param result the vector to store the result
     * @returns the current Vector4.
     */
    fun addToRef(otherVector: Vector4, result: Vector4): Vector4
    /**
     * Subtract in place the given vector from the current Vector4.
     * @param otherVector the vector to subtract
     * @returns the updated Vector4.
     */
    fun subtractInPlace(otherVector: Vector4): Vector4
    /**
     * Returns a new Vector4 with the result of the subtraction of the given vector from the current Vector4.
     * @param otherVector the vector to add
     * @returns the new vector with the result
     */
    fun subtract(otherVector: Vector4): Vector4
    /**
     * Sets the given vector "result" with the result of the subtraction of the given vector from the current Vector4.
     * @param otherVector the vector to subtract
     * @param result the vector to store the result
     * @returns the current Vector4.
     */
    fun subtractToRef(otherVector: Vector4, result: Vector4): Vector4
    /**
     * Returns a new Vector4 set with the result of the subtraction of the given floats from the current Vector4 coordinates.
     */
    /**
     * Returns a new Vector4 set with the result of the subtraction of the given floats from the current Vector4 coordinates.
     * @param x value to subtract
     * @param y value to subtract
     * @param z value to subtract
     * @param w value to subtract
     * @returns new vector containing the result
     */
    fun subtractFromFloats(x: Number, y: Number, z: Number, w: Number): Vector4
    /**
     * Sets the given vector "result" set with the result of the subtraction of the given floats from the current Vector4 coordinates.
     * @param x value to subtract
     * @param y value to subtract
     * @param z value to subtract
     * @param w value to subtract
     * @param result the vector to store the result in
     * @returns the current Vector4.
     */
    fun subtractFromFloatsToRef(x: Number, y: Number, z: Number, w: Number, result: Vector4): Vector4
    /**
     * Returns a new Vector4 set with the current Vector4 negated coordinates.
     * @returns a new vector with the negated values
     */
    fun negate(): Vector4
    /**
     * Multiplies the current Vector4 coordinates by scale (float).
     * @param scale the Number to scale with
     * @returns the updated Vector4.
     */
    fun scaleInPlace(scale: Number): Vector4
    /**
     * Returns a new Vector4 set with the current Vector4 coordinates multiplied by scale (float).
     * @param scale the Number to scale with
     * @returns a new vector with the result
     */
    fun scale(scale: Number): Vector4
    /**
     * Sets the given vector "result" with the current Vector4 coordinates multiplied by scale (float).
     * @param scale the Number to scale with
     * @param result a vector to store the result in
     * @returns the current Vector4.
     */
    fun scaleToRef(scale: Number, result: Vector4): Vector4
    /**
     * Scale the current Vector4 values by a factor and add the result to a given Vector4
     * @param scale defines the scale factor
     * @param result defines the Vector4 object where to store the result
     * @returns the unmodified current Vector4
     */
    fun scaleAndAddToRef(scale: Number, result: Vector4): Vector4
    /**
     * Boolean : True if the current Vector4 coordinates are stricly equal to the given ones.
     * @param otherVector the vector to compare against
     * @returns true if they are equal
     */
    fun equals(otherVector: Vector4): Boolean
    /**
     * Boolean : True if the current Vector4 coordinates are each beneath the distance "epsilon" from the given vector ones.
     * @param otherVector vector to compare against
     * @param epsilon (Default: very small Number)
     * @returns true if they are equal
     */
    fun equalsWithEpsilon(otherVector: Vector4, epsilon: Number?): Boolean
    /**
     * Boolean : True if the given floats are strictly equal to the current Vector4 coordinates.
     * @param x x value to compare against
     * @param y y value to compare against
     * @param z z value to compare against
     * @param w w value to compare against
     * @returns true if equal
     */
    fun equalsToFloats(x: Number, y: Number, z: Number, w: Number): Boolean
    /**
     * Multiplies in place the current Vector4 by the given one.
     * @param otherVector vector to multiple with
     * @returns the updated Vector4.
     */
    fun multiplyInPlace(otherVector: Vector4): Vector4
    /**
     * Returns a new Vector4 set with the multiplication result of the current Vector4 and the given one.
     * @param otherVector vector to multiple with
     * @returns resulting new vector
     */
    fun multiply(otherVector: Vector4): Vector4
    /**
     * Updates the given vector "result" with the multiplication result of the current Vector4 and the given one.
     * @param otherVector vector to multiple with
     * @param result vector to store the result
     * @returns the current Vector4.
     */
    fun multiplyToRef(otherVector: Vector4, result: Vector4): Vector4
    /**
     * Returns a new Vector4 set with the multiplication result of the given floats and the current Vector4 coordinates.
     * @param x x value multiply with
     * @param y y value multiply with
     * @param z z value multiply with
     * @param w w value multiply with
     * @returns resulting new vector
     */
    fun multiplyByFloats(x: Number, y: Number, z: Number, w: Number): Vector4
    /**
     * Returns a new Vector4 set with the division result of the current Vector4 by the given one.
     * @param otherVector vector to devide with
     * @returns resulting new vector
     */
    fun divide(otherVector: Vector4): Vector4
    /**
     * Updates the given vector "result" with the division result of the current Vector4 by the given one.
     * @param otherVector vector to devide with
     * @param result vector to store the result
     * @returns the current Vector4.
     */
    fun divideToRef(otherVector: Vector4, result: Vector4): Vector4
    /**
     * Divides the current Vector3 coordinates by the given ones.
     * @param otherVector vector to devide with
     * @returns the updated Vector3.
     */
    fun divideInPlace(otherVector: Vector4): Vector4
    /**
     * Updates the Vector4 coordinates with the minimum values between its own and the given vector ones
     * @param other defines the second operand
     * @returns the current updated Vector4
     */
    fun minimizeInPlace(other: Vector4): Vector4
    /**
     * Updates the Vector4 coordinates with the maximum values between its own and the given vector ones
     * @param other defines the second operand
     * @returns the current updated Vector4
     */
    fun maximizeInPlace(other: Vector4): Vector4
    /**
     * Gets a new Vector4 from current Vector4 floored values
     * @returns a new Vector4
     */
    fun floor(): Vector4
    /**
     * Gets a new Vector4 from current Vector3 floored values
     * @returns a new Vector4
     */
    fun fract(): Vector4
    /**
     * Returns the Vector4 length (float).
     * @returns the length
     */
    fun length(): Number
    /**
     * Returns the Vector4 squared length (float).
     * @returns the length squared
     */
    fun lengthSquared(): Number
    /**
     * Normalizes in place the Vector4.
     * @returns the updated Vector4.
     */
    fun normalize(): Vector4
    /**
     * Returns a new Vector3 from the Vector4 (x, y, z) coordinates.
     * @returns this converted to a new vector3
     */
    fun toVector3(): Vector3
    /**
     * Returns a new Vector4 copied from the current one.
     * @returns the new cloned vector
     */
    fun clone(): Vector4
    /**
     * Updates the current Vector4 with the given one coordinates.
     * @param source the source vector to copy from
     * @returns the updated Vector4.
     */
    fun copyFrom(source: Vector4): Vector4
    /**
     * Updates the current Vector4 coordinates with the given floats.
     * @param x float to copy from
     * @param y float to copy from
     * @param z float to copy from
     * @param w float to copy from
     * @returns the updated Vector4.
     */
    fun copyFromFloats(x: Number, y: Number, z: Number, w: Number): Vector4
    /**
     * Updates the current Vector4 coordinates with the given floats.
     * @param x float to set from
     * @param y float to set from
     * @param z float to set from
     * @param w float to set from
     * @returns the updated Vector4.
     */
    fun set(x: Number, y: Number, z: Number, w: Number): Vector4
    /**
     * Copies the given float to the current Vector3 coordinates
     * @param v defines the x, y, z and w coordinates of the operand
     * @returns the current updated Vector3
     */
    fun setAll(v: Number): Vector4

    companion object {
        /**
         * Returns a new Vector4 set from the starting index of the given array.
         * @param array the array to pull values from
         * @param offset the offset into the array to start at
         * @returns the new vector
         */
        fun FromArray(array: Collection<Number>, offset: Number?): Vector4
        /**
         * Updates the given vector "result" from the starting index of the given array.
         * @param array the array to pull values from
         * @param offset the offset into the array to start at
         * @param result the vector to store the result in
         */
        fun FromArrayToRef(array: Collection<Number>, offset: Number, result: Vector4)
        /**
         * Updates the given vector "result" from the starting index of the given Float32Array.
         * @param array the array to pull values from
         * @param offset the offset into the array to start at
         * @param result the vector to store the result in
         */
        fun FromFloatArrayToRef(array: Float32Array, offset: Number, result: Vector4)
        /**
         * Updates the given vector "result" coordinates from the given floats.
         * @param x float to set from
         * @param y float to set from
         * @param z float to set from
         * @param w float to set from
         * @param result the vector to the floats in
         */
        fun FromFloatsToRef(x: Number, y: Number, z: Number, w: Number, result: Vector4)
        /**
         * Returns a new Vector4 set to (0.0, 0.0, 0.0, 0.0)
         * @returns the new vector
         */
        fun Zero(): Vector4
        /**
         * Returns a new Vector4 set to (1.0, 1.0, 1.0, 1.0)
         * @returns the new vector
         */
        fun One(): Vector4
        /**
         * Returns a new normalized Vector4 from the given one.
         * @param vector the vector to normalize
         * @returns the vector
         */
        fun Normalize(vector: Vector4): Vector4
        /**
         * Updates the given vector "result" from the normalization of the given one.
         * @param vector the vector to normalize
         * @param result the vector to store the result in
         */
        fun NormalizeToRef(vector: Vector4, result: Vector4)
        /**
         * Returns a vector with the minimum values from the left and right vectors
         * @param left left vector to minimize
         * @param right right vector to minimize
         * @returns a new vector with the minimum of the left and right vector values
         */
        fun Minimize(left: Vector4, right: Vector4): Vector4
        /**
         * Returns a vector with the maximum values from the left and right vectors
         * @param left left vector to maximize
         * @param right right vector to maximize
         * @returns a new vector with the maximum of the left and right vector values
         */
        fun Maximize(left: Vector4, right: Vector4): Vector4
        /**
         * Returns the distance (float) between the vectors "value1" and "value2".
         * @param value1 value to calulate the distance between
         * @param value2 value to calulate the distance between
         * @return the distance between the two vectors
         */
        fun Distance(value1: Vector4, value2: Vector4): Number
        /**
         * Returns the squared distance (float) between the vectors "value1" and "value2".
         * @param value1 value to calulate the distance between
         * @param value2 value to calulate the distance between
         * @return the distance between the two vectors squared
         */
        fun DistanceSquared(value1: Vector4, value2: Vector4): Number
        /**
         * Returns a new Vector4 located at the center between the vectors "value1" and "value2".
         * @param value1 value to calulate the center between
         * @param value2 value to calulate the center between
         * @return the center between the two vectors
         */
        fun Center(value1: Vector4, value2: Vector4): Vector4
        /**
         * Returns a new Vector4 set with the result of the normal transformation by the given matrix of the given vector.
         * This methods computes transformed normalized direction vectors only.
         * @param vector the vector to transform
         * @param transformation the transformation matrix to apply
         * @returns the new vector
         */
        fun TransformNormal(vector: Vector4, transformation: Matrix): Vector4
        /**
         * Sets the given vector "result" with the result of the normal transformation by the given matrix of the given vector.
         * This methods computes transformed normalized direction vectors only.
         * @param vector the vector to transform
         * @param transformation the transformation matrix to apply
         * @param result the vector to store the result in
         */
        fun TransformNormalToRef(vector: Vector4, transformation: Matrix, result: Vector4)
        /**
         * Sets the given vector "result" with the result of the normal transformation by the given matrix of the given floats (x, y, z, w).
         * This methods computes transformed normalized direction vectors only.
         * @param x value to transform
         * @param y value to transform
         * @param z value to transform
         * @param w value to transform
         * @param transformation the transformation matrix to apply
         * @param result the vector to store the results in
         */
        fun TransformNormalFromFloatsToRef(x: Number, y: Number, z: Number, w: Number, transformation: Matrix, result: Vector4)
        /**
         * Creates a new Vector4 from a Vector3
         * @param source defines the source data
         * @param w defines the 4th component (default is 0)
         * @returns a new Vector4
         */
        fun FromVector3(source: Vector3, w: Number?): Vector4
    }
}
/**
 * Interface for the size containing width and height
 */
external interface ISize {
    /**
     * Width
     */
    val width: Number
    /**
     * Heighht
     */
    val height: Number
}
/**
 * Size containing widht and height
 */
external class Size: ISize {
    /**
     * Width
     */
    override val width: Number
    /**
     * Height
     */
    override  val height: Number
    /**
     * Creates a Size object from the given width and height (floats).
     * @param width width of the new size
     * @param height height of the new size
     */
    constructor(width: Number, height: Number)

    /**
     * "Size"
     * @returns the String "Size"
     */
    fun getClassName(): String
    /**
     * Returns the Size hash code.
     * @returns a hash code for a unique width and height
     */
    fun getHashCode(): Number
    /**
     * Updates the current size from the given one.
     * @param src the given size
     */
    fun copyFrom(src: Size)
    /**
     * Updates in place the current Size from the given floats.
     * @param width width of the new size
     * @param height height of the new size
     * @returns the updated Size.
     */
    fun copyFromFloats(width: Number, height: Number): Size
    /**
     * Updates in place the current Size from the given floats.
     * @param width width to set
     * @param height height to set
     * @returns the updated Size.
     */
    fun set(width: Number, height: Number): Size
    /**
     * Multiplies the width and height by numbers
     * @param w factor to multiple the width by
     * @param h factor to multiple the height by
     * @returns a new Size set with the multiplication result of the current Size and the given floats.
     */
    fun multiplyByFloats(w: Number, h: Number): Size
    /**
     * Clones the size
     * @returns a new Size copied from the given one.
     */
    fun clone(): Size
    /**
     * True if the current Size and the given one width and height are strictly equal.
     * @param other the other size to compare against
     * @returns True if the current Size and the given one width and height are strictly equal.
     */
    fun equals(other: Size): Boolean

    /**
     * The surface of the Size : width * height (float).
     */
    val surface: Number

    /**
     * Sums the width and height of two sizes
     * @param otherSize size to add to this size
     * @returns a new Size set as the addition result of the current Size and the given one.
     */
    fun add(otherSize: Size): Size
    /**
     * Subtracts the width and height of two
     * @param otherSize size to subtract to this size
     * @returns a new Size set as the subtraction result of  the given one from the current Size.
     */
    fun subtract(otherSize: Size): Size

    companion object {
        /**
         * Create a new size of zero
         * @returns a new Size set to (0.0, 0.0)
         */
        fun Zero(): Size
        /**
         * Creates a new Size set at the linear interpolation "amount" between "start" and "end"
         * @param start starting size to lerp between
         * @param end end size to lerp between
         * @param amount amount to lerp between the start and end values
         * @returns a new Size set at the linear interpolation "amount" between "start" and "end"
         */
        fun Lerp(start: Size, end: Size, amount: Number): Size
    }
}
/**
 * Class used to store quaternion data
 * @see https://en.wikipedia.org/wiki/Quaternion
 * @see http://doc.babylonjs.com/features/position,_rotation,_scaling
 */
external class Quaternion {
    /** defines the first component (0 by default) */
    val x: Number
    /** defines the second component (0 by default) */
    val y: Number
    /** defines the third component (0 by default) */
    val z: Number
    /** defines the fourth component (1.0 by default) */
    val w: Number
    /**
     * Creates a new Quaternion from the given floats
     * @param x defines the first component (0 by default)
     * @param y defines the second component (0 by default)
     * @param z defines the third component (0 by default)
     * @param w defines the fourth component (1.0 by default)
     */
    constructor(
        /** defines the first component (0 by default) */
        x: Number?,
        /** defines the second component (0 by default) */
        y: Number?,
        /** defines the third component (0 by default) */
        z: Number?,
        /** defines the fourth component (1.0 by default) */
        w: Number?)

    /**
     * Gets the class name of the quaternion
     * @returns the String "Quaternion"
     */
    fun getClassName(): String
    /**
     * Gets a hash code for this quaternion
     * @returns the quaternion hash code
     */
    fun getHashCode(): Number
    /**
     * Copy the quaternion to an array
     * @returns a new array populated with 4 elements from the quaternion coordinates
     */
    fun asArray(): Array<Number>
    /**
     * Check if two quaternions are equals
     * @param otherQuaternion defines the second operand
     * @return true if the current quaternion and the given one coordinates are strictly equals
     */
    fun equals(otherQuaternion: Quaternion): Boolean
    /**
     * Clone the current quaternion
     * @returns a new quaternion copied from the current one
     */
    fun clone(): Quaternion
    /**
     * Copy a quaternion to the current one
     * @param other defines the other quaternion
     * @returns the updated current quaternion
     */
    fun copyFrom(other: Quaternion): Quaternion
    /**
     * Updates the current quaternion with the given float coordinates
     * @param x defines the x coordinate
     * @param y defines the y coordinate
     * @param z defines the z coordinate
     * @param w defines the w coordinate
     * @returns the updated current quaternion
     */
    fun copyFromFloats(x: Number, y: Number, z: Number, w: Number): Quaternion
    /**
     * Updates the current quaternion from the given float coordinates
     * @param x defines the x coordinate
     * @param y defines the y coordinate
     * @param z defines the z coordinate
     * @param w defines the w coordinate
     * @returns the updated current quaternion
     */
    fun set(x: Number, y: Number, z: Number, w: Number): Quaternion
    /**
     * Adds two quaternions
     * @param other defines the second operand
     * @returns a new quaternion as the addition result of the given one and the current quaternion
     */
    fun add(other: Quaternion): Quaternion
    /**
     * Add a quaternion to the current one
     * @param other defines the quaternion to add
     * @returns the current quaternion
     */
    fun addInPlace(other: Quaternion): Quaternion
    /**
     * Subtract two quaternions
     * @param other defines the second operand
     * @returns a new quaternion as the subtraction result of the given one from the current one
     */
    fun subtract(other: Quaternion): Quaternion
    /**
     * Multiplies the current quaternion by a scale factor
     * @param value defines the scale factor
     * @returns a new quaternion set by multiplying the current quaternion coordinates by the float "scale"
     */
    fun scale(value: Number): Quaternion
    /**
     * Scale the current quaternion values by a factor and stores the result to a given quaternion
     * @param scale defines the scale factor
     * @param result defines the Quaternion object where to store the result
     * @returns the unmodified current quaternion
     */
    fun scaleToRef(scale: Number, result: Quaternion): Quaternion
    /**
     * Multiplies in place the current quaternion by a scale factor
     * @param value defines the scale factor
     * @returns the current modified quaternion
     */
    fun scaleInPlace(value: Number): Quaternion
    /**
     * Scale the current quaternion values by a factor and add the result to a given quaternion
     * @param scale defines the scale factor
     * @param result defines the Quaternion object where to store the result
     * @returns the unmodified current quaternion
     */
    fun scaleAndAddToRef(scale: Number, result: Quaternion): Quaternion
    /**
     * Multiplies two quaternions
     * @param q1 defines the second operand
     * @returns a new quaternion set as the multiplication result of the current one with the given one "q1"
     */
    fun multiply(q1: Quaternion): Quaternion
    /**
     * Sets the given "result" as the the multiplication result of the current one with the given one "q1"
     * @param q1 defines the second operand
     * @param result defines the target quaternion
     * @returns the current quaternion
     */
    fun multiplyToRef(q1: Quaternion, result: Quaternion): Quaternion
    /**
     * Updates the current quaternion with the multiplication of itself with the given one "q1"
     * @param q1 defines the second operand
     * @returns the currentupdated quaternion
     */
    fun multiplyInPlace(q1: Quaternion): Quaternion
    /**
     * Conjugates (1-q) the current quaternion and stores the result in the given quaternion
     * @param ref defines the target quaternion
     * @returns the current quaternion
     */
    fun conjugateToRef(ref: Quaternion): Quaternion
    /**
     * Conjugates in place (1-q) the current quaternion
     * @returns the current updated quaternion
     */
    fun conjugateInPlace(): Quaternion
    /**
     * Conjugates in place (1-q) the current quaternion
     * @returns a new quaternion
     */
    fun conjugate(): Quaternion
    /**
     * Gets length of current quaternion
     * @returns the quaternion length (float)
     */
    fun length(): Number
    /**
     * Normalize in place the current quaternion
     * @returns the current updated quaternion
     */
    fun normalize(): Quaternion
    /**
     * Returns a new Vector3 set with the Euler angles translated from the current quaternion
     * @param order is a reserved parameter and is ignore for now
     * @returns a new Vector3 containing the Euler angles
     */
    fun toEulerAngles(order: String?): Vector3
    /**
     * Sets the given vector3 "result" with the Euler angles translated from the current quaternion
     * @param result defines the vector which will be filled with the Euler angles
     * @param order is a reserved parameter and is ignore for now
     * @returns the current unchanged quaternion
     */
    fun toEulerAnglesToRef(result: Vector3): Quaternion
    /**
     * Updates the given rotation matrix with the current quaternion values
     * @param result defines the target matrix
     * @returns the current unchanged quaternion
     */
    fun toRotationMatrix(result: Matrix): Quaternion
    /**
     * Updates the current quaternion from the given rotation matrix values
     * @param matrix defines the source matrix
     * @returns the current updated quaternion
     */
    fun fromRotationMatrix(matrix: Matrix): Quaternion

    companion object {
        /**
         * Creates a new quaternion from a rotation matrix
         * @param matrix defines the source matrix
         * @returns a new quaternion created from the given rotation matrix values
         */
        fun FromRotationMatrix(matrix: Matrix): Quaternion
        /**
         * Updates the given quaternion with the given rotation matrix values
         * @param matrix defines the source matrix
         * @param result defines the target quaternion
         */
        fun FromRotationMatrixToRef(matrix: Matrix, result: Quaternion)
        /**
         * Returns the dot product (float) between the quaternions "left" and "right"
         * @param left defines the left operand
         * @param right defines the right operand
         * @returns the dot product
         */
        fun Dot(left: Quaternion, right: Quaternion): Number
        /**
         * Checks if the two quaternions are close to each other
         * @param quat0 defines the first quaternion to check
         * @param quat1 defines the second quaternion to check
         * @returns true if the two quaternions are close to each other
         */
        fun AreClose(quat0: Quaternion, quat1: Quaternion): Boolean
        /**
         * Creates an empty quaternion
         * @returns a new quaternion set to (0.0, 0.0, 0.0)
         */
        fun Zero(): Quaternion
        /**
         * Inverse a given quaternion
         * @param q defines the source quaternion
         * @returns a new quaternion as the inverted current quaternion
         */
        fun Inverse(q: Quaternion): Quaternion
        /**
         * Inverse a given quaternion
         * @param q defines the source quaternion
         * @param result the quaternion the result will be stored in
         * @returns the result quaternion
         */
        fun InverseToRef(q: Quaternion, result: Quaternion): Quaternion
        /**
         * Creates an identity quaternion
         * @returns the identity quaternion
         */
        fun Identity(): Quaternion
        /**
         * Gets a Boolean indicating if the given quaternion is identity
         * @param quaternion defines the quaternion to check
         * @returns true if the quaternion is identity
         */
        fun IsIdentity(quaternion: Quaternion): Boolean
        /**
         * Creates a quaternion from a rotation around an axis
         * @param axis defines the axis to use
         * @param angle defines the angle to use
         * @returns a new quaternion created from the given axis (Vector3) and angle in radians (float)
         */
        fun RotationAxis(axis: Vector3, angle: Number): Quaternion
        /**
         * Creates a rotation around an axis and stores it into the given quaternion
         * @param axis defines the axis to use
         * @param angle defines the angle to use
         * @param result defines the target quaternion
         * @returns the target quaternion
         */
        fun RotationAxisToRef(axis: Vector3, angle: Number, result: Quaternion): Quaternion
        /**
         * Creates a new quaternion from data stored into an array
         * @param array defines the data source
         * @param offset defines the offset in the source array where the data starts
         * @returns a new quaternion
         */
        fun FromArray(array: Collection<Number>, offset: Number?): Quaternion
        /**
         * Create a quaternion from Euler rotation angles
         * @param x Pitch
         * @param y Yaw
         * @param z Roll
         * @returns the new Quaternion
         */
        fun FromEulerAngles(x: Number, y: Number, z: Number): Quaternion
        /**
         * Updates a quaternion from Euler rotation angles
         * @param x Pitch
         * @param y Yaw
         * @param z Roll
         * @param result the quaternion to store the result
         * @returns the updated quaternion
         */
        fun FromEulerAnglesToRef(x: Number, y: Number, z: Number, result: Quaternion): Quaternion
        /**
         * Create a quaternion from Euler rotation vector
         * @param vec the Euler vector (x Pitch, y Yaw, z Roll)
         * @returns the new Quaternion
         */
        fun FromEulerVector(vec: Vector3): Quaternion
        /**
         * Updates a quaternion from Euler rotation vector
         * @param vec the Euler vector (x Pitch, y Yaw, z Roll)
         * @param result the quaternion to store the result
         * @returns the updated quaternion
         */
        fun FromEulerVectorToRef(vec: Vector3, result: Quaternion): Quaternion
        /**
         * Creates a new quaternion from the given Euler float angles (y, x, z)
         * @param yaw defines the rotation around Y axis
         * @param pitch defines the rotation around X axis
         * @param roll defines the rotation around Z axis
         * @returns the new quaternion
         */
        fun RotationYawPitchRoll(yaw: Number, pitch: Number, roll: Number): Quaternion
        /**
         * Creates a new rotation from the given Euler float angles (y, x, z) and stores it in the target quaternion
         * @param yaw defines the rotation around Y axis
         * @param pitch defines the rotation around X axis
         * @param roll defines the rotation around Z axis
         * @param result defines the target quaternion
         */
        fun RotationYawPitchRollToRef(yaw: Number, pitch: Number, roll: Number, result: Quaternion)
        /**
         * Creates a new quaternion from the given Euler float angles expressed in z-x-z orientation
         * @param alpha defines the rotation around first axis
         * @param beta defines the rotation around second axis
         * @param gamma defines the rotation around third axis
         * @returns the new quaternion
         */
        fun RotationAlphaBetaGamma(alpha: Number, beta: Number, gamma: Number): Quaternion
        /**
         * Creates a new quaternion from the given Euler float angles expressed in z-x-z orientation and stores it in the target quaternion
         * @param alpha defines the rotation around first axis
         * @param beta defines the rotation around second axis
         * @param gamma defines the rotation around third axis
         * @param result defines the target quaternion
         */
        fun RotationAlphaBetaGammaToRef(alpha: Number, beta: Number, gamma: Number, result: Quaternion)
        /**
         * Creates a new quaternion containing the rotation value to reach the target (axis1, axis2, axis3) orientation as a rotated XYZ system (axis1, axis2 and axis3 are normalized during this operation)
         * @param axis1 defines the first axis
         * @param axis2 defines the second axis
         * @param axis3 defines the third axis
         * @returns the new quaternion
         */
        fun RotationQuaternionFromAxis(axis1: Vector3, axis2: Vector3, axis3: Vector3): Quaternion
        /**
         * Creates a rotation value to reach the target (axis1, axis2, axis3) orientation as a rotated XYZ system (axis1, axis2 and axis3 are normalized during this operation) and stores it in the target quaternion
         * @param axis1 defines the first axis
         * @param axis2 defines the second axis
         * @param axis3 defines the third axis
         * @param ref defines the target quaternion
         */
        fun RotationQuaternionFromAxisToRef(axis1: Vector3, axis2: Vector3, axis3: Vector3, ref: Quaternion)
        /**
         * Interpolates between two quaternions
         * @param left defines first quaternion
         * @param right defines second quaternion
         * @param amount defines the gradient to use
         * @returns the new interpolated quaternion
         */
        fun Slerp(left: Quaternion, right: Quaternion, amount: Number): Quaternion
        /**
         * Interpolates between two quaternions and stores it into a target quaternion
         * @param left defines first quaternion
         * @param right defines second quaternion
         * @param amount defines the gradient to use
         * @param result defines the target quaternion
         */
        fun SlerpToRef(left: Quaternion, right: Quaternion, amount: Number, result: Quaternion)
        /**
         * Interpolate between two quaternions using Hermite interpolation
         * @param value1 defines first quaternion
         * @param tangent1 defines the incoming tangent
         * @param value2 defines second quaternion
         * @param tangent2 defines the outgoing tangent
         * @param amount defines the target quaternion
         * @returns the new interpolated quaternion
         */
        fun Hermite(value1: Quaternion, tangent1: Quaternion, value2: Quaternion, tangent2: Quaternion, amount: Number): Quaternion
    }
}
/**
 * Class used to store matrix data (4x4)
 */
external class Matrix {
    /**
     * Gets the update flag of the matrix which is an unique Number for the matrix.
     * It will be incremented every time the matrix data change.
     * You can use it to speed the comparison between two versions of the same matrix.
     */
    val updateFlag: Number
    /**
     * Gets the internal data of the matrix
     */
    val m: Float32Array
    /**
     * Creates an empty matrix (filled with zeros)
     */
    constructor()
    /**
     * Check if the current matrix is identity
     * @returns true is the matrix is the identity matrix
     */
    fun isIdentity(): Boolean
    /**
     * Check if the current matrix is identity as a texture matrix (3x2 store in 4x4)
     * @returns true is the matrix is the identity matrix
     */
    fun isIdentityAs3x2(): Boolean
    /**
     * Gets the determinant of the matrix
     * @returns the matrix determinant
     */
    fun determinant(): Number
    /**
     * Returns the matrix as a Float32Array
     * @returns the matrix underlying array
     */
    fun toArray(): Float32Array
    /**
     * Returns the matrix as a Float32Array
     * @returns the matrix underlying array.
     */
    fun asArray(): Float32Array
    /**
     * Inverts the current matrix in place
     * @returns the current inverted matrix
     */
    fun invert(): Matrix
    /**
     * Sets all the matrix elements to zero
     * @returns the current matrix
     */
    fun reset(): Matrix
    /**
     * Adds the current matrix with a second one
     * @param other defines the matrix to add
     * @returns a new matrix as the addition of the current matrix and the given one
     */
    fun add(other: Matrix): Matrix
    /**
     * Sets the given matrix "result" to the addition of the current matrix and the given one
     * @param other defines the matrix to add
     * @param result defines the target matrix
     * @returns the current matrix
     */
    fun addToRef(other: Matrix, result: Matrix): Matrix
    /**
     * Adds in place the given matrix to the current matrix
     * @param other defines the second operand
     * @returns the current updated matrix
     */
    fun addToSelf(other: Matrix): Matrix
    /**
     * Sets the given matrix to the current inverted Matrix
     * @param other defines the target matrix
     * @returns the unmodified current matrix
     */
    fun invertToRef(other: Matrix): Matrix
    /**
     * add a value at the specified position in the current Matrix
     * @param index the index of the value within the matrix. between 0 and 15.
     * @param value the value to be added
     * @returns the current updated matrix
     */
    fun addAtIndex(index: Number, value: Number): Matrix
    /**
     * mutiply the specified position in the current Matrix by a value
     * @param index the index of the value within the matrix. between 0 and 15.
     * @param value the value to be added
     * @returns the current updated matrix
     */
    fun multiplyAtIndex(index: Number, value: Number): Matrix
    /**
     * Inserts the translation vector (using 3 floats) in the current matrix
     * @param x defines the 1st component of the translation
     * @param y defines the 2nd component of the translation
     * @param z defines the 3rd component of the translation
     * @returns the current updated matrix
     */
    fun setTranslationFromFloats(x: Number, y: Number, z: Number): Matrix
    /**
     * Adds the translation vector (using 3 floats) in the current matrix
     * @param x defines the 1st component of the translation
     * @param y defines the 2nd component of the translation
     * @param z defines the 3rd component of the translation
     * @returns the current updated matrix
     */
    fun addTranslationFromFloats(x: Number, y: Number, z: Number): Matrix
    /**
     * Inserts the translation vector in the current matrix
     * @param vector3 defines the translation to insert
     * @returns the current updated matrix
     */
    fun setTranslation(vector3: Vector3): Matrix
    /**
     * Gets the translation value of the current matrix
     * @returns a new Vector3 as the extracted translation from the matrix
     */
    fun getTranslation(): Vector3
    /**
     * Fill a Vector3 with the extracted translation from the matrix
     * @param result defines the Vector3 where to store the translation
     * @returns the current matrix
     */
    fun getTranslationToRef(result: Vector3): Matrix
    /**
     * Remove rotation and scaling part from the matrix
     * @returns the updated matrix
     */
    fun removeRotationAndScaling(): Matrix
    /**
     * Multiply two matrices
     * @param other defines the second operand
     * @returns a new matrix set with the multiplication result of the current Matrix and the given one
     */
    fun multiply(other: Matrix): Matrix
    /**
     * Copy the current matrix from the given one
     * @param other defines the source matrix
     * @returns the current updated matrix
     */
    fun copyFrom(other: Matrix): Matrix
    /**
     * Populates the given array from the starting index with the current matrix values
     * @param array defines the target array
     * @param offset defines the offset in the target array where to start storing values
     * @returns the current matrix
     */
    fun copyToArray(array: Float32Array, offset: Number?): Matrix
    /**
     * Sets the given matrix "result" with the multiplication result of the current Matrix and the given one
     * @param other defines the second operand
     * @param result defines the matrix where to store the multiplication
     * @returns the current matrix
     */
    fun multiplyToRef(other: Matrix, result: Matrix): Matrix
    /**
     * Sets the Float32Array "result" from the given index "offset" with the multiplication of the current matrix and the given one
     * @param other defines the second operand
     * @param result defines the array where to store the multiplication
     * @param offset defines the offset in the target array where to start storing values
     * @returns the current matrix
     */
    fun multiplyToArray(other: Matrix, result: Float32Array, offset: Number): Matrix
    /**
     * Check equality between this matrix and a second one
     * @param value defines the second matrix to compare
     * @returns true is the current matrix and the given one values are strictly equal
     */
    fun equals(value: Matrix): Boolean
    /**
     * Clone the current matrix
     * @returns a new matrix from the current matrix
     */
    fun clone(): Matrix
    /**
     * Returns the name of the current matrix class
     * @returns the String "Matrix"
     */
    fun getClassName(): String
    /**
     * Gets the hash code of the current matrix
     * @returns the hash code
     */
    fun getHashCode(): Number
    /**
     * Decomposes the current Matrix into a translation, rotation and scaling components
     * @param scale defines the scale vector3 given as a reference to update
     * @param rotation defines the rotation quaternion given as a reference to update
     * @param translation defines the translation vector3 given as a reference to update
     * @returns true if operation was successful
     */
    fun decompose(scale: Vector3?, rotation: Quaternion?, translation: Vector3?): Boolean
    /**
     * Gets specific row of the matrix
     * @param index defines the Number of the row to get
     * @returns the index-th row of the current matrix as a new Vector4
     */
    fun getRow(index: Number): Vector4?
    /**
     * Sets the index-th row of the current matrix to the vector4 values
     * @param index defines the Number of the row to set
     * @param row defines the target vector4
     * @returns the updated current matrix
     */
    fun setRow(index: Number, row: Vector4): Matrix
    /**
     * Compute the transpose of the matrix
     * @returns the new transposed matrix
     */
    fun transpose(): Matrix
    /**
     * Compute the transpose of the matrix and store it in a given matrix
     * @param result defines the target matrix
     * @returns the current matrix
     */
    fun transposeToRef(result: Matrix): Matrix
    /**
     * Sets the index-th row of the current matrix with the given 4 x float values
     * @param index defines the row index
     * @param x defines the x component to set
     * @param y defines the y component to set
     * @param z defines the z component to set
     * @param w defines the w component to set
     * @returns the updated current matrix
     */
    fun setRowFromFloats(index: Number, x: Number, y: Number, z: Number, w: Number): Matrix
    /**
     * Compute a new matrix set with the current matrix values multiplied by scale (float)
     * @param scale defines the scale factor
     * @returns a new matrix
     */
    fun scale(scale: Number): Matrix
    /**
     * Scale the current matrix values by a factor to a given result matrix
     * @param scale defines the scale factor
     * @param result defines the matrix to store the result
     * @returns the current matrix
     */
    fun scaleToRef(scale: Number, result: Matrix): Matrix
    /**
     * Scale the current matrix values by a factor and add the result to a given matrix
     * @param scale defines the scale factor
     * @param result defines the Matrix to store the result
     * @returns the current matrix
     */
    fun scaleAndAddToRef(scale: Number, result: Matrix): Matrix
    /**
     * Writes to the given matrix a normal matrix, computed from this one (using values from identity matrix for fourth row and column).
     * @param ref matrix to store the result
     */
    fun toNormalMatrix(ref: Matrix)
    /**
     * Gets only rotation part of the current matrix
     * @returns a new matrix sets to the extracted rotation matrix from the current one
     */
    fun getRotationMatrix(): Matrix
    /**
     * Extracts the rotation matrix from the current one and sets it as the given "result"
     * @param result defines the target matrix to store data to
     * @returns the current matrix
     */
    fun getRotationMatrixToRef(result: Matrix): Matrix
    /**
     * Toggles model matrix from being right handed to left handed in place and vice versa
     */
    fun toggleModelMatrixHandInPlace()
    /**
     * Toggles projection matrix from being right handed to left handed in place and vice versa
     */
    fun toggleProjectionMatrixHandInPlace()

    companion object {
        /**
         * Creates a matrix from an array
         * @param array defines the source array
         * @param offset defines an offset in the source array
         * @returns a new Matrix set from the starting index of the given array
         */
        fun FromArray(array: Collection<Number>, offset: Number?): Matrix
        /**
         * Copy the content of an array into a given matrix
         * @param array defines the source array
         * @param offset defines an offset in the source array
         * @param result defines the target matrix
         */
        fun FromArrayToRef(array: Collection<Number>, offset: Number, result: Matrix)
        /**
         * Stores an array into a matrix after having multiplied each component by a given factor
         * @param array defines the source array
         * @param offset defines the offset in the source array
         * @param scale defines the scaling factor
         * @param result defines the target matrix
         */
        fun FromFloat32ArrayToRefScaled(array: Float32Array, offset: Number, scale: Number, result: Matrix)
        /**
         * Gets an identity matrix that must not be updated
         */
        val IdentityReadOnly: Matrix
        /**
         * Stores a list of values (16) inside a given matrix
         * @param initialM11 defines 1st value of 1st row
         * @param initialM12 defines 2nd value of 1st row
         * @param initialM13 defines 3rd value of 1st row
         * @param initialM14 defines 4th value of 1st row
         * @param initialM21 defines 1st value of 2nd row
         * @param initialM22 defines 2nd value of 2nd row
         * @param initialM23 defines 3rd value of 2nd row
         * @param initialM24 defines 4th value of 2nd row
         * @param initialM31 defines 1st value of 3rd row
         * @param initialM32 defines 2nd value of 3rd row
         * @param initialM33 defines 3rd value of 3rd row
         * @param initialM34 defines 4th value of 3rd row
         * @param initialM41 defines 1st value of 4th row
         * @param initialM42 defines 2nd value of 4th row
         * @param initialM43 defines 3rd value of 4th row
         * @param initialM44 defines 4th value of 4th row
         * @param result defines the target matrix
         */
        fun FromValuesToRef(initialM11: Number, initialM12: Number, initialM13: Number, initialM14: Number, initialM21: Number, initialM22: Number, initialM23: Number, initialM24: Number, initialM31: Number, initialM32: Number, initialM33: Number, initialM34: Number, initialM41: Number, initialM42: Number, initialM43: Number, initialM44: Number, result: Matrix)
        /**
         * Creates new matrix from a list of values (16)
         * @param initialM11 defines 1st value of 1st row
         * @param initialM12 defines 2nd value of 1st row
         * @param initialM13 defines 3rd value of 1st row
         * @param initialM14 defines 4th value of 1st row
         * @param initialM21 defines 1st value of 2nd row
         * @param initialM22 defines 2nd value of 2nd row
         * @param initialM23 defines 3rd value of 2nd row
         * @param initialM24 defines 4th value of 2nd row
         * @param initialM31 defines 1st value of 3rd row
         * @param initialM32 defines 2nd value of 3rd row
         * @param initialM33 defines 3rd value of 3rd row
         * @param initialM34 defines 4th value of 3rd row
         * @param initialM41 defines 1st value of 4th row
         * @param initialM42 defines 2nd value of 4th row
         * @param initialM43 defines 3rd value of 4th row
         * @param initialM44 defines 4th value of 4th row
         * @returns the new matrix
         */
        fun FromValues(initialM11: Number, initialM12: Number, initialM13: Number, initialM14: Number, initialM21: Number, initialM22: Number, initialM23: Number, initialM24: Number, initialM31: Number, initialM32: Number, initialM33: Number, initialM34: Number, initialM41: Number, initialM42: Number, initialM43: Number, initialM44: Number): Matrix
        /**
         * Creates a new matrix composed by merging scale (vector3), rotation (quaternion) and translation (vector3)
         * @param scale defines the scale vector3
         * @param rotation defines the rotation quaternion
         * @param translation defines the translation vector3
         * @returns a new matrix
         */
        fun Compose(scale: Vector3, rotation: Quaternion, translation: Vector3): Matrix
        /**
         * Sets a matrix to a value composed by merging scale (vector3), rotation (quaternion) and translation (vector3)
         * @param scale defines the scale vector3
         * @param rotation defines the rotation quaternion
         * @param translation defines the translation vector3
         * @param result defines the target matrix
         */
        fun ComposeToRef(scale: Vector3, rotation: Quaternion, translation: Vector3, result: Matrix)
        /**
         * Creates a new identity matrix
         * @returns a new identity matrix
         */
        fun Identity(): Matrix
        /**
         * Creates a new identity matrix and stores the result in a given matrix
         * @param result defines the target matrix
         */
        fun IdentityToRef(result: Matrix)
        /**
         * Creates a new zero matrix
         * @returns a new zero matrix
         */
        fun Zero(): Matrix
        /**
         * Creates a new rotation matrix for "angle" radians around the X axis
         * @param angle defines the angle (in radians) to use
         * @return the new matrix
         */
        fun RotationX(angle: Number): Matrix
        /**
         * Creates a new matrix as the invert of a given matrix
         * @param source defines the source matrix
         * @returns the new matrix
         */
        fun Invert(source: Matrix): Matrix
        /**
         * Creates a new rotation matrix for "angle" radians around the X axis and stores it in a given matrix
         * @param angle defines the angle (in radians) to use
         * @param result defines the target matrix
         */
        fun RotationXToRef(angle: Number, result: Matrix)
        /**
         * Creates a new rotation matrix for "angle" radians around the Y axis
         * @param angle defines the angle (in radians) to use
         * @return the new matrix
         */
        fun RotationY(angle: Number): Matrix
        /**
         * Creates a new rotation matrix for "angle" radians around the Y axis and stores it in a given matrix
         * @param angle defines the angle (in radians) to use
         * @param result defines the target matrix
         */
        fun RotationYToRef(angle: Number, result: Matrix)
        /**
         * Creates a new rotation matrix for "angle" radians around the Z axis
         * @param angle defines the angle (in radians) to use
         * @return the new matrix
         */
        fun RotationZ(angle: Number): Matrix
        /**
         * Creates a new rotation matrix for "angle" radians around the Z axis and stores it in a given matrix
         * @param angle defines the angle (in radians) to use
         * @param result defines the target matrix
         */
        fun RotationZToRef(angle: Number, result: Matrix)
        /**
         * Creates a new rotation matrix for "angle" radians around the given axis
         * @param axis defines the axis to use
         * @param angle defines the angle (in radians) to use
         * @return the new matrix
         */
        fun RotationAxis(axis: Vector3, angle: Number): Matrix
        /**
         * Creates a new rotation matrix for "angle" radians around the given axis and stores it in a given matrix
         * @param axis defines the axis to use
         * @param angle defines the angle (in radians) to use
         * @param result defines the target matrix
         */
        fun RotationAxisToRef(axis: Vector3, angle: Number, result: Matrix)
        /**
         * Takes normalised vectors and returns a rotation matrix to align "from" with "to".
         * Taken from http://www.iquilezles.org/www/articles/noacos/noacos.htm
         * @param from defines the vector to align
         * @param to defines the vector to align to
         * @param result defines the target matrix
         */
        fun RotationAlignToRef(from: Vector3, to: Vector3, result: Matrix)
        /**
         * Creates a rotation matrix
         * @param yaw defines the yaw angle in radians (Y axis)
         * @param pitch defines the pitch angle in radians (X axis)
         * @param roll defines the roll angle in radians (X axis)
         * @returns the new rotation matrix
         */
        fun RotationYawPitchRoll(yaw: Number, pitch: Number, roll: Number): Matrix
        /**
         * Creates a rotation matrix and stores it in a given matrix
         * @param yaw defines the yaw angle in radians (Y axis)
         * @param pitch defines the pitch angle in radians (X axis)
         * @param roll defines the roll angle in radians (X axis)
         * @param result defines the target matrix
         */
        fun RotationYawPitchRollToRef(yaw: Number, pitch: Number, roll: Number, result: Matrix)
        /**
         * Creates a scaling matrix
         * @param x defines the scale factor on X axis
         * @param y defines the scale factor on Y axis
         * @param z defines the scale factor on Z axis
         * @returns the new matrix
         */
        fun Scaling(x: Number, y: Number, z: Number): Matrix
        /**
         * Creates a scaling matrix and stores it in a given matrix
         * @param x defines the scale factor on X axis
         * @param y defines the scale factor on Y axis
         * @param z defines the scale factor on Z axis
         * @param result defines the target matrix
         */
        fun ScalingToRef(x: Number, y: Number, z: Number, result: Matrix)
        /**
         * Creates a translation matrix
         * @param x defines the translation on X axis
         * @param y defines the translation on Y axis
         * @param z defines the translationon Z axis
         * @returns the new matrix
         */
        fun Translation(x: Number, y: Number, z: Number): Matrix
        /**
         * Creates a translation matrix and stores it in a given matrix
         * @param x defines the translation on X axis
         * @param y defines the translation on Y axis
         * @param z defines the translationon Z axis
         * @param result defines the target matrix
         */
        fun TranslationToRef(x: Number, y: Number, z: Number, result: Matrix)
        /**
         * Returns a new Matrix whose values are the interpolated values for "gradient" (float) between the ones of the matrices "startValue" and "endValue".
         * @param startValue defines the start value
         * @param endValue defines the end value
         * @param gradient defines the gradient factor
         * @returns the new matrix
         */
        fun Lerp(startValue: Matrix, endValue: Matrix, gradient: Number): Matrix
        /**
         * Set the given matrix "result" as the interpolated values for "gradient" (float) between the ones of the matrices "startValue" and "endValue".
         * @param startValue defines the start value
         * @param endValue defines the end value
         * @param gradient defines the gradient factor
         * @param result defines the Matrix object where to store data
         */
        fun LerpToRef(startValue: Matrix, endValue: Matrix, gradient: Number, result: Matrix)
        /**
         * Builds a new matrix whose values are computed by:
         * * decomposing the the "startValue" and "endValue" matrices into their respective scale, rotation and translation matrices
         * * interpolating for "gradient" (float) the values between each of these decomposed matrices between the start and the end
         * * recomposing a new matrix from these 3 interpolated scale, rotation and translation matrices
         * @param startValue defines the first matrix
         * @param endValue defines the second matrix
         * @param gradient defines the gradient between the two matrices
         * @returns the new matrix
         */
        fun DecomposeLerp(startValue: Matrix, endValue: Matrix, gradient: Number): Matrix
        /**
         * Update a matrix to values which are computed by:
         * * decomposing the the "startValue" and "endValue" matrices into their respective scale, rotation and translation matrices
         * * interpolating for "gradient" (float) the values between each of these decomposed matrices between the start and the end
         * * recomposing a new matrix from these 3 interpolated scale, rotation and translation matrices
         * @param startValue defines the first matrix
         * @param endValue defines the second matrix
         * @param gradient defines the gradient between the two matrices
         * @param result defines the target matrix
         */
        fun DecomposeLerpToRef(startValue: Matrix, endValue: Matrix, gradient: Number, result: Matrix)
        /**
         * Gets a new rotation matrix used to rotate an entity so as it looks at the target vector3, from the eye vector3 position, the up vector3 being oriented like "up"
         * This function works in left handed mode
         * @param eye defines the final position of the entity
         * @param target defines where the entity should look at
         * @param up defines the up vector for the entity
         * @returns the new matrix
         */
        fun LookAtLH(eye: Vector3, target: Vector3, up: Vector3): Matrix
        /**
         * Sets the given "result" Matrix to a rotation matrix used to rotate an entity so that it looks at the target vector3, from the eye vector3 position, the up vector3 being oriented like "up".
         * This function works in left handed mode
         * @param eye defines the final position of the entity
         * @param target defines where the entity should look at
         * @param up defines the up vector for the entity
         * @param result defines the target matrix
         */
        fun LookAtLHToRef(eye: Vector3, target: Vector3, up: Vector3, result: Matrix)
        /**
         * Gets a new rotation matrix used to rotate an entity so as it looks at the target vector3, from the eye vector3 position, the up vector3 being oriented like "up"
         * This function works in right handed mode
         * @param eye defines the final position of the entity
         * @param target defines where the entity should look at
         * @param up defines the up vector for the entity
         * @returns the new matrix
         */
        fun LookAtRH(eye: Vector3, target: Vector3, up: Vector3): Matrix
        /**
         * Sets the given "result" Matrix to a rotation matrix used to rotate an entity so that it looks at the target vector3, from the eye vector3 position, the up vector3 being oriented like "up".
         * This function works in right handed mode
         * @param eye defines the final position of the entity
         * @param target defines where the entity should look at
         * @param up defines the up vector for the entity
         * @param result defines the target matrix
         */
        fun LookAtRHToRef(eye: Vector3, target: Vector3, up: Vector3, result: Matrix)
        /**
         * Create a left-handed orthographic projection matrix
         * @param width defines the viewport width
         * @param height defines the viewport height
         * @param znear defines the near clip plane
         * @param zfar defines the far clip plane
         * @returns a new matrix as a left-handed orthographic projection matrix
         */
        fun OrthoLH(width: Number, height: Number, znear: Number, zfar: Number): Matrix
        /**
         * Store a left-handed orthographic projection to a given matrix
         * @param width defines the viewport width
         * @param height defines the viewport height
         * @param znear defines the near clip plane
         * @param zfar defines the far clip plane
         * @param result defines the target matrix
         */
        fun OrthoLHToRef(width: Number, height: Number, znear: Number, zfar: Number, result: Matrix)
        /**
         * Create a left-handed orthographic projection matrix
         * @param left defines the viewport left coordinate
         * @param right defines the viewport right coordinate
         * @param bottom defines the viewport bottom coordinate
         * @param top defines the viewport top coordinate
         * @param znear defines the near clip plane
         * @param zfar defines the far clip plane
         * @returns a new matrix as a left-handed orthographic projection matrix
         */
        fun OrthoOffCenterLH(left: Number, right: Number, bottom: Number, top: Number, znear: Number, zfar: Number): Matrix
        /**
         * Stores a left-handed orthographic projection into a given matrix
         * @param left defines the viewport left coordinate
         * @param right defines the viewport right coordinate
         * @param bottom defines the viewport bottom coordinate
         * @param top defines the viewport top coordinate
         * @param znear defines the near clip plane
         * @param zfar defines the far clip plane
         * @param result defines the target matrix
         */
        fun OrthoOffCenterLHToRef(left: Number, right: Number, bottom: Number, top: Number, znear: Number, zfar: Number, result: Matrix)
        /**
         * Creates a right-handed orthographic projection matrix
         * @param left defines the viewport left coordinate
         * @param right defines the viewport right coordinate
         * @param bottom defines the viewport bottom coordinate
         * @param top defines the viewport top coordinate
         * @param znear defines the near clip plane
         * @param zfar defines the far clip plane
         * @returns a new matrix as a right-handed orthographic projection matrix
         */
        fun OrthoOffCenterRH(left: Number, right: Number, bottom: Number, top: Number, znear: Number, zfar: Number): Matrix
        /**
         * Stores a right-handed orthographic projection into a given matrix
         * @param left defines the viewport left coordinate
         * @param right defines the viewport right coordinate
         * @param bottom defines the viewport bottom coordinate
         * @param top defines the viewport top coordinate
         * @param znear defines the near clip plane
         * @param zfar defines the far clip plane
         * @param result defines the target matrix
         */
        fun OrthoOffCenterRHToRef(left: Number, right: Number, bottom: Number, top: Number, znear: Number, zfar: Number, result: Matrix)
        /**
         * Creates a left-handed perspective projection matrix
         * @param width defines the viewport width
         * @param height defines the viewport height
         * @param znear defines the near clip plane
         * @param zfar defines the far clip plane
         * @returns a new matrix as a left-handed perspective projection matrix
         */
        fun PerspectiveLH(width: Number, height: Number, znear: Number, zfar: Number): Matrix
        /**
         * Creates a left-handed perspective projection matrix
         * @param fov defines the horizontal field of view
         * @param aspect defines the aspect ratio
         * @param znear defines the near clip plane
         * @param zfar defines the far clip plane
         * @returns a new matrix as a left-handed perspective projection matrix
         */
        fun PerspectiveFovLH(fov: Number, aspect: Number, znear: Number, zfar: Number): Matrix
        /**
         * Stores a left-handed perspective projection into a given matrix
         * @param fov defines the horizontal field of view
         * @param aspect defines the aspect ratio
         * @param znear defines the near clip plane
         * @param zfar defines the far clip plane
         * @param result defines the target matrix
         * @param isVerticalFovFixed defines it the fov is vertically fixed (default) or horizontally
         */
        fun PerspectiveFovLHToRef(fov: Number, aspect: Number, znear: Number, zfar: Number, result: Matrix, isVerticalFovFixed: Boolean?)
        /**
         * Creates a right-handed perspective projection matrix
         * @param fov defines the horizontal field of view
         * @param aspect defines the aspect ratio
         * @param znear defines the near clip plane
         * @param zfar defines the far clip plane
         * @returns a new matrix as a right-handed perspective projection matrix
         */
        fun PerspectiveFovRH(fov: Number, aspect: Number, znear: Number, zfar: Number): Matrix
        /**
         * Stores a right-handed perspective projection into a given matrix
         * @param fov defines the horizontal field of view
         * @param aspect defines the aspect ratio
         * @param znear defines the near clip plane
         * @param zfar defines the far clip plane
         * @param result defines the target matrix
         * @param isVerticalFovFixed defines it the fov is vertically fixed (default) or horizontally
         */
        fun PerspectiveFovRHToRef(fov: Number, aspect: Number, znear: Number, zfar: Number, result: Matrix, isVerticalFovFixed: Boolean?)
        /**
         * Stores a perspective projection for WebVR info a given matrix
         * @param fov defines the field of view
         * @param znear defines the near clip plane
         * @param zfar defines the far clip plane
         * @param result defines the target matrix
         * @param rightHanded defines if the matrix must be in right-handed mode (false by default)
         */
        fun PerspectiveFovWebVRToRef(
            fov: Any, /* { val upDegrees: Number; val downDegrees: Number; val leftDegrees: Number; val rightDegrees: Number }*/
            znear: Number, zfar: Number, result: Matrix, rightHanded: Boolean?
        )
        /**
         * Computes a complete transformation matrix
         * @param viewport defines the viewport to use
         * @param world defines the world matrix
         * @param view defines the view matrix
         * @param projection defines the projection matrix
         * @param zmin defines the near clip plane
         * @param zmax defines the far clip plane
         * @returns the transformation matrix
         */
        fun GetFinalMatrix(viewport: Viewport, world: Matrix, view: Matrix, projection: Matrix, zmin: Number, zmax: Number): Matrix
        /**
         * Extracts a 2x2 matrix from a given matrix and store the result in a Float32Array
         * @param matrix defines the matrix to use
         * @returns a new Float32Array array with 4 elements : the 2x2 matrix extracted from the given matrix
         */
        fun GetAsMatrix2x2(matrix: Matrix): Float32Array
        /**
         * Extracts a 3x3 matrix from a given matrix and store the result in a Float32Array
         * @param matrix defines the matrix to use
         * @returns a new Float32Array array with 9 elements : the 3x3 matrix extracted from the given matrix
         */
        fun GetAsMatrix3x3(matrix: Matrix): Float32Array
        /**
         * Compute the transpose of a given matrix
         * @param matrix defines the matrix to transpose
         * @returns the new matrix
         */
        fun Transpose(matrix: Matrix): Matrix
        /**
         * Compute the transpose of a matrix and store it in a target matrix
         * @param matrix defines the matrix to transpose
         * @param result defines the target matrix
         */
        fun TransposeToRef(matrix: Matrix, result: Matrix)
        /**
         * Computes a reflection matrix from a plane
         * @param plane defines the reflection plane
         * @returns a new matrix
         */
        fun Reflection(plane: Plane): Matrix
        /**
         * Computes a reflection matrix from a plane
         * @param plane defines the reflection plane
         * @param result defines the target matrix
         */
        fun ReflectionToRef(plane: Plane, result: Matrix)
        /**
         * Sets the given matrix as a rotation matrix composed from the 3 left handed axes
         * @param xaxis defines the value of the 1st axis
         * @param yaxis defines the value of the 2nd axis
         * @param zaxis defines the value of the 3rd axis
         * @param result defines the target matrix
         */
        fun FromXYZAxesToRef(xaxis: Vector3, yaxis: Vector3, zaxis: Vector3, result: Matrix)
        /**
         * Creates a rotation matrix from a quaternion and stores it in a target matrix
         * @param quat defines the quaternion to use
         * @param result defines the target matrix
         */
        fun FromQuaternionToRef(quat: Quaternion, result: Matrix)
    }
}
/**
 * Represents a plane by the equation ax + by + cz + d = 0
 */
external class Plane {
    /**
     * Normal of the plane (a,b,c)
     */
    val normal: Vector3
    /**
     * d component of the plane
     */
    val d: Number
    /**
     * Creates a Plane object according to the given floats a, b, c, d and the plane equation : ax + by + cz + d = 0
     * @param a a component of the plane
     * @param b b component of the plane
     * @param c c component of the plane
     * @param d d component of the plane
     */
    constructor(a: Number, b: Number, c: Number, d: Number)
    /**
     * @returns the plane coordinates as a new array of 4 elements [a, b, c, d].
     */
    fun asArray(): Array<Number>
    /**
     * @returns a new plane copied from the current Plane.
     */
    fun clone(): Plane
    /**
     * @returns the String "Plane".
     */
    fun getClassName(): String
    /**
     * @returns the Plane hash code.
     */
    fun getHashCode(): Number
    /**
     * Normalize the current Plane in place.
     * @returns the updated Plane.
     */
    fun normalize(): Plane
    /**
     * Applies a transformation the plane and returns the result
     * @param transformation the transformation matrix to be applied to the plane
     * @returns a new Plane as the result of the transformation of the current Plane by the given matrix.
     */
    fun transform(transformation: Matrix): Plane
    /**
     * Calcualtte the dot product between the point and the plane normal
     * @param point point to calculate the dot product with
     * @returns the dot product (float) of the point coordinates and the plane normal.
     */
    fun dotCoordinate(point: Vector3): Number
    /**
     * Updates the current Plane from the plane defined by the three given points.
     * @param point1 one of the points used to contruct the plane
     * @param point2 one of the points used to contruct the plane
     * @param point3 one of the points used to contruct the plane
     * @returns the updated Plane.
     */
    fun copyFromPoints(point1: Vector3, point2: Vector3, point3: Vector3): Plane
    /**
     * Checks if the plane is facing a given direction
     * @param direction the direction to check if the plane is facing
     * @param epsilon value the dot product is compared against (returns true if dot <= epsilon)
     * @returns True is the vector "direction"  is the same side than the plane normal.
     */
    fun isFrontFacingTo(direction: Vector3, epsilon: Number): Boolean
    /**
     * Calculates the distance to a point
     * @param point point to calculate distance to
     * @returns the signed distance (float) from the given point to the Plane.
     */
    fun signedDistanceTo(point: Vector3): Number

    companion object {
        /**
         * Creates a plane from an  array
         * @param array the array to create a plane from
         * @returns a new Plane from the given array.
         */
        fun FromArray(array: Collection<Number>): Plane
        /**
         * Creates a plane from three points
         * @param point1 point used to create the plane
         * @param point2 point used to create the plane
         * @param point3 point used to create the plane
         * @returns a new Plane defined by the three given points.
         */
        fun FromPoints(point1: Vector3, point2: Vector3, point3: Vector3): Plane
        /**
         * Creates a plane from an origin point and a normal
         * @param origin origin of the plane to be constructed
         * @param normal normal of the plane to be constructed
         * @returns a new Plane the normal vector to this plane at the given origin point.
         * Note : the vector "normal" is updated because normalized.
         */
        fun FromPositionAndNormal(origin: Vector3, normal: Vector3): Plane
        /**
         * Calculates the distance from a plane and a point
         * @param origin origin of the plane to be constructed
         * @param normal normal of the plane to be constructed
         * @param point point to calculate distance to
         * @returns the signed distance between the plane defined by the normal vector at the "origin"" point and the given other point.
         */
        fun SignedDistanceToPlaneFromPositionAndNormal(origin: Vector3, normal: Vector3, point: Vector3): Number
    }
}
/**
 * Class used to represent a viewport on screen
 */
external class Viewport {
    /** viewport left coordinate */
    val x: Number
    /** viewport top coordinate */
    val y: Number
    /**viewport width */
    val width: Number
    /** viewport height */
    val height: Number
    /**
     * Creates a Viewport object located at (x, y) and sized (width, height)
     * @param x defines viewport left coordinate
     * @param y defines viewport top coordinate
     * @param width defines the viewport width
     * @param height defines the viewport height
     */
    constructor(
        /** viewport left coordinate */
        x: Number,
        /** viewport top coordinate */
        y: Number,
        /**viewport width */
        width: Number,
        /** viewport height */
        height: Number)
    /**
     * Creates a new viewport using absolute sizing (from 0-> width, 0-> height instead of 0->1)
     * @param renderWidth defines the rendering width
     * @param renderHeight defines the rendering height
     * @returns a new Viewport
     */
    fun toGlobal(renderWidth: Number, renderHeight: Number): Viewport
    /**
     * Stores absolute viewport value into a target viewport (from 0-> width, 0-> height instead of 0->1)
     * @param renderWidth defines the rendering width
     * @param renderHeight defines the rendering height
     * @param ref defines the target viewport
     * @returns the current viewport
     */
    fun toGlobalToRef(renderWidth: Number, renderHeight: Number, ref: Viewport): Viewport
    /**
     * Returns a new Viewport copied from the current one
     * @returns a new Viewport
     */
    fun clone(): Viewport
}
/**
 * Reprasents a camera frustum
 */
external class Frustum {
    companion object {
        /**
         * Gets the planes representing the frustum
         * @param transform matrix to be applied to the returned planes
         * @returns a new array of 6 Frustum planes computed by the given transformation matrix.
         */
        fun GetPlanes(transform: Matrix): Array<Plane>
        /**
         * Gets the near frustum plane transformed by the transform matrix
         * @param transform transformation matrix to be applied to the resulting frustum plane
         * @param frustumPlane the resuling frustum plane
         */
        fun GetNearPlaneToRef(transform: Matrix, frustumPlane: Plane)
        /**
         * Gets the far frustum plane transformed by the transform matrix
         * @param transform transformation matrix to be applied to the resulting frustum plane
         * @param frustumPlane the resuling frustum plane
         */
        fun GetFarPlaneToRef(transform: Matrix, frustumPlane: Plane)
        /**
         * Gets the left frustum plane transformed by the transform matrix
         * @param transform transformation matrix to be applied to the resulting frustum plane
         * @param frustumPlane the resuling frustum plane
         */
        fun GetLeftPlaneToRef(transform: Matrix, frustumPlane: Plane)
        /**
         * Gets the right frustum plane transformed by the transform matrix
         * @param transform transformation matrix to be applied to the resulting frustum plane
         * @param frustumPlane the resuling frustum plane
         */
        fun GetRightPlaneToRef(transform: Matrix, frustumPlane: Plane)
        /**
         * Gets the top frustum plane transformed by the transform matrix
         * @param transform transformation matrix to be applied to the resulting frustum plane
         * @param frustumPlane the resuling frustum plane
         */
        fun GetTopPlaneToRef(transform: Matrix, frustumPlane: Plane)
        /**
         * Gets the bottom frustum plane transformed by the transform matrix
         * @param transform transformation matrix to be applied to the resulting frustum plane
         * @param frustumPlane the resuling frustum plane
         */
        fun GetBottomPlaneToRef(transform: Matrix, frustumPlane: Plane)
        /**
         * Sets the given array "frustumPlanes" with the 6 Frustum planes computed by the given transformation matrix.
         * @param transform transformation matrix to be applied to the resulting frustum planes
         * @param frustumPlanes the resuling frustum planes
         */
        fun GetPlanesToRef(transform: Matrix, frustumPlanes: Array<Plane>)
    }
}
/** Defines supported spaces */
external enum class Space {
    /** Local (object) space */
    LOCAL /* = 0*/,
    /** World space */
    WORLD /* = 1*/,
    /** Bone space */
    BONE /* = 2 */
}
/** Defines the 3 main axes */
external class Axis {
    companion object {
        /** X axis */
        val X: Vector3 = definedExternally
        /** Y axis */
        val Y: Vector3 = definedExternally
        /** Z axis */
        val Z: Vector3 = definedExternally
    }
}
/** Class used to represent a Bezier curve */
external class BezierCurve {
    companion object {
        /**
         * Returns the cubic Bezier interpolated value (float) at "t" (float) from the given x1, y1, x2, y2 floats
         * @param t defines the time
         * @param x1 defines the left coordinate on X axis
         * @param y1 defines the left coordinate on Y axis
         * @param x2 defines the right coordinate on X axis
         * @param y2 defines the right coordinate on Y axis
         * @returns the interpolated value
         */
        fun Interpolate(t: Number, x1: Number, y1: Number, x2: Number, y2: Number): Number
    }
}
/**
 * Defines potential orientation for back face culling
 */
external enum class Orientation {
    /**
     * Clockwise
     */
    CW /*= 0*/,
    /** Counter clockwise */
    CCW /*= 1*/
}
/**
 * Defines angle representation
 */
external class Angle {
    /**
     * Creates an Angle object of "radians" radians (float).
     * @param radians the angle in radians
     */
    constructor(radians: Number)
    /**
     * Get value in degrees
     * @returns the Angle value in degrees (float)
     */
    fun degrees(): Number
    /**
     * Get value in radians
     * @returns the Angle value in radians (float)
     */
    fun radians(): Number

    companion object {
        /**
         * Gets a new Angle object valued with the angle value in radians between the two given vectors
         * @param a defines first vector
         * @param b defines second vector
         * @returns a new Angle
         */
        fun BetweenTwoPoints(a: Vector2, b: Vector2): Angle
        /**
         * Gets a new Angle object from the given float in radians
         * @param radians defines the angle value in radians
         * @returns a new Angle
         */
        fun FromRadians(radians: Number): Angle
        /**
         * Gets a new Angle object from the given float in degrees
         * @param degrees defines the angle value in degrees
         * @returns a new Angle
         */
        fun FromDegrees(degrees: Number): Angle
    }
}
/**
 * This represents an arc in a 2d space.
 */
external class Arc2 {
    /** Defines the start point of the arc */
    val startPoint: Vector2
    /** Defines the mid point of the arc */
    val midPoint: Vector2
    /** Defines the end point of the arc */
    val endPoint: Vector2
    /**
     * Defines the center point of the arc.
     */
    val centerPoint: Vector2
    /**
     * Defines the radius of the arc.
     */
    val radius: Number
    /**
     * Defines the angle of the arc (from mid point to end point).
     */
    val angle: Angle
    /**
     * Defines the start angle of the arc (from start point to middle point).
     */
    val startAngle: Angle
    /**
     * Defines the orientation of the arc (clock wise/counter clock wise).
     */
    val orientation: Orientation
    /**
     * Creates an Arc object from the three given points : start, middle and end.
     * @param startPoint Defines the start point of the arc
     * @param midPoint Defines the midlle point of the arc
     * @param endPoint Defines the end point of the arc
     */
    constructor(
        /** Defines the start point of the arc */
        startPoint: Vector2,
        /** Defines the mid point of the arc */
        midPoint: Vector2,
        /** Defines the end point of the arc */
        endPoint: Vector2)
}
/**
 * Represents a 2D path made up of multiple 2D points
 */
external class Path2 {
    /**
     * If the path start and end point are the same
     */
    val closed: Boolean
    /**
     * Creates a Path2 object from the starting 2D coordinates x and y.
     * @param x the starting points x value
     * @param y the starting points y value
     */
    constructor(x: Number, y: Number)
    /**
     * Adds a new segment until the given coordinates (x, y) to the current Path2.
     * @param x the added points x value
     * @param y the added points y value
     * @returns the updated Path2.
     */
    fun addLineTo(x: Number, y: Number): Path2
    /**
     * Adds _numberOfSegments_ segments according to the arc definition (middle point coordinates, end point coordinates, the arc start point being the current Path2 last point) to the current Path2.
     * @param midX middle point x value
     * @param midY middle point y value
     * @param endX end point x value
     * @param endY end point y value
     * @param numberOfSegments (default: 36)
     * @returns the updated Path2.
     */
    fun addArcTo(midX: Number, midY: Number, endX: Number, endY: Number, numberOfSegments: Number?): Path2
    /**
     * Closes the Path2.
     * @returns the Path2.
     */
    fun close(): Path2
    /**
     * Gets the sum of the distance between each sequential point in the path
     * @returns the Path2 total length (float).
     */
    fun length(): Number
    /**
     * Gets the points which construct the path
     * @returns the Path2 internal array of points.
     */
    fun getPoints(): Array<Vector2>
    /**
     * Retreives the point at the distance aways from the starting point
     * @param normalizedLengthPosition the length along the path to retreive the point from
     * @returns a new Vector2 located at a percentage of the Path2 total length on this path.
     */
    fun getPointAtLengthPosition(normalizedLengthPosition: Number): Vector2
    /**
     * Creates a new path starting from an x and y position
     * @param x starting x value
     * @param y starting y value
     * @returns a new Path2 starting at the coordinates (x, y).
     */
    fun StartingAt(x: Number, y: Number): Path2
}
/**
 * Represents a 3D path made up of multiple 3D points
 */
external class Path3D {
    /**
     * an array of Vector3, the curve axis of the Path3D
     */
    var path: Array<Vector3>
    /**
     * new Path3D(path, normal, raw)
     * Creates a Path3D. A Path3D is a logical math object, so not a mesh.
     * please read the description in the tutorial : https://doc.babylonjs.com/how_to/how_to_use_path3d
     * @param path an array of Vector3, the curve axis of the Path3D
     * @param firstNormal (options) Vector3, the first wanted normal to the curve. Ex (0, 1, 0) for a vertical normal.
     * @param raw (optional, default false) : Boolean, if true the returned Path3D isn't normalized. Useful to depict path acceleration or speed.
     */
    constructor(
        /**
         * an array of Vector3, the curve axis of the Path3D
         */
        path: Array<Vector3>, firstNormal: Vector3?, raw: Boolean?)
    /**
     * Returns the Path3D array of successive Vector3 designing its curve.
     * @returns the Path3D array of successive Vector3 designing its curve.
     */
    fun getCurve(): Array<Vector3>
    /**
     * Returns an array populated with tangent vectors on each Path3D curve point.
     * @returns an array populated with tangent vectors on each Path3D curve point.
     */
    fun getTangents(): Array<Vector3>
    /**
     * Returns an array populated with normal vectors on each Path3D curve point.
     * @returns an array populated with normal vectors on each Path3D curve point.
     */
    fun getNormals(): Array<Vector3>
    /**
     * Returns an array populated with binormal vectors on each Path3D curve point.
     * @returns an array populated with binormal vectors on each Path3D curve point.
     */
    fun getBinormals(): Array<Vector3>
    /**
     * Returns an array populated with distances (float) of the i-th point from the first curve point.
     * @returns an array populated with distances (float) of the i-th point from the first curve point.
     */
    fun getDistances(): Array<Number>
    /**
     * Forces the Path3D tangent, normal, binormal and distance recomputation.
     * @param path path which all values are copied into the curves points
     * @param firstNormal which should be projected onto the curve
     * @returns the same object updated.
     */
    fun update(path: Array<Vector3>, firstNormal: Vector3?): Path3D
}
/**
 * A Curve3 object is a logical object, so not a mesh, to handle curves in the 3D geometric space.
 * A Curve3 is designed from a series of successive Vector3.
 * @see https://doc.babylonjs.com/how_to/how_to_use_curve3
 */
external class Curve3 {
    companion object {
        /**
         * Returns a Curve3 object along a Quadratic Bezier curve : https://doc.babylonjs.com/how_to/how_to_use_curve3#quadratic-bezier-curve
         * @param v0 (Vector3) the origin point of the Quadratic Bezier
         * @param v1 (Vector3) the control point
         * @param v2 (Vector3) the end point of the Quadratic Bezier
         * @param nbPoints (integer) the wanted Number of points in the curve
         * @returns the created Curve3
         */
        fun CreateQuadraticBezier(v0: Vector3, v1: Vector3, v2: Vector3, nbPoints: Number): Curve3
        /**
         * Returns a Curve3 object along a Cubic Bezier curve : https://doc.babylonjs.com/how_to/how_to_use_curve3#cubic-bezier-curve
         * @param v0 (Vector3) the origin point of the Cubic Bezier
         * @param v1 (Vector3) the first control point
         * @param v2 (Vector3) the second control point
         * @param v3 (Vector3) the end point of the Cubic Bezier
         * @param nbPoints (integer) the wanted Number of points in the curve
         * @returns the created Curve3
         */
        fun CreateCubicBezier(v0: Vector3, v1: Vector3, v2: Vector3, v3: Vector3, nbPoints: Number): Curve3
        /**
         * Returns a Curve3 object along a Hermite Spline curve : https://doc.babylonjs.com/how_to/how_to_use_curve3#hermite-spline
         * @param p1 (Vector3) the origin point of the Hermite Spline
         * @param t1 (Vector3) the tangent vector at the origin point
         * @param p2 (Vector3) the end point of the Hermite Spline
         * @param t2 (Vector3) the tangent vector at the end point
         * @param nbPoints (integer) the wanted Number of points in the curve
         * @returns the created Curve3
         */
        fun CreateHermiteSpline(p1: Vector3, t1: Vector3, p2: Vector3, t2: Vector3, nbPoints: Number): Curve3
        /**
         * Returns a Curve3 object along a CatmullRom Spline curve :
         * @param points (array of Vector3) the points the spline must pass through. At least, four points required
         * @param nbPoints (integer) the wanted Number of points between each curve control points
         * @param closed (Boolean) optional with default false, when true forms a closed loop from the points
         * @returns the created Curve3
         */
        fun CreateCatmullRomSpline(points: Array<Vector3>, nbPoints: Number, closed: Boolean?): Curve3
    }
    /**
     * A Curve3 object is a logical object, so not a mesh, to handle curves in the 3D geometric space.
     * A Curve3 is designed from a series of successive Vector3.
     * Tuto : https://doc.babylonjs.com/how_to/how_to_use_curve3#curve3-object
     * @param points points which make up the curve
     */
    constructor(points: Array<Vector3>)
    /**
     * @returns the Curve3 stored array of successive Vector3
     */
    fun getPoints(): Array<Vector3>
    /**
     * @returns the computed length (float) of the curve.
     */
    fun length(): Number
    /**
     * Returns a new instance of Curve3 object : var curve = curveA.continue(curveB)
     * This new Curve3 is built by translating and sticking the curveB at the end of the curveA.
     * curveA and curveB keep unchanged.
     * @param curve the curve to continue from this curve
     * @returns the newly constructed curve
     */
    fun `continue`(curve: Curve3): Curve3
}
/**
 * Contains position and normal vectors for a vertex
 */
external class PositionNormalVertex {
    /** the position of the vertex (defaut: 0,0,0) */
    val position: Vector3
    /** the normal of the vertex (defaut: 0,1,0) */
    val normal: Vector3
    /**
     * Creates a PositionNormalVertex
     * @param position the position of the vertex (defaut: 0,0,0)
     * @param normal the normal of the vertex (defaut: 0,1,0)
     */
    constructor(
        /** the position of the vertex (defaut: 0,0,0) */
        position: Vector3?,
        /** the normal of the vertex (defaut: 0,1,0) */
        normal: Vector3?)
    /**
     * Clones the PositionNormalVertex
     * @returns the cloned PositionNormalVertex
     */
    fun clone(): PositionNormalVertex
}
/**
 * Contains position, normal and uv vectors for a vertex
 */
external class PositionNormalTextureVertex {
    /** the position of the vertex (defaut: 0,0,0) */
    val position: Vector3
    /** the normal of the vertex (defaut: 0,1,0) */
    val normal: Vector3
    /** the uv of the vertex (default: 0,0) */
    val uv: Vector2
    /**
     * Creates a PositionNormalTextureVertex
     * @param position the position of the vertex (defaut: 0,0,0)
     * @param normal the normal of the vertex (defaut: 0,1,0)
     * @param uv the uv of the vertex (default: 0,0)
     */
    constructor(
        /** the position of the vertex (defaut: 0,0,0) */
        position: Vector3?,
        /** the normal of the vertex (defaut: 0,1,0) */
        normal: Vector3?,
        /** the uv of the vertex (default: 0,0) */
        uv: Vector2?)
    /**
     * Clones the PositionNormalTextureVertex
     * @returns the cloned PositionNormalTextureVertex
     */
    fun clone(): PositionNormalTextureVertex
}

