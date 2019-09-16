@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals")
package babylonjs

import org.w3c.dom.pointerevents.PointerEvent

/**
 * Information about the result of picking within a scene
 * @see [https://doc.babylonjs.com/babylon101/picking_collisions]
 */
external class PickingInfo {
    /**
     * If the pick collided with an object
     */
    var hit: Boolean
    /**
     * Distance away where the pick collided
     */
    var distance: Number
    /**
     * The location of pick collision
     */
    var pickedPoint: Vector3?
    /**
     * The mesh corresponding the the pick collision
     */
    var pickedMesh: AbstractMesh?
    /** (See getTextureCoordinates) The barycentric U coordinate that is used when calulating the texture coordinates of the collision.*/
    var bu: Number
    /** (See getTextureCoordinates) The barycentric V coordinate that is used when calulating the texture coordinates of the collision.*/
    var bv: Number
    /** The index of the face on the mesh that was picked, or the index of the Line if the picked Mesh is a LinesMesh */
    var faceId: Number
    /** Id of the the submesh that was picked */
    var subMeshId: Number
    /** If a sprite was picked, this will be the sprite the pick collided with */
    var pickedSprite: Sprite?
    /**
     * If a mesh was used to do the picking (eg. 6dof controller) this will be populated.
     */
    var originMesh: AbstractMesh?
    /**
     * The ray that was used to perform the picking.
     */
    var ray: Ray?
    /**
     * Gets the normal correspodning to the face the pick collided with
     * @param useWorldCoordinates If the resulting normal should be relative to the world (default: false)
     * @param useVerticesNormals If the vertices normals should be used to calculate the normal instead of the normal map
     * @returns The normal correspodning to the face the pick collided with
     */
    fun getNormal(useWorldCoordinates: Boolean? = definedExternally, useVerticesNormals: Boolean? = definedExternally): Vector3?
    /**
     * Gets the texture coordinates of where the pick occured
     * @returns the vector containing the coordnates of the texture
     */
    fun getTextureCoordinates(): Vector2
}

/**
 * Gather the list of pointer event types as constants.
 */
external class PointerEventTypes {
    companion object {
        /**
         * The pointerdown event is fired when a pointer becomes active. For mouse, it is fired when the device transitions from no buttons depressed to at least one button depressed. For touch, it is fired when physical contact is made with the digitizer. For pen, it is fired when the stylus makes physical contact with the digitizer.
         */
        val POINTERDOWN: Number
        /**
         * The pointerup event is fired when a pointer is no longer active.
         */
        val POINTERUP: Number
        /**
         * The pointermove event is fired when a pointer changes coordinates.
         */
        val POINTERMOVE: Number
        /**
         * The pointerwheel event is fired when a mouse wheel has been rotated.
         */
        val POINTERWHEEL: Number
        /**
         * The pointerpick event is fired when a mesh or sprite has been picked by the pointer.
         */
        val POINTERPICK: Number
        /**
         * The pointertap event is fired when a the object has been touched and released without drag.
         */
        val POINTERTAP: Number
        /**
         * The pointerdoubletap event is fired when a the object has been touched and released twice without drag.
         */
        val POINTERDOUBLETAP: Number
    }
}
/**
 * Base class of pointer info types.
 */
open external class PointerInfoBase {
    /**
     * Defines the type of event (PointerEventTypes)
     */
    var type: Number
    /**
     * Defines the related dom event
     */
    var event: PointerEvent
    /**
     * Instantiates the base class of pointers info.
     * @param type Defines the type of event (PointerEventTypes)
     * @param event Defines the related dom event
     */
    constructor(
        /**
         * Defines the type of event (PointerEventTypes)
         */
        type: Number,
        /**
         * Defines the related dom event
         */
        event: PointerEvent)
}
/**
 * This class is used to store pointer related info for the onPrePointerObservable event.
 * Set the skipOnPointerObservable property to true if you want the engine to stop any process after this event is triggered, even not calling onPointerObservable
 */
external class PointerInfoPre: PointerInfoBase {
    /**
     * Ray from a pointer if availible (eg. 6dof controller)
     */
    var ray: Ray?
    /**
     * Defines the local position of the pointer on the canvas.
     */
    var localPosition: Vector2
    /**
     * Defines whether the engine should skip the next OnPointerObservable associated to this pre.
     */
    var skipOnPointerObservable: Boolean
    /**
     * Instantiates a PointerInfoPre to store pointer related info to the onPrePointerObservable event.
     * @param type Defines the type of event (PointerEventTypes)
     * @param event Defines the related dom event
     * @param localX Defines the local x coordinates of the pointer when the event occured
     * @param localY Defines the local y coordinates of the pointer when the event occured
     */
    constructor(type: Number, event: PointerEvent, localX: Number, localY: Number)
}
/**
 * This type contains all the data related to a pointer event in Babylon.js.
 * The event member is an instance of PointerEvent for all types except PointerWheel and is of type MouseWheelEvent when type equals PointerWheel. The different event types can be found in the PointerEventTypes class.
 */
external class PointerInfo: PointerInfoBase {
    /**
     * Defines the picking info associated to the info (if any)\
     */
    var pickInfo: PickingInfo?
    /**
     * Instantiates a PointerInfo to store pointer related info to the onPointerObservable event.
     * @param type Defines the type of event (PointerEventTypes)
     * @param event Defines the related dom event
     * @param pickInfo Defines the picking info associated to the info (if any)\
     */
    constructor(type: Number, event: PointerEvent,
    /**
     * Defines the picking info associated to the info (if any)\
     */
    pickInfo: PickingInfo?)
}
/**
 * Data relating to a touch event on the screen.
 */
external interface PointerTouch {
    /**
     * X coordinate of touch.
     */
    val x: Number
    /**
     * Y coordinate of touch.
     */
    val y: Number
    /**
     * Id of touch. Unique for each finger.
     */
    val pointerId: Number
    /**
     * Event type passed from DOM.
     */
    val type: Any
}
