# Using DataWedge with multiple Android activities

This article assumes familiarity with Zebra's DataWedge tool as well as the DataWedge profile mechanism.  For an overview of DataWedge, please refer to the [DataWedge Techdocs page](https://techdocs.zebra.com/datawedge/latest/guide/overview/)

A very common use case for many Zebra Android developers is using the device scanner across multiple activities in their application, this could cover the following:

- Scanning barcodes from multiple different activities
- Having different scanner configurations, depending on which activity is in the foreground

DataWedge has a number of ways to control the profile in effect or that profile's behaviour and it might not always be obvious which option to use:

| Mechanism  | Description  | Access by  | 
|---|---|---|
| [Profile activity association](https://techdocs.zebra.com/datawedge/latest/guide/createprofile/)  | Each profile can be (optionally) mapped to an application or an activity within that application.  When that activity comes to the foreground, the associated profile is applied  | DataWedge profile configuration  |
| The [SWITCH_TO_PROFILE_API](https://techdocs.zebra.com/datawedge/latest/guide/api/switchtoprofile/)  | Switch to the specified profile, provided that profile is not associated with another app/activity  | [The DataWedge API](https://techdocs.zebra.com/datawedge/latest/guide/api/)  |
| The [SWITCH_SCANNER_API](https://techdocs.zebra.com/datawedge/latest/guide/api/switchscanner/) (or similar)  | Make a temporary modification to the current profile, for example changing the scanner or the scanner parameters.  | [The DataWedge API](https://techdocs.zebra.com/datawedge/latest/guide/api/)  |
| The [SET_CONFIG_API](https://techdocs.zebra.com/datawedge/latest/guide/api/setconfig/)  |  Make a permanent change to the specified profile | [The DataWedge API](https://techdocs.zebra.com/datawedge/latest/guide/api/)  |

Although any of the above mechanisms could technically work, the "Profile activity association" technique is the recommended approach for handling scanning across multiple activities.

1. Create the DataWedge Profile or Profiles your application requires.  If you need different activities to have different data capture behaviour, for example, to recognize different symbologies, then create multiple profiles.  If your application will only have a single data capture configuration across multiple activities then a single profile will suffice.
Profiles can be created through [the DataWedge application](https://techdocs.zebra.com/datawedge/latest/guide/createprofile/) on the device, through the [CREATE_PROFILE](https://techdocs.zebra.com/datawedge/latest/guide/api/createprofile/) API or [mass deployed](https://techdocs.zebra.com/datawedge/latest/guide/settings/#massdeployment).
2. Associate the appropriate profiles with their corresponding activities
3. Deploy your application.  The scanner configuration will be automatically managed by DataWedge.

## Example

This [example application hosted in github](https://github.com/darryncampbell/DataWedge-Multiple-Activity) uses two activities, each associated with a different profile, imaginatively named "First Profile" and "Second Profile".

The DataWedge profiles are not automatically created for you, to use this app, first install it then create two profiles and associate them with the demo app as shown below:

Two profiles, one for each activity

![DataWedge Profiles](https://raw.githubusercontent.com/darryncampbell/DataWedge-Multiple-Activity/master/screenshots/dw-profiles.jpg)

The first profile is associated with the Main activity

![DataWedge Profiles](https://raw.githubusercontent.com/darryncampbell/DataWedge-Multiple-Activity/master/screenshots/dw-first-overview.jpg)
![DataWedge Profiles](https://raw.githubusercontent.com/darryncampbell/DataWedge-Multiple-Activity/master/screenshots/dw-first-associations.jpg)

The second profile is associated with the Second activity

![DataWedge Profiles](https://raw.githubusercontent.com/darryncampbell/DataWedge-Multiple-Activity/master/screenshots/dw-second-overview.jpg)
![DataWedge Profiles](https://raw.githubusercontent.com/darryncampbell/DataWedge-Multiple-Activity/master/screenshots/dw-second-associations.jpg)

The sample app itself will display the active profile in a text box at the top of the screen.  Notice the active profile changes depending on which activity is displayed:

![Demo app](https://raw.githubusercontent.com/darryncampbell/DataWedge-Multiple-Activity/master/screenshots/first.jpg)
![Demo app](https://raw.githubusercontent.com/darryncampbell/DataWedge-Multiple-Activity/master/screenshots/second.jpg)

**Be aware this demo does not process scanned data but merely shows the active profile.  To see how to scan data refer to one of the [existing samples](https://techdocs.zebra.com/datawedge/latest/guide/samples/), e.g. [Basic Intent](https://techdocs.zebra.com/datawedge/latest/guide/samples/basicintent1/)**

