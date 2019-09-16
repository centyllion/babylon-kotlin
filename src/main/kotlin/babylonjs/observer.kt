@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals")
package babylonjs

import kotlin.js.Promise

/**
 * A class serves as a medium between the observable and its observers
 */
external class EventState {
    /**
     * Create a new EventState
     * @param mask defines the mask associated with this state
     * @param skipNextObservers defines a flag which will instruct the observable to skip following observers when set to true
     * @param target defines the original target of the state
     * @param currentTarget defines the current target of the state
     */
    constructor(mask: Number, skipNextObservers: Boolean?, target: Any?, currentTarget: Any?)
    /**
     * Initialize the current event state
     * @param mask defines the mask associated with this state
     * @param skipNextObservers defines a flag which will instruct the observable to skip following observers when set to true
     * @param target defines the original target of the state
     * @param currentTarget defines the current target of the state
     * @returns the current event state
     */
    fun initalize(mask: Number, skipNextObservers: Boolean?, target: Any?, currentTarget: Any?): EventState
    /**
     * An Observer can set this property to true to prevent subsequent observers of being notified
     */
    var skipNextObservers: Boolean
    /**
     * Get the mask value that were used to trigger the event corresponding to this EventState object
     */
    val mask: Number
    /**
     * The object that originally notified the event
     */
    val target: Any?
    /**
     * The current object in the bubbling phase
     */
    val currentTarget: Any?
    /**
     * This will be populated with the return value of the last function that was executed.
     * If it is the first function in the callback chain it will be the event data.
     */
    val lastReturnValue: Any?
}
/**
 * Represent an Observer registered to a given Observable object.
 */
external class Observer<T> {
    /**
     * Defines the callback to call when the observer is notified
     */
    var callback: (eventData: T, eventState: EventState) -> Unit
    /**
     * Defines the mask of the observer (used to filter notifications)
     */
    var mask: Number
    /**
     * Defines the current scope used to restore the JS context
     */
    var scope: Any
    /**
     * Gets or sets a property defining that the observer as to be unregistered after the next notification
     */
    var unregisterOnNextCall: Boolean
    /**
     * Creates a new observer
     * @param callback defines the callback to call when the observer is notified
     * @param mask defines the mask of the observer (used to filter notifications)
     * @param scope defines the current scope used to restore the JS context
     */
    constructor(
        /**
         * Defines the callback to call when the observer is notified
         */
        callback: (eventData: T, eventState: EventState) -> Unit,
        /**
         * Defines the mask of the observer (used to filter notifications)
         */
        mask: Number,
        /**
         * Defines the current scope used to restore the JS context
         */
        scope: Any?)
}
/**
 * Represent a list of observers registered to multiple Observables object.
 */
external class MultiObserver<T> {
    /**
     * Release associated resources
     */
    fun dispose()
    
    companion object {
        /**
         * Raise a callback when one of the observable will notify
         * @param observables defines a list of observables to watch
         * @param callback defines the callback to call on notification
         * @param mask defines the mask used to filter notifications
         * @param scope defines the current scope used to restore the JS context
         * @returns the new MultiObserver
         */
        fun<T> Watch(observables: Array<Observable<T>>, callback: (eventData: T, eventState: EventState) -> Unit, mask: Number?, scope: Any?): MultiObserver<T>
    }
}
/**
 * The Observable class is a simple implementation of the Observable pattern.
 *
 * There's one slight particularity though: a given Observable can notify its observer using a particular mask value, only the Observers registered with this mask value will be notified.
 * This enable a more fine grained execution without having to rely on multiple different Observable objects.
 * For instance you may have a given Observable that have four different types of notifications: Move (mask = 0x01), Stop (mask = 0x02), Turn Right (mask = 0X04), Turn Left (mask = 0X08).
 * A given observer can register itself with only Move and Stop (mask = 0x03), then it will only be notified when one of these two occurs and will never be for Turn Left/Right.
 */
external class Observable<T> {
    /**
     * Creates a new observable
     * @param onObserverAdded defines a callback to call when a new observer is added
     */
    constructor(onObserverAdded: ((observer: Observer<T>) -> Unit)?)
    /**
     * Create a new Observer with the specified callback
     * @param callback the callback that will be executed for that Observer
     * @param mask the mask used to filter observers
     * @param insertFirst if true the callback will be inserted at the first position, hence executed before the others ones. If false (default behavior) the callback will be inserted at the last position, executed after all the others already present.
     * @param scope optional scope for the callback to be called from
     * @param unregisterOnFirstCall defines if the observer as to be unregistered after the next notification
     * @returns the new observer created for the callback
     */
    fun add(callback: (eventData: T, eventState: EventState) -> Unit, mask: Number? = definedExternally, insertFirst: Boolean? = definedExternally, scope: Any? = definedExternally, unregisterOnFirstCall: Boolean? = definedExternally): Observer<T>?
    /**
     * Create a new Observer with the specified callback and unregisters after the next notification
     * @param callback the callback that will be executed for that Observer
     * @returns the new observer created for the callback
     */
    fun addOnce(callback: (eventData: T, eventState: EventState) -> Unit): Observer<T>?
    /**
     * Remove an Observer from the Observable object
     * @param observer the instance of the Observer to remove
     * @returns false if it doesn't belong to this Observable
     */
    fun remove(observer: Observer<T>?): Boolean
    /**
     * Remove a callback from the Observable object
     * @param callback the callback to remove
     * @param scope optional scope. If used only the callbacks with this scope will be removed
     * @returns false if it doesn't belong to this Observable
     */
    fun removeCallback(callback: (eventData: T, eventState: EventState) -> Unit, scope: Any?): Boolean
    /**
     * Moves the observable to the top of the observer list making it get called first when notified
     * @param observer the observer to move
     */
    fun makeObserverTopPriority(observer: Observer<T>)
    /**
     * Moves the observable to the bottom of the observer list making it get called last when notified
     * @param observer the observer to move
     */
    fun makeObserverBottomPriority(observer: Observer<T>)
    /**
     * Notify all Observers by calling their respective callback with the given data
     * Will return true if all observers were executed, false if an observer set skipNextObservers to true, then prevent the subsequent ones to execute
     * @param eventData defines the data to send to all observers
     * @param mask defines the mask of the current notification (observers with incompatible mask (ie mask & observer.mask === 0) will not be notified)
     * @param target defines the original target of the state
     * @param currentTarget defines the current target of the state
     * @returns false if the complete observer chain was not processed (because one observer set the skipNextObservers to true)
     */
    fun notifyObservers(eventData: T, mask: Number?, target: Any?, currentTarget: Any?): Boolean
    /**
     * Calling this will execute each callback, expecting it to be a promise or return a value.
     * If at Any point in the chain one function fails, the promise will fail and the execution will not continue.
     * This is useful when a chain of events (sometimes async events) is needed to initialize a certain object
     * and it is crucial that all callbacks will be executed.
     * The order of the callbacks is kept, callbacks are not executed parallel.
     *
     * @param eventData The data to be sent to each callback
     * @param mask is used to filter observers defaults to -1
     * @param target defines the callback target (see EventState)
     * @param currentTarget defines he current object in the bubbling phase
     * @returns {Promise<T>} will return a Promise than resolves when all callbacks executed successfully.
     */
    fun notifyObserversWithPromise(eventData: T, mask: Number?, target: Any?, currentTarget: Any?): Promise<T>
    /**
     * Notify a specific observer
     * @param observer defines the observer to notify
     * @param eventData defines the data to be sent to each callback
     * @param mask is used to filter observers defaults to -1
     */
    fun notifyObserver(observer: Observer<T>, eventData: T, mask: Number?)
    /**
     * Gets a Boolean indicating if the observable has at least one observer
     * @returns true is the Observable has at least one Observer registered
     */
    fun hasObservers(): Boolean
    /**
     * Clear the list of observers
     */
    fun clear()
    /**
     * Clone the current observable
     * @returns a new observable
     */
    fun clone(): Observable<T>
    /**
     * Does this observable handles observer registered with a given mask
     * @param mask defines the mask to be tested
     * @return whether or not one observer registered with the given mask is handeled
     **/
    fun hasSpecificMask(mask: Number?): Boolean
}
