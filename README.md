# NotVLC
NotVLC is a simple (yet effective) App that catches all ACTION_VIEW intents for the VLC Video player and redirects them to the default video player app (or shows a selection dialog if no default is set).<br/>
This allows you to use any video player on apps that force you to use VLC (or annoy you until you install VLC).

[Download NotVLC](https://github.com/shadow578/NotVLC/releases)

## Why?
Because some apps (_&ast;cough&ast; &ast;cough&ast; [AnYme](https://github.com/zunjae/AnYme)_) will annoy you forever until you finally give in and install VLC over any other video player...<br/>
Like, I know that some streams __may__ not work in my video player and VLC would probably play them just fine, but can't you just allow me to disable that warning and live a dangerous live of not knowing that the stream will play??

## I Can't Install The App
* First off, check that VLC is __not__ installed. If its installed, uninstall it. This app cannot work alongside VLC (since they share the same package name)
* If you have a Anti- Virus, you may have to disable that. Most Anti- Virus Apps will give you a warning because I'm not (yet) a recognised developer
* The same as above also is valid for Googles PlayProtect
* If this does not help, please write a Issue

## Disclaimer
This project uses _some_ parts of the original VLC android app, mainly:
* parts of the manifest (to maintain compatibility with video and link formats)
* the package name (otherwise, all this wouldn't work)
If you're not okay with this, please contact me.
