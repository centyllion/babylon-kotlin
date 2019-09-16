@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals")
package babylonjs

import org.w3c.dom.HTMLElement

/**
 * This is the base class of all the camera used in the application.
 * @see [http://doc.babylonjs.com/features/cameras]
 */
open external class Camera : Node {
    /**
     * Define the input manager associated with the camera.
     */
    var inputs: CameraInputsManager<Camera>
    /**
     * Define the current local position of the camera in the scene
     */
    var position: Vector3
    /**
     * The vector the camera should consider as up.
     * (default is Vector3(0, 1, 0) aka Vector3.Up())
     */
    var upVector: Vector3
    /**
     * Define the current limit on the left side for an orthographic camera
     * In scene unit
     */
    var orthoLeft: Number?
    /**
     * Define the current limit on the right side for an orthographic camera
     * In scene unit
     */
    var orthoRight: Number?
    /**
     * Define the current limit on the bottom side for an orthographic camera
     * In scene unit
     */
    var orthoBottom: Number?
    /**
     * Define the current limit on the top side for an orthographic camera
     * In scene unit
     */
    var orthoTop: Number?
    /**
     * Field Of View is set in Radians. (default is 0.8)
     */
    var fov: Number
    /**
     * Define the minimum distance the camera can see from.
     * This is important to note that the depth buffer are not infinite and the closer it starts
     * the more your scene might encounter depth fighting issue.
     */
    var minZ: Number
    /**
     * Define the maximum distance the camera can see to.
     * This is important to note that the depth buffer are not infinite and the further it end
     * the more your scene might encounter depth fighting issue.
     */
    var maxZ: Number
    /**
     * Define the default inertia of the camera.
     * This helps giving a smooth feeling to the camera movement.
     */
    var inertia: Number
    /**
     * Define the mode of the camera (Camera.PERSPECTIVE_CAMERA or Camera.PERSPECTIVE_ORTHOGRAPHIC)
     */
    var mode: Number
    /**
     * Define wether the camera is intermediate.
     * This is useful to not present the output directly to the screen in case of rig without post process for instance
     */
    var isIntermediate: Boolean
    /**
     * Define the viewport of the camera.
     * This correspond to the portion of the screen the camera will render to in normalized 0 to 1 unit.
     */
    var viewport: Viewport
    /**
     * Restricts the camera to viewing objects with the same layerMask.
     * A camera with a layerMask of 1 will render mesh.layerMask & camera.layerMask!== 0
     */
    var layerMask: Number
    /**
     * fovMode sets the camera frustum bounds to the viewport bounds. (default is FOVMODE_VERTICAL_FIXED)
     */
    var fovMode: Number
    /**
     * Rig mode of the camera.
     * This is useful to create the camera with two "eyes" instead of one to create VR or stereoscopic scenes.
     * This is normally controlled byt the camera themselves as internal use.
     */
    var cameraRigMode: Number
    /**
     * Defines the distance between both "eyes" in case of a RIG
     */
    var interaxialDistance: Number
    /**
     * Defines if stereoscopic rendering is done side by side or over under.
     */
    var isStereoscopicSideBySide: Boolean
    /**
     * Defines the list of custom render target which are rendered to and then used as the input to this camera's render. Eg. display another camera view on a TV in the main scene
     * This is pretty helpfull if you wish to make a camera render to a texture you could reuse somewhere
     * else in the scene.
     */
    var customRenderTargets: Array<RenderTargetTexture>
    /**
     * When set, the camera will render to this render target instead of the default canvas
     */
    var outputRenderTarget: RenderTargetTexture?
    /**
     * Observable triggered when the camera view matrix has changed.
     */
    var onViewMatrixChangedObservable: Observable<Camera>
    /**
     * Observable triggered when the camera Projection matrix has changed.
     */
    var onProjectionMatrixChangedObservable: Observable<Camera>
    /**
     * Observable triggered when the inputs have been processed.
     */
    var onAfterCheckInputsObservable: Observable<Camera>
    /**
     * Observable triggered when reset has been called and applied to the camera.
     */
    var onRestoreStateObservable: Observable<Camera>
    /**
     * Instantiates a new camera object.
     * This should not be used directly but through the inherited cameras: ArcRotate, Free...
     * @see [http://doc.babylonjs.com/features/cameras]
     * @param name Defines the name of the camera in the scene
     * @param position Defines the position of the camera
     * @param scene Defines the scene the camera belongs too
     * @param setActiveOnSceneIfNoneActive Defines if the camera should be set as active after creation if no other camera have been defined in the scene
     */
    constructor(name: String, position: Vector3, scene: Scene, setActiveOnSceneIfNoneActive: Boolean?)
    /**
     * Store current camera state (fov, position, etc..)
     * @returns the camera
     */
    fun storeState(): Camera
    /**
     * Restored camera state. You must call storeState() first.
     * @returns true if restored and false otherwise
     */
    fun restoreState(): Boolean
    
    /**
     * Gets a String representation of the camera useful for debug purpose.
     * @param fullDetails Defines that a more verboe level of logging is required
     * @returns the String representation
     */
    fun toString(fullDetails: Boolean?): String
    /**
     * Gets the current world space position of the camera.
     */
    val globalPosition: Vector3
    /**
     * Gets the list of active meshes this frame (meshes no culled or excluded by lod s in the frame)
     * @returns the active meshe list
     */
    fun getActiveMeshes(): SmartArray<AbstractMesh>
    /**
     * Check wether a mesh is part of the current active mesh list of the camera
     * @param mesh Defines the mesh to check
     * @returns true if active, false otherwise
     */
    fun isActiveMesh(mesh: Mesh): Boolean
    /**
     * Attach the input controls to a specific dom element to get the input from.
     * @param element Defines the element the controls should be listened from
     * @param noPreventDefault Defines whether event caught by the controls should call preventdefault() ([https://developer.mozilla.org/en-US/docs/Web/API/Event/preventDefault)]
     */
    fun attachControl(element: HTMLElement, noPreventDefault: Boolean?)
    /**
     * Detach the current controls from the specified dom element.
     * @param element Defines the element to stop listening the inputs from
     */
    fun detachControl(element: HTMLElement)
    /**
     * Update the camera state according to the different inputs gathered during the frame.
     */
    fun update()
    /**
     * Gets the post process used by the rig cameras
     */
    val rigPostProcess: PostProcess?
    /**
     * Attach a post process to the camera.
     * @see [http://doc.babylonjs.com/how_to/how_to_use_postprocesses#attach-postprocess]
     * @param postProcess The post process to attach to the camera
     * @param insertAt The position of the post process in case several of them are in use in the scene
     * @returns the position the post process has been inserted at
     */
    fun attachPostProcess(postProcess: PostProcess, insertAt: Number?): Number
    /**
     * Detach a post process to the camera.
     * @see [http://doc.babylonjs.com/how_to/how_to_use_postprocesses#attach-postprocess]
     * @param postProcess The post process to detach from the camera
     */
    fun detachPostProcess(postProcess: PostProcess)
    /**
     * Gets the current view matrix of the camera.
     * @param force forces the camera to recompute the matrix without looking at the cached state
     * @returns the view matrix
     */
    fun getViewMatrix(force: Boolean?): Matrix
    /**
     * Freeze the projection matrix.
     * It will prevent the cache check of the camera projection compute and can speed up perf
     * if no parameter of the camera are meant to change
     * @param projection Defines manually a projection if necessary
     */
    fun freezeProjectionMatrix(projection: Matrix?)
    /**
     * Unfreeze the projection matrix if it has previously been freezed by freezeProjectionMatrix.
     */
    fun unfreezeProjectionMatrix()
    /**
     * Gets the current projection matrix of the camera.
     * @param force forces the camera to recompute the matrix without looking at the cached state
     * @returns the projection matrix
     */
    fun getProjectionMatrix(force: Boolean?): Matrix
    /**
     * Gets the transformation matrix (ie. the multiplication of view by projection matrices)
     * @returns a Matrix
     */
    fun getTransformationMatrix(): Matrix
    /**
     * Checks if a cullable object (mesh...) is in the camera frustum
     * This checks the bounding box center. See isCompletelyInFrustum for a full bounding check
     * @param target The object to check
     * @param checkRigCameras If the rig cameras should be checked (eg. with webVR camera both eyes should be checked) (Default: false)
     * @returns true if the object is in frustum otherwise false
     */
    fun isInFrustum(target: ICullable, checkRigCameras: Boolean?): Boolean
    /**
     * Checks if a cullable object (mesh...) is in the camera frustum
     * Unlike isInFrustum this cheks the full bounding box
     * @param target The object to check
     * @returns true if the object is in frustum otherwise false
     */
    fun isCompletelyInFrustum(target: ICullable): Boolean
    /**
     * Gets a ray in the forward direction from the camera.
     * @param length Defines the length of the ray to create
     * @param transform Defines the transform to apply to the ray, by default the world matrx is used to create a workd space ray
     * @param origin Defines the start point of the ray which defaults to the camera position
     * @returns the forward ray
     */
    fun getForwardRay(length: Number?, transform: Matrix?, origin: Vector3?): Ray
    /**
     * Gets the left camera of a rig setup in case of Rigged Camera
     */
    val isLeftCamera: Boolean
    /**
     * Gets the right camera of a rig setup in case of Rigged Camera
     */
    val isRightCamera: Boolean
    /**
     * Gets the left camera of a rig setup in case of Rigged Camera
     */
    val leftCamera: FreeCamera?
    /**
     * Gets the right camera of a rig setup in case of Rigged Camera
     */
    val rightCamera: FreeCamera?
    /**
     * Gets the left camera target of a rig setup in case of Rigged Camera
     * @returns the target position
     */
    fun getLeftTarget(): Vector3?
    /**
     * Gets the right camera target of a rig setup in case of Rigged Camera
     * @returns the target position
     */
    fun getRightTarget(): Vector3?
    /**
     * Serialiaze the camera setup to a json represention
     * @returns the JSON representation
     */
    fun serialize(): Any
    /**
     * Clones the current camera.
     * @param name The cloned camera name
     * @returns the cloned camera
     */
    fun clone(name: String): Camera
    /**
     * Gets the direction of the camera relative to a given local axis.
     * @param localAxis Defines the reference axis to provide a relative direction.
     * @return the direction
     */
    fun getDirection(localAxis: Vector3): Vector3
    /**
     * Gets the direction of the camera relative to a given local axis into a passed vector.
     * @param localAxis Defines the reference axis to provide a relative direction.
     * @param result Defines the vector to store the result in
     */
    fun getDirectionToRef(localAxis: Vector3, result: Vector3)
    /**
     * Compute the world  matrix of the camera.
     * @returns the camera workd matrix
     */
    fun computeWorldMatrix(): Matrix

    companion object {
        /**
         * This is the default projection mode used by the cameras.
         * It helps recreating a feeling of perspective and better appreciate depth.
         * This is the best way to simulate real life cameras.
         */
        val PERSPECTIVE_CAMERA: Number
        /**
         * This helps creating camera with an orthographic mode.
         * Orthographic is commonly used in engineering as a means to produce object specifications that communicate dimensions unambiguously, each line of 1 unit length (cm, meter..whatever) will appear to have the same length everywhere on the drawing. This allows the drafter to dimension only a subset of lines and let the reader know that other lines of that length on the drawing are also that length in reality. Every parallel line in the drawing is also parallel in the object.
         */
        val ORTHOGRAPHIC_CAMERA: Number
        /**
         * This is the default FOV mode for perspective cameras.
         * This setting aligns the upper and lower bounds of the viewport to the upper and lower bounds of the camera frustum.
         */
        val FOVMODE_VERTICAL_FIXED: Number
        /**
         * This setting aligns the left and right bounds of the viewport to the left and right bounds of the camera frustum.
         */
        val FOVMODE_HORIZONTAL_FIXED: Number
        /**
         * This specifies ther is no need for a camera rig.
         * Basically only one eye is rendered corresponding to the camera.
         */
        val RIG_MODE_NONE: Number
        /**
         * Simulates a camera Rig with one blue eye and one red eye.
         * This can be use with 3d blue and red glasses.
         */
        val RIG_MODE_STEREOSCOPIC_ANAGLYPH: Number
        /**
         * Defines that both eyes of the camera will be rendered side by side with a parallel target.
         */
        val RIG_MODE_STEREOSCOPIC_SIDEBYSIDE_PARALLEL: Number
        /**
         * Defines that both eyes of the camera will be rendered side by side with a none parallel target.
         */
        val RIG_MODE_STEREOSCOPIC_SIDEBYSIDE_CROSSEYED: Number
        /**
         * Defines that both eyes of the camera will be rendered over under each other.
         */
        val RIG_MODE_STEREOSCOPIC_OVERUNDER: Number
        /**
         * Defines that both eyes of the camera should be renderered in a VR mode (carbox).
         */
        val RIG_MODE_VR: Number
        /**
         * Defines that both eyes of the camera should be renderered in a VR mode (webVR).
         */
        val RIG_MODE_WEBVR: Number
        /**
         * Custom rig mode allowing rig cameras to be populated manually with Any Number of cameras
         */
        val RIG_MODE_CUSTOM: Number
        /**
         * Defines if by default attaching controls should prevent the default javascript event to continue.
         */
        var ForceAttachControlToAlwaysPreventDefault: Boolean
        /**
         * Gets a camera constructor for a given camera type
         * @param type The type of the camera to construct (should be equal to one of the camera class name)
         * @param name The name of the camera the result will be able to instantiate
         * @param scene The scene the result will construct the camera in
         * @param interaxial_distance In case of stereoscopic setup, the distance between both eyes
         * @param isStereoscopicSideBySide In case of stereoscopic setup, should the sereo be side b side
         * @returns a factory method to construc the camera
         */
        fun GetConstructorFromName(type: String, name: String, scene: Scene, interaxial_distance: Number?, isStereoscopicSideBySide: Boolean?): () -> Camera
        /**
         * Parse a JSON and creates the camera from the parsed information
         * @param parsedCamera The JSON to parse
         * @param scene The scene to instantiate the camera in
         * @returns the newly constructed camera
         */
        fun Parse(parsedCamera: Any, scene: Scene): Camera
    }
}

/**
 * A target camera takes a mesh or position as a target and continues to look at it while it moves.
 * This is the base of the follow, arc rotate cameras and Free camera
 * @see [http://doc.babylonjs.com/features/cameras]
 */
open external class TargetCamera : Camera {
    /**
     * Define the current direction the camera is moving to
     */
    var cameraDirection: Vector3
    /**
     * Define the current rotation the camera is rotating to
     */
    var cameraRotation: Vector2
    /**
     * When set, the up vector of the camera will be updated by the rotation of the camera
     */
    var updateUpVectorFromRotation: Boolean
    /**
     * Define the current rotation of the camera
     */
    var rotation: Vector3
    /**
     * Define the current rotation of the camera as a quaternion to prevent Gimbal lock
     */
    var rotationQuaternion: Quaternion
    /**
     * Define the current speed of the camera
     */
    var speed: Number
    /**
     * Add cconstraint to the camera to prevent it to move freely in all directions and
     * around all axis.
     */
    var noRotationConstraint: Boolean
    /**
     * Define the current target of the camera as an object or a position.
     */
    var lockedTarget: Any
    /**
     * Instantiates a target camera that takes a meshor position as a target and continues to look at it while it moves.
     * This is the base of the follow, arc rotate cameras and Free camera
     * @see [http://doc.babylonjs.com/features/cameras]
     * @param name Defines the name of the camera in the scene
     * @param position Defines the start position of the camera in the scene
     * @param scene Defines the scene the camera belongs to
     * @param setActiveOnSceneIfNoneActive Defines wheter the camera should be marked as active if not other active cameras have been defined
     */
    constructor(name: String, position: Vector3, scene: Scene, setActiveOnSceneIfNoneActive: Boolean?)
    /**
     * Gets the position in front of the camera at a given distance.
     * @param distance The distance from the camera we want the position to be
     * @returns the position
     */
    fun getFrontPosition(distance: Number): Vector3
    /**
     * Defines the target the camera should look at.
     * This will automatically adapt alpha beta and radius to fit within the new target.
     * @param target Defines the new target as a Vector or a mesh
     */
    fun setTarget(target: Vector3)
    /**
     * Return the current target position of the camera. This value is expressed in local space.
     * @returns the target position
     */
    fun getTarget(): Vector3
}

/**
* This represents an orbital type of camera.
*
* This camera always points towards a given target position and can be rotated around that target with the target as the centre of rotation. It can be controlled with cursors and mouse, or with touch events.
* Think of this camera as one orbiting its target position, or more imaginatively as a spy satellite orbiting the earth. Its position relative to the target (earth) can be set by three parameters, alpha (radians) the longitudinal rotation, beta (radians) the latitudinal rotation and radius the distance from the target position.
* @see [http://doc.babylonjs.com/babylon101/cameras#arc-rotate-camera]
*/
external class ArcRotateCamera : TargetCamera {
    /**
     * Defines the rotation angle of the camera along the longitudinal axis.
     */
    var alpha: Number
    /**
     * Defines the rotation angle of the camera along the latitudinal axis.
     */
    var beta: Number
    /**
     * Defines the radius of the camera from it s target point.
     */
    var radius: Number
    /**
     * Defines the target point of the camera.
     * The camera looks towards it form the radius distance.
     */
    var target: Vector3
    /**
     * Sets the Y-up to camera up-vector rotation matrix, and the up-vector to Y-up rotation matrix.
     */
    fun setMatUp()
    /**
     * Current inertia value on the longitudinal axis.
     * The bigger this Number the longer it will take for the camera to stop.
     */
    var inertialAlphaOffset: Number
    /**
     * Current inertia value on the latitudinal axis.
     * The bigger this Number the longer it will take for the camera to stop.
     */
    var inertialBetaOffset: Number
    /**
     * Current inertia value on the radius axis.
     * The bigger this Number the longer it will take for the camera to stop.
     */
    var inertialRadiusOffset: Number
    /**
     * Minimum allowed angle on the longitudinal axis.
     * This can help limiting how the Camera is able to move in the scene.
     */
    var lowerAlphaLimit: Number?
    /**
     * Maximum allowed angle on the longitudinal axis.
     * This can help limiting how the Camera is able to move in the scene.
     */
    var upperAlphaLimit: Number?
    /**
     * Minimum allowed angle on the latitudinal axis.
     * This can help limiting how the Camera is able to move in the scene.
     */
    var lowerBetaLimit: Number
    /**
     * Maximum allowed angle on the latitudinal axis.
     * This can help limiting how the Camera is able to move in the scene.
     */
    var upperBetaLimit: Number
    /**
     * Minimum allowed distance of the camera to the target (The camera can not get closer).
     * This can help limiting how the Camera is able to move in the scene.
     */
    var lowerRadiusLimit: Number?
    /**
     * Maximum allowed distance of the camera to the target (The camera can not get further).
     * This can help limiting how the Camera is able to move in the scene.
     */
    var upperRadiusLimit: Number?
    /**
     * Defines the current inertia value used during panning of the camera along the X axis.
     */
    var inertialPanningX: Number
    /**
     * Defines the current inertia value used during panning of the camera along the Y axis.
     */
    var inertialPanningY: Number
    /**
     * Defines the distance used to consider the camera in pan mode vs pinch/zoom.
     * Basically if your fingers moves away from more than this distance you will be considered
     * in pinch mode.
     */
    var pinchToPanMaxDistance: Number
    /**
     * Defines the maximum distance the camera can pan.
     * This could help keeping the cammera always in your scene.
     */
    var panningDistanceLimit: Number?
    /**
     * Defines the target of the camera before paning.
     */
    var panningOriginTarget: Vector3
    /**
     * Defines the value of the inertia used during panning.
     * 0 would mean stop inertia and one would mean no decelleration at all.
     */
    var panningInertia: Number
    /**
     * Gets or Set the pointer angular sensibility  along the X axis or how fast is the camera rotating.
     */
    var angularSensibilityX: Number
    /**
     * Gets or Set the pointer angular sensibility along the Y axis or how fast is the camera rotating.
     */
    var angularSensibilityY: Number
    /**
     * Gets or Set the pointer pinch precision or how fast is the camera zooming.
     */
    var pinchPrecision: Number
    /**
     * Gets or Set the pointer pinch delta percentage or how fast is the camera zooming.
     * It will be used instead of pinchDeltaPrecision if different from 0.
     * It defines the percentage of current camera.radius to use as delta when pinch zoom is used.
     */
    var pinchDeltaPercentage: Number
    /**
     * Gets or Set the pointer panning sensibility or how fast is the camera moving.
     */
    var panningSensibility: Number
    /**
     * Gets or Set the list of keyboard keys used to control beta angle in a positive direction.
     */
    var keysUp: Array<Number>
    /**
     * Gets or Set the list of keyboard keys used to control beta angle in a negative direction.
     */
    var keysDown: Array<Number>
    /**
     * Gets or Set the list of keyboard keys used to control alpha angle in a negative direction.
     */
    var keysLeft: Array<Number>
    /**
     * Gets or Set the list of keyboard keys used to control alpha angle in a positive direction.
     */
    var keysRight: Array<Number>
    /**
     * Gets or Set the mouse wheel precision or how fast is the camera zooming.
     */
    var wheelPrecision: Number
    /**
     * Gets or Set the mouse wheel delta percentage or how fast is the camera zooming.
     * It will be used instead of pinchDeltaPrecision if different from 0.
     * It defines the percentage of current camera.radius to use as delta when pinch zoom is used.
     */
    var wheelDeltaPercentage: Number
    /**
     * Defines how much the radius should be scaled while zomming on a particular mesh (through the zoomOn function)
     */
    var zoomOnFactor: Number
    /**
     * Defines a screen offset for the camera position.
     */
    var targetScreenOffset: Vector2
    /**
     * Allows the camera to be completely reversed.
     * If false the camera can not arrive upside down.
     */
    var allowUpsideDown: Boolean
    /**
     * Define if double tap/click is used to restore the previously saved state of the camera.
     */
    var useInputToRestoreState: Boolean
    /**
     * Defines the allowed panning axis.
     */
    var panningAxis: Vector3
    /**
     * Gets the bouncing behavior of the camera if it has been enabled.
     * @see [http://doc.babylonjs.com/how_to/camera_behaviors#bouncing-behavior]
     */
    val bouncingBehavior: BouncingBehavior?
    /**
     * Defines if the bouncing behavior of the camera is enabled on the camera.
     * @see [http://doc.babylonjs.com/how_to/camera_behaviors#bouncing-behavior]
     */
    var useBouncingBehavior: Boolean
    /**
     * Gets the framing behavior of the camera if it has been enabled.
     * @see [http://doc.babylonjs.com/how_to/camera_behaviors#framing-behavior]
     */
    val framingBehavior: FramingBehavior?
    /**
     * Defines if the framing behavior of the camera is enabled on the camera.
     * @see [http://doc.babylonjs.com/how_to/camera_behaviors#framing-behavior]
     */
    var useFramingBehavior: Boolean
    /**
     * Gets the auto rotation behavior of the camera if it has been enabled.
     * @see [http://doc.babylonjs.com/how_to/camera_behaviors#autorotation-behavior]
     */
    val autoRotationBehavior: AutoRotationBehavior?
    /**
     * Defines if the auto rotation behavior of the camera is enabled on the camera.
     * @see [http://doc.babylonjs.com/how_to/camera_behaviors#autorotation-behavior]
     */
    var useAutoRotationBehavior: Boolean
    /**
     * Observable triggered when the mesh target has been changed on the camera.
     */
    var onMeshTargetChangedObservable: Observable<AbstractMesh?>
    /**
     * Event raised when the camera is colliding with a mesh.
     */
    var onCollide: (collidedMesh: AbstractMesh) -> Unit
    /**
     * Defines whether the camera should check collision with the objects oh the scene.
     * @see [http://doc.babylonjs.com/babylon101/cameras,_mesh_collisions_and_gravity#how-can-i-do-this]
     */
    var checkCollisions: Boolean
    /**
     * Defines the collision radius of the camera.
     * This simulates a sphere around the camera.
     * @see [http://doc.babylonjs.com/babylon101/cameras,_mesh_collisions_and_gravity#arcrotatecamera]
     */
    var collisionRadius: Vector3
    /**
     * Instantiates a new ArcRotateCamera in a given scene
     * @param name Defines the name of the camera
     * @param alpha Defines the camera rotation along the logitudinal axis
     * @param beta Defines the camera rotation along the latitudinal axis
     * @param radius Defines the camera distance from its target
     * @param target Defines the camera target
     * @param scene Defines the scene the camera belongs to
     * @param setActiveOnSceneIfNoneActive Defines wheter the camera should be marked as active if not other active cameras have been defined
     */
    constructor(name: String, alpha: Number, beta: Number, radius: Number, target: Vector3, scene: Scene, setActiveOnSceneIfNoneActive: Boolean? = definedExternally)
    
    /**
     * Attached controls to the current camera.
     * @param element Defines the element the controls should be listened from
     * @param noPreventDefault Defines whether event caught by the controls should call preventdefault() ([https://developer.mozilla.org/en-US/docs/Web/API/Event/preventDefault)]
     * @param useCtrlForPanning  Defines whether ctrl is used for paning within the controls
     * @param panningMouseButton Defines whether panning is allowed through mouse click button
     */
    fun attachControl(element: HTMLElement, noPreventDefault: Boolean?, useCtrlForPanning: Boolean?, panningMouseButton: Number?)
    /**
     * Rebuilds angles (alpha, beta) and radius from the give position and target
     */
    fun rebuildAnglesAndRadius()
    /**
     * Use a position to define the current camera related information like aplha, beta and radius
     * @param position Defines the position to set the camera at
     */
    fun setPosition(position: Vector3)
    /**
     * Defines the target the camera should look at.
     * This will automatically adapt alpha beta and radius to fit within the new target.
     * @param target Defines the new target as a Vector or a mesh
     * @param toBoundingCenter In case of a mesh target, defines wether to target the mesh position or its bounding information center
     * @param allowSamePosition If false, prevents reapplying the new computed position if it is identical to the current one (optim)
     */
    fun setTarget(target: Vector3, toBoundingCenter: Boolean?, allowSamePosition: Boolean?)
    /**
     * Defines the target the camera should look at.
     * This will automatically adapt alpha beta and radius to fit within the new target.
     * @param target Defines the new target as a Vector or a mesh
     * @param toBoundingCenter In case of a mesh target, defines wether to target the mesh position or its bounding information center
     * @param allowSamePosition If false, prevents reapplying the new computed position if it is identical to the current one (optim)
     */
    fun setTarget(target: AbstractMesh, toBoundingCenter: Boolean?, allowSamePosition: Boolean?)
    /**
     * Zooms on a mesh to be at the min distance where we could see it fully in the current viewport.
     * @param meshes Defines the mesh to zoom on
     * @param doNotUpdateMaxZ Defines whether or not maxZ should be updated whilst zooming on the mesh (this can happen if the mesh is big and the maxradius pretty small for instance)
     */
    fun zoomOn(meshes: Array<AbstractMesh?>, doNotUpdateMaxZ: Boolean?)
    /**
     * Focus on a mesh or a bounding box. This adapts the target and maxRadius if necessary but does not update the current radius.
     * The target will be changed but the radius
     * @param meshesOrMinMaxVectorAndDistance Defines the mesh or bounding info to focus on
     * @param doNotUpdateMaxZ Defines whether or not maxZ should be updated whilst zooming on the mesh (this can happen if the mesh is big and the maxradius pretty small for instance)
     */
    fun focusOn(meshesOrMinMaxVectorAndDistance: Array<AbstractMesh> /*| {
        var min: Vector3
        var max: Vector3
        var distance: Number
    }*/, doNotUpdateMaxZ: Boolean?)
    /**
     * @override
     * Override Camera.createRigCamera
     */
    fun createRigCamera(name: String, cameraIndex: Number): Camera
    /**
     * Destroy the camera and release the current resources hold by it.
     */
    fun dispose()
}
