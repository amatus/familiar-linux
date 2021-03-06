SECTION = "libs"
DESCRIPTION = "Secure Socket Layer (SSL) binary and related cryptographic tools."
HOMEPAGE = "http://www.openssl.org/"
LICENSE = "openssl"

SRC_URI = "http://www.openssl.org/source/openssl-${PV}.tar.gz"
S = "${WORKDIR}/openssl-${PV}"

AR_append = " r"
export CFLAG = "-fPIC -DTHREADS -D_REENTRANT -DDSO_DLFCN -DHAVE_DLFCN_H -DTERMIO -Wall ${FULL_OPTIMIZATION}"

# -02 does not work on mipsel: ssh hangs when it tries to read /dev/urandom
export CFLAG_mtx-1 := "${@'${CFLAG}'.replace('-O2', '')}"

export DIRS = "crypto ssl apps"
export EX_LIBS = "-lgcc -ldl -L${STAGING_LIBDIR}"
export AS = "${CC} -c"

PACKAGES =+ "libcrypto libssl"
FILES_libcrypto = "${libdir}/libcrypto.so*"
FILES_libssl = "${libdir}/libssl.so*"

do_compile () {
	cd util
	perl perlpath.pl ${bindir}
	cd ..
	ln -sf apps/openssl.pod crypto/crypto.pod ssl/ssl.pod doc/

	# endianness fun.. whee
	. ${CONFIG_SITE}
	if test "x$ac_cv_c_bigendian" = "xyes"; then
		CFLAG="${CFLAG} -DB_ENDIAN"
	elif test "x$ac_cv_c_littleendian" = "xyes"; then
		CFLAG="${CFLAG} -DL_ENDIAN"
	elif test "x$ac_cv_c_bigendian" = "xno" && test "x$ac_cv_c_littleendian" = "xno"; then
		# no idea what to do with this...
		:
	else
		CFLAG="${CFLAG} -DL_ENDIAN"
	fi

	os=${HOST_OS}
	if [ "x$os" = "xlinux-uclibc" ]; then
		os=linux
	fi
	target="$os-${HOST_ARCH}"
	case $target in
	linux-arm)
		target=linux-elf-arm
		;;
	linux-armeb)
		target=linux-elf-armeb
		;;
	linux-i586)
		target=linux-pentium
		;;
	linux-i686)
		target=linux-ppro
		;;
	linux-powerpc)
		target=linux-ppc
		;;
	esac
	perl ./Configure shared --prefix=${prefix} --openssldir=${libdir}/ssl $target
	oe_runmake
}

do_stage () {
	cp --dereference -R include/openssl ${STAGING_INCDIR}/
	oe_libinstall -a -so libcrypto ${STAGING_LIBDIR}
	oe_libinstall -a -so libssl ${STAGING_LIBDIR}
}

do_install () {
	install -m 0755 -d ${D}${libdir}/pkgconfig
	oe_runmake INSTALL_PREFIX="${D}" install
	chmod 644 ${D}${libdir}/pkgconfig/openssl.pc
	oe_libinstall -so libcrypto ${D}${libdir}
	oe_libinstall -so libssl ${D}${libdir}
}
