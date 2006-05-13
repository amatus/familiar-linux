DESCRIPTION = "Meta-package for Enlightenment/X11"
PACKAGES = "task-e-x11"
PROVIDES = "task-e-x11"
PR = "r6"

FEED_URIS += "x11##http://ewi546.ewi.utwente.nl/mirror/www.openzaurus.org/official/unstable/3.5.2/feed/x11 \
              e##http://ewi546.ewi.utwente.nl/mirror/www.openzaurus.org/official/unstable/3.5.2/feed/e"

ALLOW_EMPTY = 1

DEPENDS              = "task-e-x11-core entice eclair examine evidence e-modules e-utils elitaire"
RDEPENDS_task-e-x11  = "task-e-x11-core entice eclair examine evidence e-modules e-utils elitaire"

