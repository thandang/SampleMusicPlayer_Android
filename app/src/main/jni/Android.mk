LOCAL_PATH := $(call my-dir)
PROJECT_ROOT_PATH := $(LOCAL_PATH)/../../../
CORE_RELATIVE_PATH := Libraries/Core/

include $(CLEAR_VARS)

LOCAL_MODULE    := player
LOCAL_CFLAGS    := -Wall -Wextra
LOCAL_SRC_FILES := platform_asset_utils.c \
                   platform_log.c \
                   renderer_wrapper.c \
				   $(CORE_RELATIVE_PATH)/shader_processor.c \
				   $(CORE_RELATIVE_PATH)/buffer.c \
				   $(CORE_RELATIVE_PATH)/block_objects.c \
                   $(CORE_RELATIVE_PATH)/player.c \
                   $(CORE_RELATIVE_PATH)/image.c \
                   $(CORE_RELATIVE_PATH)/program.c \
                   $(CORE_RELATIVE_PATH)/shader.c \
                   $(CORE_RELATIVE_PATH)/texture.c \
                  
LOCAL_C_INCLUDES := Libraries/common/
LOCAL_C_INCLUDES += Libraries/Core/
LOCAL_C_INCLUDES += Libraries/linmath/
LOCAL_STATIC_LIBRARIES := libpng
LOCAL_LDLIBS := -lGLESv2 -llog -landroid

include $(BUILD_SHARED_LIBRARY)

$(call import-add-path,Libraries)
$(call import-module,libpng)