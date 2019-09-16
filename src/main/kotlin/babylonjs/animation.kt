@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary")
package babylonjs

import kotlin.js.Promise

/**
 * Interface containing an array of animations
 */
external interface IAnimatable {
    /**
     * Array of animations
     */
    var animations: Array<Animation>?
}

/**
 * Class used to store an actual running animation
 */
external class Animatable {
    /** defines the target object */
    var target: Any
    /** defines the starting frame Number (default is 0) */
    var fromFrame: Number
    /** defines the ending frame Number (default is 100) */
    var toFrame: Number
    /** defines if the animation must loop (default is false)  */
    var loopAnimation: Boolean
    /** defines a callback to call when animation ends if it is not looping */
    var onAnimationEnd: (() -> Unit)?
    /** defines a callback to call when animation loops */
    var onAnimationLoop: (() -> Unit)?

    /**
     * Gets or sets a boolean indicating if the animatable must be disposed and removed at the end of the animation.
     * This will only apply for non looping animation (default is true)
     */
    var disposeOnEnd: Boolean
    /**
     * Gets a boolean indicating if the animation has started
     */
    var animationStarted: Boolean
    /**
     * Observer raised when the animation ends
     */
    // TODO Observable
    //var onAnimationEndObservable: Observable<Animatable>
    /**
     * Observer raised when the animation loops
     */
    // TODO Observalbe
    // var onAnimationLoopObservable: Observable<Animatable>
    /**
     * Gets the root Animatable used to synchronize and normalize animations
     */
    val syncRoot: Animatable?
    /**
     * Gets the current frame of the first RuntimeAnimation
     * Used to synchronize Animatables
     */
    val masterFrame: Number
    /**
     * Gets or sets the animatable weight (-1.0 by default meaning not weighted)
     */
    var weight: Number
    /**
     * Gets or sets the speed ratio to apply to the animatable (1.0 by default)
     */
    var speedRatio: Number
    /**
     * Creates a new Animatable
     * @param scene defines the hosting scene
     * @param target defines the target object
     * @param fromFrame defines the starting frame number (default is 0)
     * @param toFrame defines the ending frame number (default is 100)
     * @param loopAnimation defines if the animation must loop (default is false)
     * @param speedRatio defines the factor to apply to animation speed (default is 1)
     * @param onAnimationEnd defines a callback to call when animation ends if it is not looping
     * @param animations defines a group of animation to add to the new Animatable
     * @param onAnimationLoop defines a callback to call when animation loops
     */
    constructor(scene: Scene,
                /** defines the target object */
                target: Any,
                /** defines the starting frame number (default is 0) */
                fromFrame: Number?,
                /** defines the ending frame number (default is 100) */
                toFrame: Number?,
                /** defines if the animation must loop (default is false)  */
                loopAnimation: Boolean?, speedRatio: Number?,
                /** defines a callback to call when animation ends if it is not looping */
                onAnimationEnd: (() -> Unit)?, animations: Array<Animation>?,
                /** defines a callback to call when animation loops */
                onAnimationLoop: (() -> Unit)?)

    /**
     * Synchronize and normalize current Animatable with a source Animatable
     * This is useful when using animation weights and when animations are not of the same length
     * @param root defines the root Animatable to synchronize with
     * @returns the current Animatable
     */
    fun syncWith(root: Animatable): Animatable
    /**
     * Gets the list of runtime animations
     * @returns an array of RuntimeAnimation
     */
    fun getAnimations(): Array<RuntimeAnimation>
    /**
     * Adds more animations to the current animatable
     * @param target defines the target of the animations
     * @param animations defines the new animations to add
     */
    fun appendAnimations(target: Any, animations: Array<Animation>)
    /**
     * Gets the source animation for a specific property
     * @param property defines the propertyu to look for
     * @returns null or the source animation for the given property
     */
    fun getAnimationByTargetProperty(property: String): Animation?
    /**
     * Gets the runtime animation for a specific property
     * @param property defines the propertyu to look for
     * @returns null or the runtime animation for the given property
     */
    fun getRuntimeAnimationByTargetProperty(property: String): RuntimeAnimation?
    /**
     * Resets the animatable to its original state
     */
    fun reset()
    /**
     * Allows the animatable to blend with current running animations
     * @see http://doc.babylonjs.com/babylon101/animations#animation-blending
     * @param blendingSpeed defines the blending speed to use
     */
    fun enableBlending(blendingSpeed: Number)
    /**
     * Disable animation blending
     * @see http://doc.babylonjs.com/babylon101/animations#animation-blending
     */
    fun disableBlending()
    /**
     * Jump directly to a given frame
     * @param frame defines the frame to jump to
     */
    fun goToFrame(frame: Number)
    /**
     * Pause the animation
     */
    fun pause()
    /**
     * Restart the animation
     */
    fun restart()
    /**
     * Stop and delete the current animation
     * @param animationName defines a string used to only stop some of the runtime animations instead of all
     * @param targetMask - a function that determines if the animation should be stopped based on its target (all animations will be stopped if both this and animationName are empty)
     */
    fun stop(animationName: String?, targetMask: ((target: Any) -> Boolean)?)
    /**
     * Wait asynchronously for the animation to end
     * @returns a promise which will be fullfilled when the animation ends
     */
    fun waitAsync(): Promise<Animatable>
}

external class AnimationEvent {
    /** The frame for which the event is triggered **/
    var frame: Number
    /** The event to perform when triggered **/
    var action: (currentFrame: Number) -> Unit
    /** Specifies if the event should be triggered only once**/
    var onlyOnce: Boolean?
    /**
     * Specifies if the animation event is done
     */
    var isDone: Boolean
    /**
     * Initializes the animation event
     * @param frame The frame for which the event is triggered
     * @param action The event to perform when triggered
     * @param onlyOnce Specifies if the event should be triggered only once
     */
    constructor(
        /** The frame for which the event is triggered **/
        frame: Number,
        /** The event to perform when triggered **/
        action: (currentFrame: Number) -> Unit,
        /** Specifies if the event should be triggered only once**/
        onlyOnce: Boolean?
    )
}

external class AnimationRange {
    /**The name of the animation range**/
    var name: String
    /**The starting frame of the animation */
    var from: Number
    /**The ending frame of the animation*/
    var to: Number
    /**
     * Initializes the range of an animation
     * @param name The name of the animation range
     * @param from The starting frame of the animation
     * @param to The ending frame of the animation
     */
    constructor(
        /**The name of the animation range**/
        name: String,
        /**The starting frame of the animation */
        from: Number,
        /**The ending frame of the animation*/
        to: Number
    )
    /**
     * Makes a copy of the animation range
     * @returns A copy of the animation range
     */
    fun clone(): AnimationRange
}

/**
 * This represents the main contract an easing function should follow.
 * Easing functions are used throughout the animation system.
 * @see http://doc.babylonjs.com/babylon101/animations#easing-functions
 */
external interface IEasingFunction {
    /**
     * Given an input gradient between 0 and 1, this returns the corrseponding value
     * of the easing function.
     * The link below provides some of the most common examples of easing functions.
     * @see https://easings.net/
     * @param gradient Defines the value between 0 and 1 we want the easing value for
     * @returns the corresponding value on the curve defined by the easing function
     */
    fun ease(gradient: Number): Number
}

// TODO add all easing implementation

/**
 * Defines an interface which represents an animation key frame
 */
external interface IAnimationKey {
    /**
     * Frame of the key frame
     */
    var frame: Number
    /**
     * Value at the specifies key frame
     */
    var value: Any
    /**
     * The input tangent for the cubic hermite spline
     */
    var inTangent: Any?
    /**
     * The output tangent for the cubic hermite spline
     */
    var outTangent: Any?
    /**
     * The animation interpolation type
     */
    var interpolation: AnimationKeyInterpolation?
}

/**
 * Enum for the animation key frame interpolation type
 */
external enum class AnimationKeyInterpolation {
    /**
     * Do not interpolate between keys and use the start key value only. Tangents are ignored
     */
    // TODO is the value correct ?
    STEP /*= 1*/
}

/**
 * Base class used for every default easing function.
 * @see http://doc.babylonjs.com/babylon101/animations#easing-functions
 */
external class EasingFunction: IEasingFunction {
    /**
     * Sets the easing mode of the current function.
     * @param easingMode Defines the willing mode (EASINGMODE_EASEIN, EASINGMODE_EASEOUT or EASINGMODE_EASEINOUT)
     */
    fun setEasingMode(easingMode: Number)
    /**
     * Gets the current easing mode.
     * @returns the easing mode
     */
    fun getEasingMode(): Number
    /**
     * @hidden
     */
    fun easeInCore(gradient: Number): Number
    /**
     * Given an input gradient between 0 and 1, this returns the corrseponding value
     * of the easing function.
     * @param gradient Defines the value between 0 and 1 we want the easing value for
     * @returns the corresponding value on the curve defined by the easing function
     */
    override fun ease(gradient: Number): Number

    companion object {
        /**
         * Interpolation follows the mathematical formula associated with the easing function.
         */
        val EASINGMODE_EASEIN: Number
        /**
         * Interpolation follows 100% interpolation minus the output of the formula associated with the easing function.
         */
        val EASINGMODE_EASEOUT: Number
        /**
         * Interpolation uses EaseIn for the first half of the animation and EaseOut for the second half.
         */
        val EASINGMODE_EASEINOUT: Number
    }
}

/**
 * @hidden
 */
external class _IAnimationState {
    var key: Number
    var repeatCount: Number
    var workValue: Any?
    var loopMode: Number?
    var offsetValue: Any?
    var highLimitValue: Any?
}

/**
 * Class used to store Any kind of animation
 */
external class Animation {
    /**Name of the animation */
    var name: String
    /**Property to animate */
    var targetProperty: String
    /**The frames per second of the animation */
    var framePerSecond: Number
    /**The data type of the animation */
    var dataType: Number
    /**The loop mode of the animation */
    var loopMode: Number?
    /**Specifies if blending should be enabled */
    val enableBlending: Boolean?
    /**
     * Stores an array of target property paths
     */
    var targetPropertyPath: Array<String>
    /**
     * Stores the blending speed of the animation
     */
    var blendingSpeed: Number
    /**
     * Return the array of runtime animations currently using this animation
     */
    val runtimeAnimations: Array<RuntimeAnimation>
    /**
     * Specifies if Any of the runtime animations are currently running
     */
    val hasRunningRuntimeAnimations: Boolean
    /**
     * Initializes the animation
     * @param name Name of the animation
     * @param targetProperty Property to animate
     * @param framePerSecond The frames per second of the animation
     * @param dataType The data type of the animation
     * @param loopMode The loop mode of the animation
     * @param enableBlending Specifies if blending should be enabled
     */
    constructor(
        /**Name of the animation */
        name: String,
        /**Property to animate */
        targetProperty: String,
        /**The frames per second of the animation */
        framePerSecond: Number,
        /**The data type of the animation */
        dataType: Number,
        /**The loop mode of the animation */
        loopMode: Number? = definedExternally,
        /**Specifies if blending should be enabled */
        enableBlending: Boolean? = definedExternally
    )
    /**
     * Converts the animation to a String
     * @param fullDetails support for multiple levels of logging within scene loading
     * @returns String form of the animation
     */
   fun toString(fullDetails: Boolean?): String
    /**
     * Add an event to this animation
     * @param event Event to add
     */
   fun addEvent(event: AnimationEvent)
    /**
     * Remove all events found at the given frame
     * @param frame The frame to remove events from
     */
   fun removeEvents(frame: Number)
    /**
     * Retrieves all the events from the animation
     * @returns Events from the animation
     */
   fun getEvents(): Array<AnimationEvent>
    /**
     * Creates an animation range
     * @param name Name of the animation range
     * @param from Starting frame of the animation range
     * @param to Ending frame of the animation
     */
   fun createRange(name: String, from: Number, to: Number)
    /**
     * Deletes an animation range by name
     * @param name Name of the animation range to delete
     * @param deleteFrames Specifies if the key frames for the range should also be deleted (true) or not (false)
     */
   fun deleteRange(name: String, deleteFrames: Boolean?)
    /**
     * Gets the animation range by name, or null if not defined
     * @param name Name of the animation range
     * @returns Nullable animation range
     */
   fun getRange(name: String): AnimationRange?
    /**
     * Gets the key frames from the animation
     * @returns The key frames of the animation
     */
   fun getKeys(): Array<IAnimationKey>
    /**
     * Gets the highest frame rate of the animation
     * @returns Highest frame rate of the animation
     */
   fun getHighestFrame(): Number
    /**
     * Gets the easing function of the animation
     * @returns Easing function of the animation
     */
   fun getEasingFunction(): IEasingFunction
    /**
     * Sets the easing function of the animation
     * @param easingFunction A custom mathematical formula for animation
     */
   fun setEasingFunction(easingFunction: EasingFunction)
    /**
     * Interpolates a scalar linearly
     * @param startValue Start value of the animation curve
     * @param endValue End value of the animation curve
     * @param gradient Scalar amount to interpolate
     * @returns Interpolated scalar value
     */
   fun floatInterpolateFunction(startValue: Number, endValue: Number, gradient: Number): Number
    /**
     * Interpolates a scalar cubically
     * @param startValue Start value of the animation curve
     * @param outTangent End tangent of the animation
     * @param endValue End value of the animation curve
     * @param inTangent Start tangent of the animation curve
     * @param gradient Scalar amount to interpolate
     * @returns Interpolated scalar value
     */
   fun floatInterpolateFunctionWithTangents(startValue: Number, outTangent: Number, endValue: Number, inTangent: Number, gradient: Number): Number
    /**
     * Interpolates a quaternion using a spherical linear interpolation
     * @param startValue Start value of the animation curve
     * @param endValue End value of the animation curve
     * @param gradient Scalar amount to interpolate
     * @returns Interpolated quaternion value
     */
   fun quaternionInterpolateFunction(startValue: Quaternion, endValue: Quaternion, gradient: Number): Quaternion
    /**
     * Interpolates a quaternion cubically
     * @param startValue Start value of the animation curve
     * @param outTangent End tangent of the animation curve
     * @param endValue End value of the animation curve
     * @param inTangent Start tangent of the animation curve
     * @param gradient Scalar amount to interpolate
     * @returns Interpolated quaternion value
     */
   fun quaternionInterpolateFunctionWithTangents(startValue: Quaternion, outTangent: Quaternion, endValue: Quaternion, inTangent: Quaternion, gradient: Number): Quaternion
    /**
     * Interpolates a Vector3 linearl
     * @param startValue Start value of the animation curve
     * @param endValue End value of the animation curve
     * @param gradient Scalar amount to interpolate
     * @returns Interpolated scalar value
     */
   fun vector3InterpolateFunction(startValue: Vector3, endValue: Vector3, gradient: Number): Vector3
    /**
     * Interpolates a Vector3 cubically
     * @param startValue Start value of the animation curve
     * @param outTangent End tangent of the animation
     * @param endValue End value of the animation curve
     * @param inTangent Start tangent of the animation curve
     * @param gradient Scalar amount to interpolate
     * @returns InterpolatedVector3 value
     */
   fun vector3InterpolateFunctionWithTangents(startValue: Vector3, outTangent: Vector3, endValue: Vector3, inTangent: Vector3, gradient: Number): Vector3
    /**
     * Interpolates a Vector2 linearly
     * @param startValue Start value of the animation curve
     * @param endValue End value of the animation curve
     * @param gradient Scalar amount to interpolate
     * @returns Interpolated Vector2 value
     */
   fun vector2InterpolateFunction(startValue: Vector2, endValue: Vector2, gradient: Number): Vector2
    /**
     * Interpolates a Vector2 cubically
     * @param startValue Start value of the animation curve
     * @param outTangent End tangent of the animation
     * @param endValue End value of the animation curve
     * @param inTangent Start tangent of the animation curve
     * @param gradient Scalar amount to interpolate
     * @returns Interpolated Vector2 value
     */
   fun vector2InterpolateFunctionWithTangents(startValue: Vector2, outTangent: Vector2, endValue: Vector2, inTangent: Vector2, gradient: Number): Vector2
    /**
     * Interpolates a size linearly
     * @param startValue Start value of the animation curve
     * @param endValue End value of the animation curve
     * @param gradient Scalar amount to interpolate
     * @returns Interpolated Size value
     */
   fun sizeInterpolateFunction(startValue: Size, endValue: Size, gradient: Number): Size
    /**
     * Interpolates a Color3 linearly
     * @param startValue Start value of the animation curve
     * @param endValue End value of the animation curve
     * @param gradient Scalar amount to interpolate
     * @returns Interpolated Color3 value
     */
   fun color3InterpolateFunction(startValue: Color3, endValue: Color3, gradient: Number): Color3
    /**
     * @hidden Internal use only
     */
   fun _getKeyValue(value: Any): Any
    /**
     * @hidden Internal use only
     */
   fun _interpolate(currentFrame: Number, state: _IAnimationState): Any
    /**
     * Defines the function to use to interpolate matrices
     * @param startValue defines the start matrix
     * @param endValue defines the end matrix
     * @param gradient defines the gradient between both matrices
     * @param result defines an optional target matrix where to store the interpolation
     * @returns the interpolated matrix
     */
   fun matrixInterpolateFunction(startValue: Matrix, endValue: Matrix, gradient: Number, result: Matrix?): Matrix
    /**
     * Makes a copy of the animation
     * @returns Cloned animation
     */
   fun clone(): Animation
    /**
     * Sets the key frames of the animation
     * @param values The animation key frames to set
     */
   fun setKeys(values: Array<IAnimationKey>)
    /**
     * Serializes the animation to an object
     * @returns Serialized object
     */
   fun serialize(): Any

    companion object {
        /**
         * Use matrix interpolation instead of using direct key value when animating matrices
         */
        var AllowMatricesInterpolation: Boolean
        /**
         * When matrix interpolation is enabled, this Boolean forces the system to use Matrix.DecomposeLerp instead of Matrix.Lerp. Interpolation is more precise but slower
         */
        var AllowMatrixDecomposeForInterpolation: Boolean
        /**
         * @hidden Internal use
         */
        fun _PrepareAnimation(name: String, targetProperty: String, framePerSecond: Number, totalFrame: Number, from: Any, to: Any, loopMode: Number?, easingFunction: EasingFunction?): Animation?
        /**
         * Sets up an animation
         * @param property The property to animate
         * @param animationType The animation type to apply
         * @param framePerSecond The frames per second of the animation
         * @param easingFunction The easing function used in the animation
         * @returns The created animation
         */
        fun CreateAnimation(property: String, animationType: Number, framePerSecond: Number, easingFunction: EasingFunction): Animation
        /**
         * Create and start an animation on a node
         * @param name defines the name of the global animation that will be run on all nodes
         * @param node defines the root node where the animation will take place
         * @param targetProperty defines property to animate
         * @param framePerSecond defines the Number of frame per second yo use
         * @param totalFrame defines the Number of frames in total
         * @param from defines the initial value
         * @param to defines the final value
         * @param loopMode defines which loop mode you want to use (off by default)
         * @param easingFunction defines the easing function to use (linear by default)
         * @param onAnimationEnd defines the callback to call when animation end
         * @returns the animatable created for this animation
         */
        fun CreateAndStartAnimation(
            name: String, node: Node, targetProperty: String,
            framePerSecond: Number, totalFrame: Number,
            from: Any, to: Any,
            loopMode: Number? = definedExternally, easingFunction: EasingFunction? = definedExternally,
            onAnimationEnd: (() -> Unit)? = definedExternally
        ): Animatable?
        /**
         * Create and start an animation on a node and its descendants
         * @param name defines the name of the global animation that will be run on all nodes
         * @param node defines the root node where the animation will take place
         * @param directDescendantsOnly if true only direct descendants will be used, if false direct and also indirect (children of children, an so on in a recursive manner) descendants will be used
         * @param targetProperty defines property to animate
         * @param framePerSecond defines the Number of frame per second to use
         * @param totalFrame defines the Number of frames in total
         * @param from defines the initial value
         * @param to defines the final value
         * @param loopMode defines which loop mode you want to use (off by default)
         * @param easingFunction defines the easing function to use (linear by default)
         * @param onAnimationEnd defines the callback to call when an animation ends (will be called once per node)
         * @returns the list of animatables created for all nodes
         * @example https://www.babylonjs-playground.com/#MH0VLI
         */
        fun CreateAndStartHierarchyAnimation(name: String, node: Node, directDescendantsOnly: Boolean, targetProperty: String, framePerSecond: Number, totalFrame: Number, from: Any, to: Any, loopMode: Number?, easingFunction: EasingFunction?, onAnimationEnd: (() -> Unit)?): Array<Animatable>?
        /**
         * Creates a new animation, merges it with the existing animations and starts it
         * @param name Name of the animation
         * @param node Node which contains the scene that begins the animations
         * @param targetProperty Specifies which property to animate
         * @param framePerSecond The frames per second of the animation
         * @param totalFrame The total Number of frames
         * @param from The frame at the beginning of the animation
         * @param to The frame at the end of the animation
         * @param loopMode Specifies the loop mode of the animation
         * @param easingFunction (Optional) The easing function of the animation, which allow custom mathematical formulas for animations
         * @param onAnimationEnd Callback to run once the animation is complete
         * @returns Nullable animation
         */
        fun CreateMergeAndStartAnimation(name: String, node: Node, targetProperty: String, framePerSecond: Number, totalFrame: Number, from: Any, to: Any, loopMode: Number?, easingFunction: EasingFunction?, onAnimationEnd: (() -> Unit)?): Animatable?
        /**
         * Transition property of an host to the target Value
         * @param property The property to transition
         * @param targetValue The target Value of the property
         * @param host The object where the property to animate belongs
         * @param scene Scene used to run the animation
         * @param frameRate Framerate (in frame/s) to use
         * @param transition The transition type we want to use
         * @param duration The duration of the animation, in milliseconds
         * @param onAnimationEnd Callback trigger at the end of the animation
         * @returns Nullable animation
         */
        fun TransitionTo(property: String, targetValue: Any, host: Any, scene: Scene, frameRate: Number, transition: Animation, duration: Number, onAnimationEnd: (() -> Unit)?): Animatable?

        /**
         * Get the float animation type
         */
        val ANIMATIONTYPE_FLOAT: Number
        /**
         * Get the Vector3 animation type
         */
        val ANIMATIONTYPE_VECTOR3: Number
        /**
         * Get the Vector2 animation type
         */
        val ANIMATIONTYPE_VECTOR2: Number
        /**
         * Get the Size animation type
         */
        val ANIMATIONTYPE_SIZE: Number
        /**
         * Get the Quaternion animation type
         */
        val ANIMATIONTYPE_QUATERNION: Number
        /**
         * Get the Matrix animation type
         */
        val ANIMATIONTYPE_MATRIX: Number
        /**
         * Get the Color3 animation type
         */
        val ANIMATIONTYPE_COLOR3: Number
        /**
         * Get the Relative Loop Mode
         */
        val ANIMATIONLOOPMODE_RELATIVE: Number
        /**
         * Get the Cycle Loop Mode
         */
        val ANIMATIONLOOPMODE_CYCLE: Number
        /**
         * Get the Constant Loop Mode
         */
        val ANIMATIONLOOPMODE_CONSTANT: Number
        
        /** @hidden */
        fun _UniversalLerp(left: Any, right: Any, amount: Number): Any
        /**
         * Parses an animation object and creates an animation
         * @param parsedAnimation Parsed animation object
         * @returns Animation object
         */
        fun Parse(parsedAnimation: Any): Animation
        /**
         * Appends the serialized animations from the source animations
         * @param source Source containing the animations
         * @param destination Target to store the animations
         */
        fun AppendSerializedAnimations(source: IAnimatable, destination: Any)
    }
}

external class RuntimeAnimation {
    /**
     * Gets the current frame of the runtime animation
     */
    val currentFrame: Number
    /**
     * Gets the weight of the runtime animation
     */
    val weight: Number
    /**
     * Gets the current value of the runtime animation
     */
    val currentValue: Any
    /**
     * Gets the target path of the runtime animation
     */
    val targetPath: String
    /**
     * Gets the actual target of the runtime animation
     */
    val target: Any

    /**
     * Create a new RuntimeAnimation object
     * @param target defines the target of the animation
     * @param animation defines the source animation object
     * @param scene defines the hosting scene
     * @param host defines the initiating Animatable
     */
    constructor(target: Any, animation: Animation, scene: Scene, host: Animatable)

    /**
     * Gets the animation from the runtime animation
     */
    val animation: Animation
    /**
     * Resets the runtime animation to the beginning
     * @param restoreOriginal defines whether to restore the target property to the original value
     */
    fun reset(restoreOriginal: Boolean?)
    /**
     * Specifies if the runtime animation is stopped
     * @returns Boolean specifying if the runtime animation is stopped
     */
    fun isStopped(): Boolean
    /**
     * Disposes of the runtime animation
     */
    fun dispose()
    /**
     * Apply the interpolated value to the target
     * @param currentValue defines the value computed by the animation
     * @param weight defines the weight to apply to this value (Defaults to 1.0)
     */
    fun setValue(currentValue: Any, weight: Number)
    /**
     * Move the current animation to a given frame
     * @param frame defines the frame to move to
     */
    fun goToFrame(frame: Number)
    /**
     * @hidden Internal use only
     */
    fun _prepareForSpeedRatioChange(newSpeedRatio: Number)
    /**
     * Execute the current animation
     * @param delay defines the delay to add to the current frame
     * @param from defines the lower bound of the animation range
     * @param to defines the upper bound of the animation range
     * @param loop defines if the current animation must loop
     * @param speedRatio defines the current speed ratio
     * @param weight defines the weight of the animation (default is -1 so no weight)
     * @param onLoop optional callback called when animation loops
     * @returns a Boolean indicating if the animation is running
     */
    fun animate(delay: Number, from: Number, to: Number, loop: Boolean, speedRatio: Number, weight: Number?): Boolean
}
