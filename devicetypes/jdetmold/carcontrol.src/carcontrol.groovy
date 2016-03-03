/**
 *  CarControl
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
	definition (name: "carcontrol", namespace: "jdetmold", author: "Jeff Detmold") {
		capability "Alarm"
		capability "Battery"
		capability "Polling"
		capability "Refresh"
		capability "Switch"
		capability "Temperature Measurement"

		attribute "switchUnLock", "string"
		attribute "switchLock", "string"
		attribute "switchTrunk", "string"
		attribute "switchStart", "string"
		attribute "switchPanic", "string"

		command "cmdUnLock"
		command "cmdLock"
		command "cmdTrunk"
		command "cmdStart"
		command "cmdPanic"
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles(scale: 2) {
		// TODO: define your main and details tiles here
        
			standardTile("switchUnLock", "device.switchUnLock", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
				state "on", label: "unlock", action: "cmdUnLock", icon: "st.switches.switch.on", backgroundColor: "#79b821"
				state "off", label: "unlock", action: "cmdUnLock", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
			}
    		standardTile("switchLock", "device.switchLock", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
				state "on", label: "lock", action: "cmdLock", icon: "st.switches.switch.on", backgroundColor: "#79b821"
				state "off", label: "lock", action: "cmdLock", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
			}
    		standardTile("switchTrunk", "device.switchTrunk", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
				state "on", label: "trunk", action: "cmdTrunk", icon: "st.switches.switch.on", backgroundColor: "#79b821"
				state "off", label: "trunk", action:"cmdTrunk", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
			}
    		standardTile("switchStart", "device.switchStart", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
				state "on", label: "start", action: "cmdStart", icon: "st.Transportation.transportation2", backgroundColor: "#79b821"
				state "off", label: "start", action:"cmdStart", icon: "st.Transportation.transportation2", backgroundColor: "#ffffff"
			}
    		standardTile("switchPanic", "device.switchPanic", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
				state "on", label: "panic", action: "cmdPanic", icon: "st.switches.switch.on", backgroundColor: "#79b821"
				state "off", label: "panic", action:"cmdPanic", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
		    }
			valueTile("temperature", "device.temperature", width: 2, height: 2) {
				state("temperature", label:'${currentValue}°', unit:"F",
				backgroundColors:[
					[value: 31, color: "#153591"],
					[value: 44, color: "#1e9cbb"],
					[value: 59, color: "#90d2a7"],
					[value: 74, color: "#44b621"],
					[value: 84, color: "#f1d801"],
					[value: 95, color: "#d04e00"],
					[value: 96, color: "#bc2323"]
				]
			)
			}
			standardTile("refresh", "device.switch", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
		    	state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
			}
	main (["switchStart"])
    details(["temperature","switchUnLock","switchLock","switchTrunk","switchStart","switchPanic","refresh"])
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
	// TODO: handle 'alarm' attribute
	// TODO: handle 'battery' attribute
	// TODO: handle 'switch' attribute
	// TODO: handle 'temperature' attribute
	// TODO: handle 'switchUnLock' attribute
	// TODO: handle 'switchLock' attribute
	// TODO: handle 'switchTrunk' attribute
	// TODO: handle 'switchStart' attribute
	// TODO: handle 'switchPanic' attribute

}

// handle commands


def strobe() {
	log.debug "Executing 'strobe'"
	// TODO: handle 'strobe' command
}

def siren() {
	log.debug "Executing 'siren'"
	// TODO: handle 'siren' command
}

def both() {
	log.debug "Executing 'both'"
	// TODO: handle 'both' command
}

def poll() {
	log.debug "Executing 'poll'"
	// TODO: handle 'poll' command
}

def refresh() {
	log.debug "Executing 'refresh'"
	// TODO: handle 'refresh' command
}

def on() {
	log.debug "Executing 'on'"
	// TODO: handle 'on' command
}

def off() {
	log.debug "Executing 'off'"
	// TODO: handle 'off' command
}

 // 1 = Unlock		switchUnLock	cmdUnLock
 // 2 = Lock		switchLock		cmdLock
 // 3 = Trunk		switchTrunk		cmdTrunk
 // 4 = Start		switchStart		cmdStart
 // 5 = Panic		switchPanic		cmdPanic

def cmdUnLock() {
	log.debug "Executing 'cmdUnLock'"
	// TODO: handle 'cmdUnLock' command
    put '1'
}

def cmdLock() {
	log.debug "Executing 'cmdLock'"
	// TODO: handle 'cmdLock' command
    put '2'
}

def cmdTrunk() {
	log.debug "Executing 'cmdTrunk'"
	// TODO: handle 'cmdTrunk' command
    put '3'
}

def cmdStart() {
	log.debug "Executing 'cmdStart'"
	// TODO: handle 'cmdStart' command
    put '4'
}

def cmdPanic() {
	log.debug "Executing 'cmdPanic'"
	// TODO: handle 'cmdPanic' command
    put '5'
}

private put(ValveAction) {
//	log.debug "sending post";
//		httpPost(
//			uri: "https://api.spark.io/v1/devices/${deviceId}/ValveAction",
//	        body: [access_token: token, command: ValveAction],  
//		) {response -> log.debug (response.data)}
//	log.debug "post sent";
    }
	
	def GetValveState() {
		def params = [
		uri: "https://api.spark.io/v1/devices/${deviceId}/ValveState?access_token=${token}"]		
		try{
			httpGet(params){ resp ->
                    return resp.data.result;
			    }
			} catch (e) {
			    log.error "something went wrong: $e"
			}
	}