LICENSE     = "LGPL"
DESCRIPTION = "Data interchange library for GPE"
SECTION     = "gpe/libs"
PRIORITY    = "optional"
DEPENDS     = "libmimedir libeventdb"
MAINTAINER  = "Florian Boor <florian.boor@kernelconcepts.de>
PR          = "r1"

GPE_TARBALL_SUFFIX = "bz2"
inherit pkgconfig gpe autotools

headers = "tag-db.h vcard.h vevent.h vtodo.h"


do_stage () {
	oe_libinstall -so libgpevtype ${STAGING_LIBDIR}

	mkdir -p ${STAGING_INCDIR}/gpe
	for h in ${headers}; do
		install -m 0644 ${S}/gpe/$h ${STAGING_INCDIR}/gpe/${h}
	done
}


