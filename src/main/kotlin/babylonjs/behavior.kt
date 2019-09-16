@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals")
package babylonjs

/**
 * Interface used to define a behavior
 */
external interface Behavior<T> {
    /** gets or sets behavior's name */
    val name: String
    /**
     * Function called when the behavior needs to be initialized (after attaching it to a target)
     */
    fun `init`()
    /**
     * Called when the behavior is attached to a target
     * @param target defines the target where the behavior is attached to
     */
    fun attach(target: T)
    /**
     * Called when the behavior is detached from its target
     */
    fun detach()
}

/**
 * Interface implemented by classes supporting behaviors
 */
external interface IBehaviorAware<T> {
    /**
     * Attach a behavior
     * @param behavior defines the behavior to attach
     * @returns the current host
     */
    fun addBehavior(behavior: Behavior<T>, attachImmediately: Boolean? = definedExternally): T
    /**
     * Remove a behavior from the current object
     * @param behavior defines the behavior to detach
     * @returns the current host
     */
    fun removeBehavior(behavior: Behavior<T>): T
    /**
     * Gets a behavior using its name to search
     * @param name defines the name to search
     * @returns the behavior or null if not found
     */
    fun getBehaviorByName(name: String): Behavior<T>?
}

/**
 * A behavior that when attached to a mesh will allow the mesh to be dragged around the screen based on pointer events
 */
external class PointerDragBehavior: Behavior<Node> {
    /**
     * The maximum tolerated angle between the drag plane and dragging pointer rays to trigger pointer events. Set to 0 to allow any angle (default: 0)
     */
    var maxDragAngle: Number
    /**
     * The id of the pointer that is currently interacting with the behavior (-1 when no pointer is active)
     */
    var currentDraggingPointerID: Number
    /**
     * The last position where the pointer hit the drag plane in world space
     */
    var lastDragPosition: Vector3
    /**
     * If the behavior is currently in a dragging state
     */
    var dragging: Boolean
    /**
     * The distance towards the target drag position to move each frame. This can be useful to avoid jitter. Set this to 1 for no delay. (Default: 0.2)
     */
    var dragDeltaRatio: Number
    /**
     * If the drag plane orientation should be updated during the dragging (Default: true)
     */
    var updateDragPlane: Boolean
    /**
     *  Fires each time the attached mesh is dragged with the pointer
     *  * delta between last drag position and current drag position in world space
     *  * dragDistance along the drag axis
     *  * dragPlaneNormal normal of the current drag plane used during the drag
     *  * dragPlanePoint in world space where the drag intersects the drag plane
     */
    var onDragObservable: Observable<DragStatus>
    /**
     *  Fires each time a drag begins (eg. mouse down on mesh)
     */
    var onDragStartObservable: Observable<DragStartEnd>
    /**
     *  Fires each time a drag ends (eg. mouse release after drag)
     */
    var onDragEndObservable: Observable<DragStartEnd>
    /**
     *  If the attached mesh should be moved when dragged
     */
    var moveAttached: Boolean
    /**
     *  If the drag behavior will react to drag events (Default: true)
     */
    var enabled: Boolean
    /**
     * If camera controls should be detached during the drag
     */
    var detachCameraControls: Boolean
    /**
     * If set, the drag plane/axis will be rotated based on the attached mesh's world rotation (Default: true)
     */
    var useObjectOrienationForDragging: Boolean
    /**
     * Creates a pointer drag behavior that can be attached to a mesh
     * @param options The drag axis or normal of the plane that will be dragged across. If no options are specified the drag plane will always face the ray's origin (eg. camera)
     */
    constructor(options: DragBehaviorOptions? = definedExternally)
    /**
     * Predicate to determine if it is valid to move the object to a new position when it is moved
     */
    var validateDrag: (targetPosition: Vector3) -> Boolean
    /**
     *  The name of the behavior
     */
    override val name: String
    /**
     *  Initializes the behavior
     */
    override fun init()
    /**
     * Attaches the drag behavior the passed in mesh
     * @param target The mesh that will be dragged around once attached
     */
    override fun attach(target: Node)
    /**
     * Force relase the drag action by code.
     */
    fun releaseDrag()
    /**
     * Simulates the start of a pointer drag event on the behavior
     * @param pointerId pointerID of the pointer that should be simulated (Default: Any mouse pointer ID)
     * @param fromRay initial ray of the pointer to be simulated (Default: Ray from camera to attached mesh)
     * @param startPickedPoint picked point of the pointer to be simulated (Default: attached mesh position)
     */
    fun startDrag(pointerId: Number?, fromRay: Ray?, startPickedPoint: Vector3?)
    /**
     *  Detaches the behavior from the mesh
     */
    override fun detach()
}
