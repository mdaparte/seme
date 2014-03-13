/*
 * Copyright 2014 Sandia Corporation.
 * Under the terms of Contract DE-AC04-94AL85000 with Sandia Corporation, the U.S.
 * Government retains certain rights in this software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This software was written as part of an Inter-Agency Agreement between Sandia
 * National Laboratories and the US EPA NHSRC.
 */
/**
 * @if doxSemeMain
 * @mainpage The SeMe Framework
 *
 * The Sequentially Executed Model Evaluation (SeMe -- pronounced "see me")
 * Framework decouples model development from I/O development for certain
 * classes of discrete models. The model classes of interest are numerical
 * models that are evaluated sequentially for each step along a discrete axis.
 * Because most of these types of models are time-series models, so this
 * document will use time-series models in most examples and descriptions.
 * However, any model that is controlled by stepping a parameter along a
 * discrete axis will work with this framework. To do this, the controlling
 * parameter is divided into equal size steps, and the model is evaluated once
 * per step. For unitless parameters, integer steps are used. For time series
 * models, steps are in date/time format. And on other continuous axes, as long
 * as the axis is divided into equal size bins, a step index can be calculated
 * and used (such as depth along a bore hole).
 *
 * Referring back to time-series models, certain models are executed at a fixed
 * rate in real time. The data used in such real time models is generally
 * obtained in some way from physical measurements. For testing and development,
 * historical data is usually extracted into a historian database or into data
 * files. Unfortunately, real time data collection is hardly standardized – even
 * Supervisory Control and Data Acquisition (SCADA) systems are incredibly
 * diverse, and frequently involve custom, one-off databases for data
 * collection. Developing models that execute in real time is therefore
 * complicated by the need to interface with the database; the I/O effort can
 * quickly overwhelm the model development, and resulting models can end up tied
 * to specific databases or requiring massive rework to move the model to a
 * different system. The goal of the SeMe framework is to separate the model and
 * I/O efforts by providing a messaging interface that models can use for their
 * input and output while leaving the specific I/O drivers for someone else to
 * program (SEP).
 *
 * @htmlonly
 *
 * Sandia National Laboratories is a multi-program laboratory managed and
 * operated by Sandia Corporation, a wholly owned subsidiary of Lockheed Martin
 * Corporation, for the U.S. Department of Energy’s National Nuclear Security
 * Administration under contract DE-AC04-94AL85000.
 *
 * @endhtmlonly
 *
 * @endif
 */
/**
 * Provides SeMe framework interfaces and base classes.
 */
package gov.sandia.seme.framework;
