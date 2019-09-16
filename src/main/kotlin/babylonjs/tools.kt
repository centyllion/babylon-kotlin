@file:JsQualifier("BABYLON")
@file:Suppress("unused", "ConvertSecondaryConstructorToPrimary", "CovariantEquals", "FunctionName")
package babylonjs

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLImageElement
import org.w3c.files.Blob
import org.w3c.files.File
import org.w3c.xhr.ProgressEvent
import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Promise

/**
 * Interface for any object that can request an animation frame
 */
external interface ICustomAnimationFrameRequester {
    /**
     * This function will be called when the render loop is ready. If this is not populated, the engine's renderloop function will be called
     */
    var renderFunction: (() -> Unit)?
    /**
     * Called to request the next frame to render to
     * @see [https://developer.mozilla.org/en-US/docs/Web/API/window/requestAnimationFrame]
     */
    var requestAnimationFrame: (() -> Unit)?
    /**
     * You can pass this value to cancelAnimationFrame() to cancel the refresh callback request
     * @see [https://developer.mozilla.org/en-US/docs/Web/API/window/requestAnimationFrame#Return_value]
     */
    var requestID: Number?
}

/** Interface used by value gradients (color, factor, ...) */
external interface IValueGradient {
    /**
     * Gets or sets the gradient value (between 0 and 1)
     */
    var gradient: Number
}

/** Class used to store color4 gradient */
external class ColorGradient: IValueGradient {
    /**
     * Gets or sets the gradient value (between 0 and 1)
     */
    override var gradient: Number
    /**
     * Gets or sets first associated color
     */
    var color1: Color4
    /**
     * Gets or sets second associated color
     */
    var color2: Color4?
    /**
     * Will get a color picked randomly between color1 and color2.
     * If color2 is undefined then color1 will be used
     * @param result defines the target Color4 to store the result in
     */
    fun getColorToRef(result: Color4)
}

/** Class used to store color 3 gradient */
external class Color3Gradient: IValueGradient {
    /**
     * Gets or sets the gradient value (between 0 and 1)
     */
    override var gradient: Number
    /**
     * Gets or sets the associated color
     */
    var color: Color3
}
/** Class used to store factor gradient */
external class FactorGradient: IValueGradient {
    /**
     * Gets or sets the gradient value (between 0 and 1)
     */
    override var gradient: Number
    /**
     * Gets or sets first associated factor
     */
    var factor1: Number
    /**
     * Gets or sets second associated factor
     */
    var factor2: Number
    /**
     * Will get a number picked randomly between factor1 and factor2.
     * If factor2 is undefined then factor1 will be used
     * @returns the picked number
     */
    fun getFactor(): Number
}

/**
 * Extended version of XMLHttpRequest with support for customizations (headers, ...)
 */
external class WebRequest: XMLHttpRequest {
    companion object {
        /**
         * Custom HTTP Request Headers to be sent with XMLHttpRequests
         * i.e. when loading files, where the server/service expects an Authorization header
         */
        var CustomRequestHeaders: MutableMap<String, String>
        /**
         * Add callback functions in this array to update all the requests before they get sent to the network
         */
        var CustomRequestModifiers: Array<(request: XMLHttpRequest) -> Unit>
    }
}

/**
 * File request interface
 */
external interface IFileRequest {
    /**
     * Raised when the request is complete (success or error).
     */
    val onCompleteObservable: Observable<IFileRequest>
    /**
     * Aborts the request for a file.
     */
    val abort: () -> Unit
}

/**
 * Class containing a set of static utilities functions
 */
external class Tools {
    companion object {
        /**
         * Gets or sets the base URL to use to load assets
         */
        var BaseUrl: String
        /**
         * Enable/Disable Custom HTTP Request Headers globally.
         * default = false
         */
        var UseCustomRequestHeaders: Boolean
        /**
         * Custom HTTP Request Headers to be sent with XMLHttpRequests
         * i.e. when loading files, where the server/service expects an Authorization header
         */
        // TODO CustomRequestHeaders
        //static CustomRequestHeaders: { [key: String]: String; };
        /**
         * Gets or sets the retry strategy to apply when an error happens while loading an asset
         */
        var DefaultRetryStrategy: (url: String, request: WebRequest, retryIndex: Number) -> Number
        /**
         * Default behaviour for cors in the application.
         * It can be a String if the expected behavior is identical in the entire app.
         * Or a callback to be able to set it per url or on a group of them (in case of Video source for instance)
         */
        var CorsBehavior: String /*| ((url: String | String[]) -> String) */
        /**
         * Gets or sets a global variable indicating if fallback texture must be used when a texture cannot be loaded
         * @ignorenaming
         */
        var UseFallbackTexture: Boolean
        /**
         * Use this object to register external classes like custom textures or material
         * to allow the laoders to instantiate them
         */
        // TODO RegisteredExternalClasses
        //static RegisteredExternalClasses: { [key: String]: Object; }
        /**
         * Texture content used if a texture cannot loaded
         * @ignorenaming
         */
        var fallbackTexture: String
        /**
         * Read the content of a byte array at a specified coordinates (taking in account wrapping)
         * @param u defines the coordinate on X axis
         * @param v defines the coordinate on Y axis
         * @param width defines the width of the source data
         * @param height defines the height of the source data
         * @param pixels defines the source byte array
         * @param color defines the output color
         */
        fun FetchToRef(u: Number, v: Number, width: Number, height: Number, pixels: Uint8Array, color: Color4)
        /**
         * Interpolates between a and b via alpha
         * @param a The lower value (returned when alpha = 0)
         * @param b The upper value (returned when alpha = 1)
         * @param alpha The interpolation-factor
         * @return The mixed value
         */
        fun Mix(a: Number, b: Number, alpha: Number): Number
        /**
         * Tries to instantiate a new object from a given class name
         * @param className defines the class name to instantiate
         * @returns the new object or null if the system was not able to do the instantiation
         */
        fun Instantiate(className: String): Any
        /**
         * Provides a slice function that will work even on IE
         * @param data defines the array to slice
         * @param start defines the start of the data (optional)
         * @param end defines the end of the data (optional)
         * @returns the new sliced array
         */
        fun <T>Slice(data : T, start: Number?, end: Number?): T
        /**
         * Polyfill for setImmediate
         * @param action defines the action to execute after the current execution block
         */
        fun SetImmediate(action: () -> Unit)
        /**
         * Function indicating if a Number is an exponent of 2
         * @param value defines the value to test
         * @returns true if the value is an exponent of 2
         */
        fun IsExponentOfTwo(value: Number): Boolean
        /**
         * Returns the nearest 32-bit single precision float representation of a Number
         * @param value A Number.  If the parameter is of a different type, it will get converted
         * to a Number or to NaN if it cannot be converted
         * @returns Number
         */
        fun FloatRound(value: Number): Number
        /**
         * Find the next highest power of two.
         * @param x Number to start search from.
         * @return Next highest power of two.
         */
        fun CeilingPOT(x: Number): Number
        /**
         * Find the next lowest power of two.
         * @param x Number to start search from.
         * @return Next lowest power of two.
         */
        fun FloorPOT(x: Number): Number
        /**
         * Find the nearest power of two.
         * @param x Number to start search from.
         * @return Next nearest power of two.
         */
        fun NearestPOT(x: Number): Number
        /**
         * Get the closest exponent of two
         * @param value defines the value to approximate
         * @param max defines the maximum value to return
         * @param mode defines how to define the closest value
         * @returns closest exponent of two of the given value
         */
        fun GetExponentOfTwo(value: Number, max: Number, mode: Number?): Number
        /**
         * Extracts the filename from a path
         * @param path defines the path to use
         * @returns the filename
         */
        fun GetFilename(path: String): String
        /**
         * Extracts the "folder" part of a path (everything before the filename).
         * @param uri The URI to extract the info from
         * @param returnUnchangedIfNoSlash Do not touch the URI if no slashes are present
         * @returns The "folder" part of the path
         */
        fun GetFolderPath(uri: String, returnUnchangedIfNoSlash: Boolean?): String
        /**
         * Extracts text content from a DOM element hierarchy
         * Back Compat only, please use DomManagement.GetDOMTextContent instead.
         */
        // TODO GetDOMTextContent
        //var GetDOMTextContent: typeof DomManagement.GetDOMTextContent
        /**
         * Convert an angle in radians to degrees
         * @param angle defines the angle to convert
         * @returns the angle in degrees
         */
        fun ToDegrees(angle: Number): Number
        /**
         * Convert an angle in degrees to radians
         * @param angle defines the angle to convert
         * @returns the angle in radians
         */
        fun ToRadians(angle: Number): Number
        /**
         * Encode a buffer to a base64 String
         * @param buffer defines the buffer to encode
         * @returns the encoded String
         */
        fun EncodeArrayBufferTobase64(buffer: ArrayBuffer): String
        /**
         * Extracts minimum and maximum values from a list of indexed positions
         * @param positions defines the positions to use
         * @param indices defines the indices to the positions
         * @param indexStart defines the start index
         * @param indexCount defines the end index
         * @param bias defines bias value to add to the result
         * @return minimum and maximum values
         */
        fun ExtractMinAndMaxIndexed(positions: FloatArray, indices: IndicesArray, indexStart: Number, indexCount: Number, bias: Vector2?): MinMax
        /**
         * Extracts minimum and maximum values from a list of positions
         * @param positions defines the positions to use
         * @param start defines the start index in the positions array
         * @param count defines the Number of positions to handle
         * @param bias defines bias value to add to the result
         * @param stride defines the stride size to use (distance between two positions in the positions array)
         * @return minimum and maximum values
         */
        fun ExtractMinAndMax(positions: FloatArray, start: Number, count: Number, bias: Vector2?, stride: Number?): MinMax
        /**
         * Returns an array if obj is not an array
         * @param obj defines the object to evaluate as an array
         * @param allowsNullUndefined defines a Boolean indicating if obj is allowed to be null or undefined
         * @returns either obj directly if obj is an array or a new array containing obj
         */
        fun MakeArray(obj: Any, allowsNullUndefined: Boolean?): Array<Any>?
        /**
         * Gets the pointer prefix to use
         * @returns "pointer" if touch is enabled. Else returns "mouse"
         */
        fun GetPointerPrefix(): String
        /**
         * Queue a new function into the requested animation frame pool (ie. this function will be executed byt the browser for the next frame)
         * @param func - the function to be called
         * @param requester - the object that will request the next frame. Falls back to window.
         * @returns frame Number
         */
        fun QueueNewFrame(func: () -> Unit, requester: Any?): Number
        /**
         * Ask the browser to promote the current element to fullscreen rendering mode
         * @param element defines the DOM element to promote
         */
        fun RequestFullscreen(element: HTMLElement)
        /**
         * Asks the browser to exit fullscreen mode
         */
        fun ExitFullscreen()
        /**
         * Sets the cors behavior on a dom element. This will add the required Tools.CorsBehavior to the element.
         * @param url define the url we are trying
         * @param element define the dom element where to configure the cors policy
         */
        fun SetCorsBehavior(url: /*String | */Array<String>, element: Any? = definedExternally/*{ crossOrigin: String | null; }*/)
        /**
         * Removes unwanted characters from an url
         * @param url defines the url to clean
         * @returns the cleaned url
         */
        fun CleanUrl(url: String): String
        /**
         * Gets or sets a function used to pre-process url before using them to load assets
         */
        var PreprocessUrl: (url: String) -> String
        /**
         * Loads an image as an HTMLImageElement.
         * @param input url String, ArrayBuffer, or Blob to load
         * @param onLoad callback called when the image successfully loads
         * @param onError callback called when the image fails to load
         * @param offlineProvider offline provider for caching
         * @returns the HTMLImageElement of the loaded image
         */
        fun LoadImage(input: String , onLoad: (img: HTMLImageElement) -> Unit, onError: (message: String?, exception: Any?) -> Unit, offlineProvider: IOfflineProvider?): HTMLImageElement
        /**
         * Loads an image as an HTMLImageElement.
         * @param input url String, ArrayBuffer, or Blob to load
         * @param onLoad callback called when the image successfully loads
         * @param onError callback called when the image fails to load
         * @param offlineProvider offline provider for caching
         * @returns the HTMLImageElement of the loaded image
         */
        fun LoadImage(input: ArrayBuffer, onLoad: (img: HTMLImageElement) -> Unit, onError: (message: String?, exception: Any?) -> Unit, offlineProvider: IOfflineProvider?): HTMLImageElement
        /**
         * Loads an image as an HTMLImageElement.
         * @param input url String, ArrayBuffer, or Blob to load
         * @param onLoad callback called when the image successfully loads
         * @param onError callback called when the image fails to load
         * @param offlineProvider offline provider for caching
         * @returns the HTMLImageElement of the loaded image
         */
        fun LoadImage(input: Blob, onLoad: (img: HTMLImageElement) -> Unit, onError: (message: String?, exception: Any?) -> Unit, offlineProvider: IOfflineProvider?): HTMLImageElement
        /**
         * Loads a file
         * @param url url String, ArrayBuffer, or Blob to load
         * @param onSuccess callback called when the file successfully loads
         * @param onProgress callback called while file is loading (if the server supports this mode)
         * @param offlineProvider defines the offline provider for caching
         * @param useArrayBuffer defines a Boolean indicating that date must be returned as ArrayBuffer
         * @param onError callback called when the file fails to load
         * @returns a file request object
         */
        fun LoadFile(url: String, onSuccess: (data : String, responseURL: String?) -> Unit, onProgress: ((data : Any) -> Unit)?, offlineProvider: IOfflineProvider?, useArrayBuffer: Boolean?, onError: ((request: WebRequest?, exception: Any?) -> Unit)?): IFileRequest
        /**
         * Loads a file
         * @param url url String, ArrayBuffer, or Blob to load
         * @param onSuccess callback called when the file successfully loads
         * @param onProgress callback called while file is loading (if the server supports this mode)
         * @param offlineProvider defines the offline provider for caching
         * @param useArrayBuffer defines a Boolean indicating that date must be returned as ArrayBuffer
         * @param onError callback called when the file fails to load
         * @returns a file request object
         */
        fun LoadFile(url: String, onSuccess: (data : ArrayBuffer, responseURL: String?) -> Unit, onProgress: ((data : Any) -> Unit)?, offlineProvider: IOfflineProvider?, useArrayBuffer: Boolean?, onError: ((request: WebRequest?, exception: Any?) -> Unit)?): IFileRequest
        /**
         * Load a script (identified by an url). When the url returns, the
         * content of this file is added into a new script element, attached to the DOM (body element)
         * @param scriptUrl defines the url of the script to laod
         * @param onSuccess defines the callback called when the script is loaded
         * @param onError defines the callback to call if an error occurs
         * @param scriptId defines the id of the script element
         */
        fun LoadScript(scriptUrl: String, onSuccess: () -> Unit, onError: ((message: String?, exception: Any?) -> Unit)?, scriptId: String?)
        /**
         * Load an asynchronous script (identified by an url). When the url returns, the
         * content of this file is added into a new script element, attached to the DOM (body element)
         * @param scriptUrl defines the url of the script to laod
         * @param scriptId defines the id of the script element
         * @returns a promise request object
         */
        fun LoadScriptAsync(scriptUrl: String, scriptId: String?): Promise<Boolean>?
        /**
         * Loads a file from a blob
         * @param fileToLoad defines the blob to use
         * @param callback defines the callback to call when data is loaded
         * @param progressCallback defines the callback to call during loading process
         * @returns a file request object
         */
        fun ReadFileAsDataURL(fileToLoad: Blob, callback: (data : Any) -> Unit, progressCallback: (ev: ProgressEvent) -> Any): IFileRequest
        /**
         * Loads a file
         * @param fileToLoad defines the file to load
         * @param callback defines the callback to call when data is loaded
         * @param progressCallBack defines the callback to call during loading process
         * @param useArrayBuffer defines a Boolean indicating that data must be returned as an ArrayBuffer
         * @returns a file request object
         */
        fun ReadFile(fileToLoad: File, callback: (data : Any) -> Unit, progressCallBack: ((ev: ProgressEvent) -> Any)?, useArrayBuffer: Boolean?): IFileRequest
        /**
         * Creates a data url from a given String content
         * @param content defines the content to convert
         * @returns the new data url link
         */
        fun FileAsURL(content: String): String
        /**
         * Format the given Number to a specific decimal format
         * @param value defines the Number to format
         * @param decimals defines the Number of decimals to use
         * @returns the formatted String
         */
        fun Format(value: Number, decimals: Number?): String
        /**
         * Checks if a given vector is inside a specific range
         * @param v defines the vector to test
         * @param min defines the minimum range
         * @param max defines the maximum range
         */
        fun CheckExtends(v: Vector3, min: Vector3, max: Vector3)
        /**
         * Tries to copy an object by duplicating every property
         * @param source defines the source object
         * @param destination defines the target object
         * @param doNotCopyList defines a list of properties to avoid
         * @param mustCopyList defines a list of properties to copy (even if they start with _)
         */
        fun DeepCopy(source: Any, destination: Any, doNotCopyList: Array<String>?, mustCopyList: Array<String>?)
        /**
         * Gets a Boolean indicating if the given object has no own property
         * @param obj defines the object to test
         * @returns true if object has no own property
         */
        fun IsEmpty(obj: Any): Boolean
        /**
         * Checks for a matching suffix at the end of a String (for ES5 and lower)
         * @param str Source String
         * @param suffix Suffix to search for in the source String
         * @returns Boolean indicating whether the suffix was found (true) or not (false)
         */
        fun EndsWith(str: String, suffix: String): Boolean
        /**
         * Function used to register events at window level
         * @param events defines the events to register
         */
        fun RegisterTopRootEvents(events: Array<Any> /*{ name: String; handler: Nullable<(e: FocusEvent) -> Any>; }*/)
        /**
         * Function used to unregister events from window level
         * @param events defines the events to unregister
         */
        fun UnregisterTopRootEvents(events: Array<Any> /*{ name: String; handler: Nullable<(e: FocusEvent) -> Any>; }*/)
        /**
         * Dumps the current bound framebuffer
         * @param width defines the rendering width
         * @param height defines the rendering height
         * @param engine defines the hosting engine
         * @param successCallback defines the callback triggered once the data are available
         * @param mimeType defines the mime type of the result
         * @param fileName defines the filename to download. If present, the result will automatically be downloaded
         */
        fun DumpFramebuffer(width: Number, height: Number, engine: Engine, successCallback: ((data : String) -> Unit)?, mimeType: String?, fileName: String?)
        /**
         * Converts the canvas data to blob.
         * This acts as a polyfill for browsers not supporting the to blob function.
         * @param canvas Defines the canvas to extract the data from
         * @param successCallback Defines the callback triggered once the data are available
         * @param mimeType Defines the mime type of the result
         */
        fun ToBlob(canvas: HTMLCanvasElement, successCallback: (blob: Blob?) -> Unit, mimeType: String? = definedExternally)
        /**
         * Encodes the canvas data to base 64 or automatically download the result if filename is defined
         * @param successCallback defines the callback triggered once the data are available
         * @param mimeType defines the mime type of the result
         * @param fileName defines he filename to download. If present, the result will automatically be downloaded
         */
        fun EncodeScreenshotCanvasData(successCallback: ((data : String) -> Unit)?, mimeType: String?, fileName: String?)
        /**
         * Downloads a blob in the browser
         * @param blob defines the blob to download
         * @param fileName defines the name of the downloaded file
         */
        fun Download(blob: Blob, fileName: String)
        /**
         * Captures a screenshot of the current rendering
         * @see [http://doc.babylonjs.com/how_to/render_scene_on_a_png]
         * @param engine defines the rendering engine
         * @param camera defines the source camera
         * @param size This parameter can be set to a single Number or to an object with the
         * following (optional) properties: precision, width, height. If a single Number is passed,
         * it will be used for both width and height. If an object is passed, the screenshot size
         * will be derived from the parameters. The precision property is a multiplier allowing
         * rendering at a higher or lower resolution
         * @param successCallback defines the callback receives a single parameter which contains the
         * screenshot as a String of base64-encoded characters. This String can be assigned to the
         * src parameter of an <img> to display it
         * @param mimeType defines the MIME type of the screenshot image (default: image/png).
         * Check your browser for supported MIME types
         */
        fun CreateScreenshot(engine: Engine, camera: Camera, size: Any, successCallback: ((data : String) -> Unit)?, mimeType: String? = definedExternally)
        /**
         * Generates an image screenshot from the specified camera.
         * @see [http://doc.babylonjs.com/how_to/render_scene_on_a_png]
         * @param engine The engine to use for rendering
         * @param camera The camera to use for rendering
         * @param size This parameter can be set to a single Number or to an object with the
         * following (optional) properties: precision, width, height. If a single Number is passed,
         * it will be used for both width and height. If an object is passed, the screenshot size
         * will be derived from the parameters. The precision property is a multiplier allowing
         * rendering at a higher or lower resolution
         * @param successCallback The callback receives a single parameter which contains the
         * screenshot as a String of base64-encoded characters. This String can be assigned to the
         * src parameter of an <img> to display it
         * @param mimeType The MIME type of the screenshot image (default: image/png).
         * Check your browser for supported MIME types
         * @param samples Texture samples (default: 1)
         * @param antialiasing Whether antialiasing should be turned on or not (default: false)
         * @param fileName A name for for the downloaded file.
         */
        fun CreateScreenshotUsingRenderTarget(engine: Engine, camera: Camera, size: Any, successCallback: ((data : String) -> Unit)?, mimeType: String?, samples: Number?, antialiasing: Boolean?, fileName: String?)
        /**
         * Implementation from http://stackoverflow.com/questions/105034/how-to-create-a-guid-uuid-in-javascript/2117523#answer-2117523
         * Be aware Math.random() could cause collisions, but:
         * "All but 6 of the 128 bits of the ID are randomly generated, which means that for Any two ids, there's a 1 in 2^^122 (or 5.3x10^^36) chance they'll collide"
         * @returns a pseudo random id
         */
        fun RandomId(): String
        /**
         * Test if the given uri is a base64 String
         * @param uri The uri to test
         * @return True if the uri is a base64 String or false otherwise
         */
        fun IsBase64(uri: String): Boolean
        /**
         * Decode the given base64 uri.
         * @param uri The uri to decode
         * @return The decoded base64 data.
         */
        fun DecodeBase64(uri: String): ArrayBuffer
        /**
         * Gets the absolute url.
         * @param url the input url
         * @return the absolute url
         */
        fun GetAbsoluteUrl(url: String): String
        /**
         * No log
         */
        val NoneLogLevel: Number
        /**
         * Only message logs
         */
        val MessageLogLevel: Number
        /**
         * Only warning logs
         */
        val WarningLogLevel: Number
        /**
         * Only error logs
         */
        val ErrorLogLevel: Number
        /**
         * All logs
         */
        val AllLogLevel: Number
        /**
         * Gets a value indicating the Number of loading errors
         * @ignorenaming
         */
        val errorsCount: Number
        /**
         * Callback called when a new log is added
         */
        var OnNewCacheEntry: (entry: String) -> Unit
        /**
         * Log a message to the console
         * @param message defines the message to log
         */
        fun Log(message: String)
        /**
         * Write a warning message to the console
         * @param message defines the message to log
         */
        fun Warn(message: String)
        /**
         * Write an error message to the console
         * @param message defines the message to log
         */
        fun Error(message: String)
        /**
         * Gets current log cache (list of logs)
         */
        val LogCache: String
        /**
         * Clears the log cache
         */
        fun ClearLogCache()
        /**
         * Sets the current log level (MessageLogLevel / WarningLogLevel / ErrorLogLevel)
         */
        var LogLevels: Number
        /**
         * Checks if the loaded document was accessed via `file:`-Protocol.
         * @returns Boolean
         */
        fun IsFileURL(): Boolean
        /**
         * Checks if the window object exists
         * Back Compat only, please use DomManagement.IsWindowObjectExist instead.
         */
        // TODO IsWindowObjectExist
        //var IsWindowObjectExist: typeof DomManagement.IsWindowObjectExist
        /**
         * No performance log
         */
        val PerformanceNoneLogLevel: Number
        /**
         * Use user marks to log performance
         */
        val PerformanceUserMarkLogLevel: Number
        /**
         * Log performance to the console
         */
        val PerformanceConsoleLogLevel: Number
        /**
         * Sets the current performance log level
         */
        var PerformanceLogLevel: Number
        /**
         * Starts a performance counter
         */
        var StartPerformanceCounter: (counterName: String, condition: Boolean?) -> Unit
        /**
         * Ends a specific performance coutner
         */
        var EndPerformanceCounter: (counterName: String, condition: Boolean?) -> Unit
        /**
         * Gets either window.performance.now() if supported or Date.now() else
         */
        val Now: Number
        /**
         * This method will return the name of the class used to create the instance of the given object.
         * It will works only on Javascript basic data types (Number, String, ...) and instance of class declared with the @className decorator.
         * @param source the object to get the class name from
         * @param isType defines if the object is actually a type
         * @returns the name of the class, will be "object" for a custom data type not using the @className decorator
         */
        fun GetClassName(source : Any, isType: Boolean?): String
        /**
         * Gets the first element of an array satisfying a given predicate
         * @param array defines the array to browse
         * @param predicate defines the predicate to use
         * @returns null if not found or the element
         */
        fun <T>First(array: Array<T>, predicate: (item: T) -> Boolean): T?
        /**
         * This method will return the name of the full name of the class, including its owning module (if Any).
         * It will works only on Javascript basic data types (Number, String, ...) and instance of class declared with the @className decorator or implementing a method getClassName():String (in which case the module won't be specified).
         * @param source the object to get the class name from
         * @param isType defines if the object is actually a type
         * @return a String that can have two forms: "moduleName.className" if module was specified when the class' Name was registered or "className" if there was not module specified.
         * @ignorenaming
         */
        fun getFullClassName(source : Any, isType: Boolean?): String?
        /**
         * Returns a promise that resolves after the given amount of time.
         * @param delay Number of milliseconds to delay
         * @returns Promise that resolves after the given amount of time
         */
        fun DelayAsync(delay: Number): Promise<Unit>
        /**
         * Gets the current gradient from an array of IValueGradient
         * @param ratio defines the current ratio to get
         * @param gradients defines the array of IValueGradient
         * @param updateFunc defines the callback function used to get the final value from the selected gradients
         */
        fun GetCurrentGradient(ratio: Number, gradients: Array<IValueGradient>, updateFunc: (current: IValueGradient, next: IValueGradient, scale: Number) -> Unit)
    }
}
