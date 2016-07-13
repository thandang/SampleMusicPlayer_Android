//
//  player.h
//  SampleMusicPlayer
//
//  Created by Than Dang on 7/5/16.
//  Copyright © 2016 Than Dang. All rights reserved.
//

#ifndef player_h
#define player_h

#include <stdio.h>
#include "block_objects.h"
#include "../../platform_gl.h"

#define MAX_NUM_COLUMN 6

static const float BOTTOM_Y = -0.3;
static const float HALF_POINT_SIZE = 17.0f;

void setup_screen();
void on_surface_changed(int width, int height);
void initial_data(InputData listDatas[MAX_NUM_COLUMN]);

void render_blocks();
void update_blocks();
void update_block_at_index(int index);


#endif /* player_h */
