@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals")
package babylonjs

import org.w3c.media.AudioBuffer
import org.w3c.media.AudioNode

/**
 * Defines a sound that can be played in the application.
 * The sound can either be an ambient track or a simple sound played in reaction to a user action.
 * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music]
 */
external class Sound {
    /**
     * The name of the sound in the scene.
     */
    var name: String
    /**
     * Does the sound autoplay once loaded.
     */
    var autoplay: Any
    /**
     * Does the sound loop after it finishes playing once.
     */
    var loop: Any
    /**
     * Does the sound use a custom attenuation curve to simulate the falloff
     * happening when the source gets further away from the camera.
     * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music#creating-your-own-custom-attenuation-function]
     */
    var useCustomAttenuation: Any
    /**
     * The sound track id this sound belongs to.
     */
    var soundTrackId: Number
    /**
     * Is this sound currently played.
     */
    var isPlaying: Any
    /**
     * Is this sound currently paused.
     */
    var isPaused: Any
    /**
     * Does this sound enables spatial sound.
     * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music#creating-a-spatial-3d-sound]
     */
    var spatialSound: Any
    /**
     * Define the reference distance the sound should be heard perfectly.
     * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music#creating-a-spatial-3d-sound]
     */
    var refDistance: Number
    /**
     * Define the roll off factor of spatial sounds.
     * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music#creating-a-spatial-3d-sound]
     */
    var rolloffFactor: Number
    /**
     * Define the max distance the sound should be heard (intensity just became 0 at this point).
     * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music#creating-a-spatial-3d-sound]
     */
    var maxDistance: Number
    /**
     * Define the distance attenuation model the sound will follow.
     * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music#creating-a-spatial-3d-sound]
     */
    var distanceModel: String
    /**
     * @hidden
     * Back Compat
     **/
    var onended: () -> Any
    /**
     * Observable event when the current playing sound finishes.
     */
    var onEndedObservable: Observable<Sound>
    
    /**
     * Create a sound and attach it to a scene
     * @param name Name of your sound
     * @param urlOrArrayBuffer Url to the sound to load async or ArrayBuffer, it also works with MediaStreams
     * @param scene defines the scene the sound belongs to
     * @param readyToPlayCallback Provide a callback function if you'd like to load your code once the sound is ready to be played
     * @param options Objects to provide with the current available options: autoplay, loop, volume, spatialSound, maxDistance, rolloffFactor, refDistance, distanceModel, panningModel, streaming
     */
    constructor(name: String, urlOrArrayBuffer: Any, scene: Scene, readyToPlayCallback: (() -> Unit)?, options: Any?)
    /**
     * Release the sound and its associated resources
     */
    fun dispose()
    /**
     * Gets if the sounds is ready to be played or not.
     * @returns true if ready, otherwise false
     */
    fun isReady(): Any
    /**
     * Sets the data of the sound from an audiobuffer
     * @param audioBuffer The audioBuffer containing the data
     */
    fun setAudioBuffer(audioBuffer: AudioBuffer)
    /**
     * Updates the current sounds options such as maxdistance, loop...
     * @param options A JSON object containing values named as the object properties
     */
    fun updateOptions(options: Any)
    /**
     * Switch the panning model to HRTF:
     * Renders a stereo output of higher quality than equalpower — it uses a convolution with measured impulse responses from human subjects.
     * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music#creating-a-spatial-3d-sound]
     */
    fun switchPanningModelToHRTF()
    /**
     * Switch the panning model to Equal Power:
     * Represents the equal-power panning algorithm, generally regarded as simple and efficient. equalpower is the default value.
     * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music#creating-a-spatial-3d-sound]
     */
    fun switchPanningModelToEqualPower()
    /**
     * Connect this sound to a sound track audio node like gain...
     * @param soundTrackAudioNode the sound track audio node to connect to
     */
    fun connectToSoundTrackAudioNode(soundTrackAudioNode: AudioNode)
    /**
     * Transform this sound into a directional source
     * @param coneInnerAngle Size of the inner cone in degree
     * @param coneOuterAngle Size of the outer cone in degree
     * @param coneOuterGain Volume of the sound outside the outer cone (between 0.0 and 1.0)
     */
    fun setDirectionalCone(coneInnerAngle: Number, coneOuterAngle: Number, coneOuterGain: Number)
    /**
     * Gets or sets the inner angle for the directional cone.
     */
    /**
     * Gets or sets the inner angle for the directional cone.
     */
    var directionalConeInnerAngle: Number
    /**
     * Gets or sets the outer angle for the directional cone.
     */
    /**
     * Gets or sets the outer angle for the directional cone.
     */
    var directionalConeOuterAngle: Number
    /**
     * Sets the position of the emitter if spatial sound is enabled
     * @param newPosition Defines the new posisiton
     */
    fun setPosition(newPosition: Vector3)
    /**
     * Sets the local direction of the emitter if spatial sound is enabled
     * @param newLocalDirection Defines the new local direction
     */
    fun setLocalDirectionToMesh(newLocalDirection: Vector3)
    /**
     * Sets a new custom attenuation function for the sound.
     * @param callback Defines the function used for the attenuation
     * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music#creating-your-own-custom-attenuation-function]
     */
    fun setAttenuationFunction(callback: (currentVolume: Number, currentDistance: Number, maxDistance: Number, refDistance: Number, rolloffFactor: Number) -> Number)
    /**
     * Play the sound
     * @param time (optional) Start the sound after X seconds. Start immediately (0) by default.
     * @param offset (optional) Start the sound setting it at a specific time
     */
    fun play(time: Number?, offset: Number?)
    /**
     * Stop the sound
     * @param time (optional) Stop the sound after X seconds. Stop immediately (0) by default.
     */
    fun stop(time: Number?)
    /**
     * Put the sound in pause
     */
    fun pause()
    /**
     * Sets a dedicated volume for this sounds
     * @param newVolume Define the new volume of the sound
     * @param time Define in how long the sound should be at this value
     */
    fun setVolume(newVolume: Number, time: Number?)
    /**
     * Set the sound play back rate
     * @param newPlaybackRate Define the playback rate the sound should be played at
     */
    fun setPlaybackRate(newPlaybackRate: Number)
    /**
     * Gets the volume of the sound.
     * @returns the volume of the sound
     */
    fun getVolume(): Number
    /**
     * Attach the sound to a dedicated mesh
     * @param transformNode The transform node to connect the sound with
     * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music#attaching-a-sound-to-a-mesh]
     */
    fun attachToMesh(transformNode: TransformNode)
    /**
     * Detach the sound from the previously attached mesh
     * @see [http://doc.babylonjs.com/how_to/playing_sounds_and_music#attaching-a-sound-to-a-mesh]
     */
    fun detachFromMesh()
    /**
     * Clone the current sound in the scene.
     * @returns the new sound clone
     */
    fun clone(): Sound?
    /**
     * Gets the current underlying audio buffer containing the data
     * @returns the audio buffer
     */
    fun getAudioBuffer(): AudioBuffer?
    /**
     * Serializes the Sound in a JSON representation
     * @returns the JSON representation of the sound
     */
    fun serialize(): Any
    
    companion object {
        /**
         * Parse a JSON representation of a sound to innstantiate in a given scene
         * @param parsedSound Define the JSON representation of the sound (usually coming from the serialize method)
         * @param scene Define the scene the new parsed sound should be created in
         * @param rootUrl Define the rooturl of the load in case we need to fetch relative dependencies
         * @param sourceSound Define a cound place holder if do not need to instantiate a new one
         * @returns the newly parsed sound
         */
        fun Parse(parsedSound: Any, scene: Scene, rootUrl: String, sourceSound: Sound?): Sound
    }
}
