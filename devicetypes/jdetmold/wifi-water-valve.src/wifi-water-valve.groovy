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
	        capability "Contact Sensor"
	        capability "Configuration"

	        attribute "powered", "string"
	        attribute "valveState", "string"

	}

	    // UI tile definitions
		tiles(scale: 2) {
			multiAttributeTile(name:"toggle", type: "generic", width: 6, height: 4, canChangeIcon: true, decoration: "flat"){
				tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
					attributeState "on", label: 'Closed', action: "switch.off", icon: "st.valves.water.closed", backgroundColor: "#ff0000", nextState:"openingvalve"
					attributeState "off", label: 'Open', action: "switch.on", icon: "st.valves.water.open", backgroundColor: "#53a7c0", nextState:"closingvalve"
					attributeState "closingvalve", label:'Closing', icon:"st.valves.water.closed", backgroundColor:"#ffd700"
					attributeState "openingvalve", label:'Opening', icon:"st.valves.water.open", backgroundColor:"#ffd700"
				}
	            tileAttribute ("statusText", key: "SECONDARY_CONTROL") {
	           		attributeState "statusText", label:'${currentValue}'       		
	            }
	        }

	        standardTile("switch", "device.contact", width: 3, height: 2, inactiveLabel: false) {
	            state "open", label: 'Open', icon: "st.valves.water.open", backgroundColor: "#53a7c0"
	            state "closed", label: 'Closed', icon: "st.valves.water.closed", backgroundColor: "#ff0000"
	        }

			
	        standardTile("switch", "device.switch", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "on", label: 'Open', action: "switch.off", icon: "st.valves.water.open", nextState:"Opening"
	        }
	        standardTile("switch", "device.switch", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "off", label: 'Close', action: "switch.on", icon: "st.valves.water.closed", nextState:"Closing"
	        }


	
	        standardTile("powered", "device.powered", width: 2, height: 2, inactiveLabel: false) {
				state "powerOn", label: "Power On", icon: "st.switches.switch.on"
				state "powerOff", label: "Power Off", icon: "st.switches.switch.off"
			}
	        standardTile("refresh", "device.switch", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
	            state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
	        }
			standardTile("configure", "device.configure", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
				state "configure", label:'', action:"configuration.configure", icon:"st.secondary.configure"
			}
	        valueTile("statusText", "statusText", inactiveLabel: false, width: 2, height: 2) {
				state "statusText", label:'${currentValue}', backgroundColor:"#ffffff"
			}
	        main (["switch", "contact"])
	        details(["switch", "powered", "refresh", "configure"])
	    }

	}

	def parse(String description) {
	
	}
/**
*	def sensorValueEvent(Short value) {
*	    if (value) {
*			log.debug "Main Water Valve is Open"
*			sendEvent(name: "contact", value: "open", descriptionText: "$device.displayName is open")
*	        sendEvent(name: "valveState", value: "flowing water (tap to close)")
*	    } else {
*	    	log.debug "Main Water Valve is Closed"
*	        sendEvent(name: "contact", value: "closed", descriptionText: "$device.displayName is closed")
*	        sendEvent(name: "valveState", value: "NOT flowing water (tap to open)")
*	    }
*	}
*
*/

	def on() {
		log.debug "Closing Main Water Valve per user request"
		put '1'
	}

	def off() {
		log.debug "Opening Main Water Valve per user request"
		put '0'
	}

	// This is for when the the valve's ALARM capability is called
	def both() {
		log.debug "Closing Main Water Valve due to an ALARM capability condition"
	}

	// This is for when the the valve's VALVE capability is called
	def close() {
		log.debug "Closing Main Water Valve due to a VALVE capability condition"
	}

	// This is for when the the valve's VALVE capability is called
	def open() {
		log.debug "Opening Main Water Valve due to a VALVE capability condition"
	}

	def poll() {
		log.debug "Executing Poll for Main Water Valve"
	}

	def refresh() {
		log.debug "Executing Refresh for Main Water Valve per user request"
	}

	def configure() {
		log.debug "Executing Configure for Main Water Valve per user request"
	}

	
	private put(valvestate) {
	    //Spark Core API Call
		httpPost(
			uri: "https://api.spark.io/v1/devices/${deviceId}/valvestate",
	        body: [access_token: token, command: led],  
		) {response -> log.debug (response.data)}
	}
