/**
 *  sump-monitor
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
    input("MountHeight", "decimal", title: "MountHeight")
}
metadata {
	definition (name: "sump-monitor", namespace: "jdetmold", author: "Jeff Detmold") {
		capability "Alarm"
		capability "Notification"
		capability "Polling"
		capability "Refresh"
		capability "Sensor"
		capability "Water Sensor"
        attribute "depth", "string"
	}

	tiles(scale: 2) {
        valueTile("depth", "device.depth", width: 6, height: 4) {
        state("depth", label:'${currentValue}"',
            backgroundColors:[
                [value: 2, color: "#00FF00"], //green
                [value: 4, color: "#FFFF00"], //yellow
                [value: 6, color: "#FF0000"] //red
            ]
        )
    }
		standardTile("refresh", "device.switch", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
		}
    
		main (["depth"])
		details(["depth", "refresh"])
    }
    
    
	simulator {
		// TODO: define status and reply messages here
	}

}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
	// TODO: handle 'alarm' attribute
	// TODO: handle 'water' attribute

}

// handle commands
def off() {
	log.debug "Executing 'off'"
	// TODO: handle 'off' command
}

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

def deviceNotification() {
	log.debug "Executing 'deviceNotification'"
	// TODO: handle 'deviceNotification' command
}

def poll() {
	log.debug "Executing 'poll'"
	// TODO: handle 'poll' command
}

def refresh() {
	log.debug "Executing 'refresh'"
    log.debug "Mount Height: ${MountHeight}"
	log.debug "sump reading: " + GetSumpDepth();
    def TotalDepth = MountHeight + GetSumpDepth()
	log.debug "total Depth: " + TotalDepth	
	sendEvent(name: "depth", value: TotalDepth)
    createEvent(name: "depth", value: TotalDepth)
}


def GetSumpDepth(){
		def params = [
		uri: "https://api.particle.io/v1/devices/${deviceId}/sumpdepth?access_token=${token}"]
		try{
			httpGet(params){ resp ->
                    return resp.data.result;
			    }
			} catch (e) {
			    log.error "something went wrong: $e"
			}
}

