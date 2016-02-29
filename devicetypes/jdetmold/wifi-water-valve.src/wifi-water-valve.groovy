/**
 *  wifi-water-valve
 *
 *  Copyright 2016 Jeff Detmold
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */

preferences {
    input("deviceId", "text", title: "Device ID")
    input("token", "text", title: "Access Token")
}

metadata {
	definition (name: "wifi-water-valve", namespace: "jdetmold", author: "Jeff Detmold") {
			capability "Alarm"
			capability "Polling"
	        capability "Refresh"
	        capability "Switch"
			capability "Valve"
            capability "Water Sensor"
	        capability "Contact Sensor"
	        capability "Configuration"

	        attribute "valveState", "string"

	}

	    // UI tile definitions
			tiles(scale: 2) {
            
				multiAttributeTile(name:"switch", type: "generic", width: 6, height: 4, canChangeIcon: true, decoration: "flat"){
					tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
						attributeState "off", label: 'Closed', action: "switch.off", icon: "st.valves.water.closed", backgroundColor: "#ff0000", nextState:"openingvalve"
						attributeState "on", label: 'Open', action: "switch.on", icon: "st.valves.water.open", backgroundColor: "#53a7c0", nextState:"closingvalve"
						attributeState "closingvalve", label:'Closing', icon:"st.valves.water.closed", backgroundColor:"#ffd700"
						attributeState "openingvalve", label:'Opening', icon:"st.valves.water.open", backgroundColor:"#ffd700"
					}
//		            tileAttribute ("statusText", key: "SECONDARY_CONTROL") {
//		           		attributeState "statusText", label:'${currentValue}'       		
//		            }
		        }
		        standardTile("refresh", "device.switch", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
		            state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
		        }
//		        valueTile("statusText", "statusText", inactiveLabel: false, width: 2, height: 2) {
//					state "statusText", label:'${currentValue}', backgroundColor:"#ffffff"
//				}

				standardTile("switchOpen", "device.switch", width: 2, height: 2, canChangeIcon: false, decoration: "flat") {
					state "on", label: 'open', action: "switch.off", icon: "st.valves.water.open"
				}
				standardTile("switchClose", "device.switch", width: 2, height: 2, canChangeIcon: false, decoration: "flat") {
					state "off", label: 'close', action: "switch.on", icon: "st.valves.water.closed"
				}

		        main (["switch", "contact"])
		        details(["switch","switchOpen", "switchClose", "powered", "refresh", "configure"])
		    }
		}

	def parse(String description) {
	
	}


	def refreshDelay(seconds) {
		log.debug "in start timer"
		def now = new Date()
        def runTime = new Date(now.getTime() + (seconds * 1000))
        runOnce(runTime, myTimerEventHandler)
	}

	def myTimerEventHandler() {
		//do the things that I want delayed in this function
		log.debug "doing the delayed things"
        refresh()
	}


	def on() {
		log.debug "Closing Main Water Valve per user request"
		put '1'
		def waitSeconds = 10
		refreshDelay(waitSeconds)
	}

	def off() {
		log.debug "Opening Main Water Valve per user request"
		put '0'
		def waitSeconds = 10
		refreshDelay(waitSeconds)
	}

	// This is for when the the valve's ALARM capability is called
	def both() {
		log.debug "Closing Main Water Valve due to an ALARM capability condition"
		put '1'
		def waitSeconds = 10
		refreshDelay(waitSeconds)
	}

	// This is for when the the valve's VALVE capability is called
	def close() {
		log.debug "Closing Main Water Valve due to a VALVE capability condition"
		put '1'
		def waitSeconds = 10
		refreshDelay(waitSeconds)
	}

	// This is for when the the valve's VALVE capability is called
	def open() {
		log.debug "Opening Main Water Valve due to a VALVE capability condition"
		put '0'
		def waitSeconds = 10
		refreshDelay(waitSeconds)
	}

	def poll() {
		log.debug "Executing Poll for Main Water Valve"
	}

	def refresh() {
		log.debug "Executing Refresh for Main Water Valve per user request"
		log.debug GetValveState();

		if (GetValveState() == 1) {
			sendEvent(name: "switch", value: off)
            log.debug "valve set to off";
        }
        if (GetValveState() == 0) {
        	sendEvent(name: "switch", value: on)
            log.debug "valve set to on";
		}
	}

	def configure() {
		log.debug "Executing Configure for Main Water Valve per user request"
	}

	
	private put(ValveAction) {
        log.debug "sending post";
		httpPost(
			uri: "https://api.spark.io/v1/devices/${deviceId}/ValveAction",
	        body: [access_token: token, command: ValveAction],  
		) {response -> log.debug (response.data)}
	log.debug "post sent";
    }
	
	def GetValveState()
	{
		def params = [
		uri: "https://api.spark.io/v1/devices/${deviceId}/ValveState?access_token=${token}"]
        //https://api.spark.io/v1/devices/[DeviceID]/ValveState?access_token=[Token]
		
		try{
			httpGet(params){ resp ->
			
			/*resp.headers.each {
			           log.debug "${it.name} : ${it.value}"
			        }

			        // get an array of all headers with the specified key
			        //def theHeaders = resp.getHeaders("Content-Length")

			        // get the contentType of the response
			        //log.debug "response contentType: ${resp.contentType}"*/

			        // get the status code of the response
			        //log.debug "response status code: ${resp.status}"

			        // get the data from the response body
			        //log.debug "response data: ${resp.data}"
                    return resp.data.result;
			    }
			} catch (e) {
			    log.error "something went wrong: $e"
			}
            
	}