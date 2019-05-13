/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ab.view.chart;

// TODO: Auto-generated Javadoc
/**
 * A zoom listener.
 *
 * @see ZoomEvent
 */
public interface ZoomListener {
  
  /**
   * Called when a zoom change is triggered.
   * @param e the zoom event
   */
  void zoomApplied(ZoomEvent e);
  
  /**
   * Called when a zoom reset is done.
   */
  void zoomReset();
}